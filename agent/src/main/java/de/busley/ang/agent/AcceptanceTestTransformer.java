package de.busley.ang.agent;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.Done;
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.stream;
import static javassist.CtClass.voidType;

/**
 * @author Martin Busley
 */
public class AcceptanceTestTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass;
        try {
            ctClass = classPool.getCtClass(className);
            ctClass.defrost();
        } catch (NotFoundException e) {
            throw new InternalError(e.getMessage(), e);
        }

        if (!isAcceptanceTest(ctClass)) {
            return null;
        }

        stream(ctClass.getDeclaredMethods())
                .filter(AcceptanceTestTransformer::isTestMethod)
                .filter(AcceptanceTestTransformer::notDone)
                .forEach((ctMethod) -> catchAssertionError(classPool, ctMethod));

        try {
            return ctClass.toBytecode();
        } catch (IOException | CannotCompileException e) {
            throw new InternalError(e.getMessage(), e);
        }
    }

    private void catchAssertionError(ClassPool classPool, CtMethod ctMethod) {
        try {
            ctMethod.addCatch("{return;}", classPool.get("java.lang.AssertionError"));
        } catch (NotFoundException | CannotCompileException e) {
            throw new InternalError(e.getMessage(), e);
        }
    }

    private static boolean isTestMethod(CtMethod ctMethod) {
        int modifiers = ctMethod.getModifiers();
        try {
            return isPublic(modifiers) && !isStatic(modifiers)
                    && ctMethod.getReturnType().equals(voidType);
        } catch (NotFoundException e) {
            throw new InternalError(e.getMessage(), e);
        }
    }

    private static boolean notDone(CtMethod ctMethod) {
        try {
            return ctMethod.getAnnotation(Done.class) == null;
        } catch (ClassNotFoundException e) {
            throw new InternalError(e.getMessage(), e);
        }
    }

    private static boolean isAcceptanceTest(CtClass ctClass) {
        try {
            return ctClass.getAnnotation(AcceptanceTest.class) != null;
        } catch (ClassNotFoundException e) {
            throw new InternalError(e.getMessage(), e);
        }
    }
}

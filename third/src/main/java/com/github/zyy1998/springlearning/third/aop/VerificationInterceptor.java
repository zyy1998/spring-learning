package com.github.zyy1998.springlearning.third.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 验证码验证拦截
 */
@Aspect
@Component
public class VerificationInterceptor {

    @Before("@annotation(com.github.zyy1998.springlearning.third.aop.Verification)")
    public void interceptor(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Verification verificationAnnotation = method.getAnnotation(Verification.class);
        Verification.VerificationEnums verificationEnums = verificationAnnotation.type();
        String uuid = verificationAnnotation.uuid();
        System.out.println("enums:" + verificationEnums + "\tuuid:" + uuid);
        boolean result = "uuidok".equals(uuid);
        if (result) {
            return;
        }
        throw new RuntimeException("uuid错误");
    }
}
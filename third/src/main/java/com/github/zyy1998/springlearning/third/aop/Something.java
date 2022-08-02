package com.github.zyy1998.springlearning.third.aop;

import org.springframework.stereotype.Service;

@Service
public class Something {
    @Verification(uuid = "uuidok", type = Verification.VerificationEnums.LOGIN)
    public void methodWithVerificationAop() {
        System.out.println("this is aop method with verification aop");
    }

    @Verification(uuid = "uuid error", type = Verification.VerificationEnums.LOGIN)
    public void methodWithVerificationAop2() {
        System.out.println("this is aop method with verification aop");
    }
}

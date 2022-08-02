package com.github.zyy1998.springlearning.third.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SomethingTest {
    @Autowired
    private Something something;

    @Test
    public void testOK() {
        assertThat(something).isNotNull();
        something.methodWithVerificationAop();
    }

    @Test
    public void testError() {
        assertThat(something).isNotNull();
        something.methodWithVerificationAop2();
    }
}
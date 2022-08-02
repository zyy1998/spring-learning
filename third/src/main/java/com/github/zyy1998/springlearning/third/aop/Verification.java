package com.github.zyy1998.springlearning.third.aop;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Verification {
    /**
     * uuid
     *
     * @return String
     */
    String uuid();

    /**
     * 验证类型
     */
    VerificationEnums type();

    public enum VerificationEnums {

        /**
         * 登录
         * 注册
         * 找回用户
         * 修改密码
         * 支付钱包密码
         */
        LOGIN,
        REGISTER,
        FIND_USER,
        UPDATE_PASSWORD,
        WALLET_PASSWORD;
    }


}

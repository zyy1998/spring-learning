package com.github.zyy1998.springlearning.redis.entity;

/**
 * 购物车类型
 * 暂时只支持立即购买，使用购物车是为了后续的扩充
 */
public enum CartTypeEnum {

    /**
     * 立即购买
     */
    BUY_NOW;

    public String getPrefix() {
        return "{" + this.name() + "}_";
    }

}

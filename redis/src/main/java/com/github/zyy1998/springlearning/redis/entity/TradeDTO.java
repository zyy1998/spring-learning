package com.github.zyy1998.springlearning.redis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车视图
 * TradeDTO必须有构造函数，否则无法从redis反序列化
 */
@Data
@NoArgsConstructor

public class TradeDTO implements Serializable {
    private static final long serialVersionUID = -7549869426030355966L;

    private String sn;

    /**
     * 买家名称
     */
    private String memberName;

    /**
     * 买家id
     */
    private String memberId;

    /**
     * 购物车类型
     */
    private CartTypeEnum cartTypeEnum;

    /**
     * 整笔交易中所有的规格商品
     */
    private List<Goods> goodsList;

    public TradeDTO(CartTypeEnum cartTypeEnum) {
        this.cartTypeEnum = cartTypeEnum;
        this.goodsList = new ArrayList<>();
    }
}

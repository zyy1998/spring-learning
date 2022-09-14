package com.github.zyy1998.springlearning.redis.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {
    private static final long serialVersionUID = -8501576031783063438L;
    private String name;

    private Double price;

    private String intro;
}

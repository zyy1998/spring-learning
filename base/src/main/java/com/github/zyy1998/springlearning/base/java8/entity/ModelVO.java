package com.github.zyy1998.springlearning.base.java8.entity;


import java.io.Serializable;

public class ModelVO implements Serializable {
    private static final long serialVersionUID = -3144029400800395165L;

    private Long id;

    private String name;

    public ModelVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

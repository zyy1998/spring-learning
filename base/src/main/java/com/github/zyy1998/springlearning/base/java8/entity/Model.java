package com.github.zyy1998.springlearning.base.java8.entity;


import java.io.Serializable;

public class Model implements Serializable {
    private static final long serialVersionUID = -8501576031783063438L;

    private Long id;

    private String name;

    private String path;

    public Model(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

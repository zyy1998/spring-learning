package com.github.zyy1998.springlearning.springbase.jackson;

import java.io.Serializable;

public class JacksonModel1 implements Serializable {

    private static final long serialVersionUID = -3383316571171074783L;

    private String URL;
    private Long appId;

    private String TeST;


    public String getTeST() {
        return TeST;
    }

    public void setTeST(String teST) {
        TeST = teST;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
}

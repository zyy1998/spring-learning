package com.github.zyy1998.springlearning.springbase.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Getter也要加注解，否则json字符串会多个url和teST的key
 * 但是如果用了Lombok似乎就不用在getter上也加注解了
 */
public class JacksonModel2 implements Serializable {

    private static final long serialVersionUID = -3383316571171074783L;

    @JsonProperty("URL")
    private String URL;
    private Long appId;
    @JsonProperty("TeST")
    private String TeST;


    @JsonProperty("TeST")
    public String getTeST() {
        return TeST;
    }

    public void setTeST(String teST) {
        TeST = teST;
    }

    @JsonProperty("URL")
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

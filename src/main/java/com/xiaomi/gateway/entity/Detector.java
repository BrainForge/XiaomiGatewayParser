package com.xiaomi.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by anton on 17.12.16.
 */
public class Detector {
    @JsonProperty("cmd")
    private String cmd;
    @JsonProperty("model")
    private String model;
    @JsonProperty("sid")
    private String sid;
    @JsonProperty("short_id")
    private String shortId;
    @JsonProperty("data")
    private TempDetector data;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public TempDetector getData() {
        return data;
    }

    public void setData(TempDetector data) {
        this.data = data;
    }
}

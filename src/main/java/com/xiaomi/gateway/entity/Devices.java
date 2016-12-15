package com.xiaomi.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Anton Afanasyeu on 12/15/16.
 */
public class Devices {
    @JsonProperty("cmd")
    private String cmd;
    @JsonProperty("sid")
    private String sid;
    @JsonProperty("token")
    private String token;
    @JsonProperty("data")
    private List<String> data = null;
}

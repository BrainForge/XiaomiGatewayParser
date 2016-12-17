package com.xiaomi.gateway.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by anton on 17.12.16.
 */
public class TempDetector {

    @JsonProperty("temperature")
    private String temperature;
    @JsonProperty("humidity")
    private String humidity;

    public Integer getTemperature() {
        return Integer.valueOf(temperature);
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return Integer.valueOf(humidity);
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}

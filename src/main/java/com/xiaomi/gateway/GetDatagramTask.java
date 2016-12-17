package com.xiaomi.gateway;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaomi.gateway.entity.Detector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Anton Afanasyeu on 12/15/16.
 */
@Component
public class GetDatagramTask {

    @Autowired
    private Datagram datagram;

    @Scheduled(fixedRate=5000)
    public void getData() {
        datagram.getDataForSid("158d0001143109");

        datagram.setJsonCallback((json -> {
            json = json.replace("\\","");
            json = json.replace("\"{","{");
            json = json.replace("}\"","}");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                Detector detector = mapper.readValue(json, Detector.class);
                System.out.println("temperature = " +
                        detector.getData().getTemperature()/100f +
                " humidity = " + detector.getData().getHumidity()/100f);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }));

    }
}

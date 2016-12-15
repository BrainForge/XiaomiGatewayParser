package com.xiaomi.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Anton Afanasyeu on 12/15/16.
 */
@Component
public class GetDatagramTask {

    @Autowired
    private Datagram datagram;

    @Scheduled(fixedRate=5000)
    public void getData() {
        datagram.sendWhois();

        datagram.setJsonCallback((json -> {
            System.out.println(json);
        }));

    }
}

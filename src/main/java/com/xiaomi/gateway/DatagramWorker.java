package com.xiaomi.gateway;

import org.springframework.stereotype.Component;

/**
 * Created by Anton Afanasyeu on 12/19/16.
 */
@Component
public class DatagramWorker {

    public DatagramWorker() {

        Datagram datagram = new Datagram();

        datagram.getDeviceList();

        datagram.setJsonCallback(json -> {
            System.out.println("rec: " + json);
        });
    }
}

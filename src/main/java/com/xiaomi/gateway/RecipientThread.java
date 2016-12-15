package com.xiaomi.gateway;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * Created by Anton Afanasyeu on 12/15/16.
 */
public class RecipientThread implements Runnable {

    private JsonCallback jsonCallback;
    private MulticastSocket mcSocket;

    public RecipientThread(JsonCallback jsonCallback, MulticastSocket mcSocket) {
        this.jsonCallback = jsonCallback;
        this.mcSocket = mcSocket;
    }

    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        while (true) {
            try {
                mcSocket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String msg = new String(packet.getData(), packet.getOffset(),
                    packet.getLength());

            if(jsonCallback != null)
                jsonCallback.json(msg);

            System.out.println("[Multicast  Receiver] Received:" + msg);
        }
    }

}

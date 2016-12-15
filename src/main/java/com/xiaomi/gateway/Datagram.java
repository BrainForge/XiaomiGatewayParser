package com.xiaomi.gateway;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Anton Afanasyeu on 12/15/16.
 */
@Component
@Scope("singleton")
public class Datagram {

    private int mcPort = 4321;
    private String mcIPStr = "224.0.0.50";
    private MulticastSocket mcSocket = null;
    private InetAddress mcIPAddress = null;
    private InetAddress gateway = null;

    private JsonCallback jsonCallback;

    public Datagram() {
        try {
            mcIPAddress = InetAddress.getByName(mcIPStr);
            gateway = InetAddress.getByName("192.168.0.100");

            mcSocket = new MulticastSocket(mcPort);
            mcSocket.setReuseAddress(true);
            System.out.println("Multicast Receiver running at:"
                    + mcSocket.getLocalSocketAddress());
            mcSocket.joinGroup(mcIPAddress);

            System.out.println("Waiting for a  multicast message...");

            new Thread(new RecipientThread((json -> {
                if(jsonCallback!=null)
                    jsonCallback.json(json);
            }), mcSocket)).start();

        } catch (UnknownHostException e) {
            System.out.println();
        } catch (IOException e) {
            System.out.println();
        }
    }



    private MulticastSocket getSocket() {
        return mcSocket;
    }

    public void setJsonCallback(JsonCallback jsonCallback) {
        this.jsonCallback = jsonCallback;
    }

    public void sendJsonToGateway(String json) {
        DatagramPacket dp = new DatagramPacket(json.getBytes(),
                json.getBytes().length, gateway, 9898);
        send(dp);
    }

    public void sendWhois() {
        String json = "{\"cmd\": \"whois\"}";
        DatagramPacket dp = new DatagramPacket(json.getBytes(),
                json.getBytes().length, mcIPAddress, mcPort);
        this.send(dp);
    }

    private void send(DatagramPacket dp) {
        try {
            getSocket().send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDeviceList() {
        String json = "{\"cmd\":\"get_id_list\"}";
        sendJsonToGateway(json);
    }
}

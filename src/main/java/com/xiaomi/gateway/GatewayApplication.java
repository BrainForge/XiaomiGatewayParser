package com.xiaomi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.*;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

        int mcPort = 4321;
        String mcIPStr = "224.0.0.50";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        InetAddress gateway = null;
        try {
            mcIPAddress = InetAddress.getByName(mcIPStr);
            gateway = InetAddress.getByName("192.168.0.100");

            mcSocket = new MulticastSocket(mcPort);
            mcSocket.setReuseAddress(true);
            System.out.println("Multicast Receiver running at:"
                    + mcSocket.getLocalSocketAddress());
//            InetSocketAddress address = new InetSocketAddress(9898);
//            mcSocket.bind(address);
            mcSocket.joinGroup(mcIPAddress);


            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

            System.out.println("Waiting for a  multicast message...");
            String json = "{\"cmd\": \"whois\"}";
            DatagramPacket dp = new DatagramPacket(json.getBytes(),
                    json.getBytes().length, mcIPAddress, mcPort);

            String json2 = "{\"cmd\":\"get_id_list\"}";
            DatagramPacket dp2 = new DatagramPacket(json2.getBytes(),
                    json2.getBytes().length, gateway, 9898);

            String json3 = "{\"cmd\":\"read\", \"sid\":\"158d0001051e02\"}";
            DatagramPacket dp3 = new DatagramPacket(json3.getBytes(),
                    json3.getBytes().length, gateway, 9898);

            mcSocket.send(dp);
            while (true) {
                mcSocket.send(dp3);
                mcSocket.receive(packet);
                String msg = new String(packet.getData(), packet.getOffset(),
                        packet.getLength());
                System.out.println("[Multicast  Receiver] Received:" + msg);
                Thread.sleep(2000);
            }
        } catch (UnknownHostException e) {
            System.out.println();
        } catch (IOException e) {
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println();
        }

        //mcSocket.leaveGroup(mcIPAddress);
        //mcSocket.close();


    }
}

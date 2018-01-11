package ex3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SingleMulticastClientServer {


    private static final int PACKET_LENGTH = 1024;
    private final InetAddress group;
    private final int receivePort;

    private MulticastSocket multiClient;
    private MulticastSocket multiServer;

    public SingleMulticastClientServer(InetAddress group, int receivePort) throws IOException {
        this.group = group;
        this.receivePort = receivePort;

        multiClient = new MulticastSocket(receivePort);
        multiClient.setTimeToLive(1);
        multiClient.joinGroup(group);

        multiServer = new MulticastSocket(receivePort);
        multiServer.setTimeToLive(1);
        multiServer.joinGroup(group);
    }

    public void listen() {
        try {
            byte[] buffer = new byte[PACKET_LENGTH];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            multiClient.receive(packet);


            String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println("received: " + result + " from " + packet.getAddress().getHostName());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not receive");
        }
    }


    public void send(String message) {
        byte[] byteData = message.getBytes();
        DatagramPacket packet = new DatagramPacket(byteData, byteData.length, group, receivePort);

        try {
            multiServer.send(packet);
            System.out.println("sent message: " + message + " to " + group.getHostName());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed to send message");
        }
    }

    public void close() {
        multiClient.close();
        multiServer.close();
    }

    public static void main(String[] args) throws IOException {
        SingleMulticastClientServer single = new SingleMulticastClientServer(InetAddress.getByName("224.0.0.1"),
                7654);

        new Thread(
                () -> {
                    System.out.println("client is listening");
                    while (true) {
                        single.listen();
                    }
                }
        ).start();

        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        single.send("hello " + i);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();


    }
}

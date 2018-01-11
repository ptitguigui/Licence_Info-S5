import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {


    private static final int PACKET_LENGTH = 1024;

    private DatagramSocket socket;
    private DatagramPacket packet;

    public void listen(int port) {
        try {
            socket = new DatagramSocket(port);
            packet = new DatagramPacket(new byte[PACKET_LENGTH], PACKET_LENGTH);


            System.out.println("listening on " + port);

            socket.receive(packet);

            String result = new String(packet.getData(), 0, packet.getLength());
            System.out.println("received: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }

}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {


    private static final int PACKET_LENGTH = 1024;

    private DatagramSocket socket;
    private DatagramPacket packet;

    public void listen(int port) {
        try {
            byte[] buffer = new byte[PACKET_LENGTH];
            packet = new DatagramPacket(buffer, buffer.length);

            socket = new DatagramSocket(port);

            System.out.println("listening on " + port);

            socket.receive(packet);


            String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
            System.out.println("received: " + result);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not receive");
        }

        socket.close();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.listen(9090);
    }

}

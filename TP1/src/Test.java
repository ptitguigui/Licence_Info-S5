import java.io.IOException;
import java.net.InetAddress;

public class Test {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        Client client = new Client();


        new Thread(() -> server.listen(9090)).run();

        client.send("hello", InetAddress.getByName("a15p12"), 9090);

    }

}

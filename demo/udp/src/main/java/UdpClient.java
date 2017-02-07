import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Created by jens on 07.02.17.
 */
public class UdpClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        socket.connect(new InetSocketAddress("localhost", 8080));

        byte[] bytes = "Hello world".getBytes();
        socket.send(new DatagramPacket(bytes, bytes.length));
    }

}

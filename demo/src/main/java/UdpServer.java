import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Created by jens on 07.02.17.
 */
public class UdpServer {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(
                new InetSocketAddress("localhost", 8080));

        byte[] buffer = new byte[10];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        System.out.println(new String(buffer));
    }

}

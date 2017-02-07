import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by scheldejonas on 07/02/17.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        // TODO: Run the udp server in a thread here

        UdpServer udpServerOne = new UdpServer();

        // TODO: 2. Try to start the socket without the server. Do you get an exception? Why/Why not?


        // TODO: 3. Start the server and send the message from above to the server. What are you receiving?

        udpServerOne.startServer();

        UdpClient udpClient = new UdpClient();

        udpClient.sendMessageToUdpServer();

    }
}

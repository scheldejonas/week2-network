import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * <h2>Task 1</h2>
 * <p>
 *     Your first task is to write an UDP server instead of a TCP server. UDP splits your data into small data fragments
 *     and sends them off without any guarantee of order: you don't know which package comes first! (A bit like
 *     concurrency). Instead of the constantly open output- and input-streams from the TCP server, you receive and
 *     send things from/to byte arrays.
 * </p>
 * <p>
 *     So, each package receives a certain amount of bytes. In our (kinda artificial) case we only receive 10 bytes.
 *     Use the {@link #DATA_BUFFER} for storing and retrieving data in this exercise.
 * </p>
 * <p>
 *     Your job is to implement a UDP server that binds to a socket on your local machine and waits until a client
 *     sends some data. When the data arrives you print it out. That's it! When it's printed, notice how much data is
 *     sent (and how much isn't). That's the drawback of this approach. On the other hand it's fast!
 * </p>
 */
public class UdpServer implements Runnable {

    private static final byte[] DATA_BUFFER = new byte[10];

    @Override
    public void run() {
        // TODO 1. Create a UDP server that binds (listens) to a port

        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            datagramSocket.bind(new InetSocketAddress("localhost",7070));
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // TODO 2. Wait for some data to arrive

        byte[] buffer = new byte[10];

        DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);

        try {
            while (true) {

                datagramSocket.receive(datagramPacket);

                System.out.printf("Printing data...: \n %s", datagramPacket.getData().length);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO 3. Print the data!
    }

    public static void main(String[] args) throws IOException {
        // Run the udp server in a thread here
        // Wait for the thread to finish before the program stops
    }

}

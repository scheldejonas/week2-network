import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

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
public class UdpServer {

    private static final byte[] DATA_BUFFER = new byte[10];

    public UdpServer() {
    }

    public void startServer() {
        // TODO 1. Create a UDP server that binds (listens) to a port

        DatagramSocket datagramSocket = null;

        try {
            datagramSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            datagramSocket.bind(new InetSocketAddress("localhost",8080) );
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // TODO 2. Wait for some data to arrive

        byte[] buffer = new byte[10];

        DatagramPacket datagramRecievePacket = new DatagramPacket(buffer,buffer.length);

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ExecutorService executorService = Executors.newFixedThreadPool(128,threadFactory);

        try {
            while (true) {

                datagramSocket.receive(datagramRecievePacket);

                executorService.execute(new ConnectionProcess(datagramRecievePacket,datagramSocket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // TODO: Wait for the thread to finish before the program stops by Exceptions
            executorService.shutdown();
        }

    }

    class ConnectionProcess extends Thread {

        private DatagramPacket datagramPacket;
        private DatagramSocket datagramSocketServer;
        private ReentrantLock reentrantLock;

        public ConnectionProcess(DatagramPacket datagramPacket, DatagramSocket datagramSocketServer) {
            this.datagramPacket = datagramPacket;
            this.datagramSocketServer = datagramSocketServer;
            this.reentrantLock = new ReentrantLock();
        }

        @Override
        public void run() {

            try {
                reentrantLock.lock();

                String recieveText = new String(datagramPacket.getData());

                // TODO 3. Print the data!

                System.out.printf("Printing data...: \n %s", recieveText);

                InetAddress sendBackInetIpAddress = datagramPacket.getAddress();

                int sendBackPort = datagramPacket.getPort();

                String confirmSentence = "I have recieved you message";

                byte[] sendDataByteArray = new byte[confirmSentence.length()];

                sendDataByteArray = confirmSentence.getBytes();

                DatagramPacket sendDatagramPacket = new DatagramPacket(sendDataByteArray,sendDataByteArray.length,sendBackInetIpAddress,sendBackPort);

                try {
                    datagramSocketServer.send(sendDatagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } finally {
                reentrantLock.unlock();
            }

        }
    }

}

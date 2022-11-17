package udpsample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPSample {

    public static final int PORT = 60000;
    public static final int BUFFER_SIZE = 256;
    public static final String END_COMUNICATION_REQUEST = "END_COMUNICATION";

    public static void main(String[] args) throws SocketException, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramSocket socket = new DatagramSocket(PORT);
        DatagramPacket packet = new DatagramPacket(buffer, BUFFER_SIZE);
        boolean isRunning = true;

        while (isRunning) {
            socket.receive(packet);

            String request = new String(packet.getData(), 0, packet.getLength());
            isRunning = !request.equals(END_COMUNICATION_REQUEST);
        }
    }
}

package tcpbyteprotocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPByteProtocol {

    public static int SERVER_PORT = 50000;

    public static void runServer(int port, int moltiplicatore) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        byte[] inputBuffer = new byte[4]; 
        in.read(inputBuffer);
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputBuffer);

        int number = byteBuffer.getInt();
        byteBuffer.position(byteBuffer.position() - 4);

        System.out.println(number);
        byteBuffer.putInt(number * moltiplicatore);
        out.write(byteBuffer.array());

        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

    static public void runClient(InetAddress address, int port, int number) throws IOException {
        Socket socket = new Socket(address, port);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        byte[] inputBuffer = new byte[4]; 
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputBuffer);
        byteBuffer.putInt(number);
        out.write(byteBuffer.array());

        in.read(inputBuffer);
        byteBuffer = ByteBuffer.wrap(inputBuffer);
        System.out.println(byteBuffer.getInt());
        byteBuffer.position(byteBuffer.position() - 4);
        
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                runServer(SERVER_PORT, 10);
            } catch (IOException ex) {
                Logger.getLogger(TCPByteProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        
        new Thread(() -> {
            try {
                runClient(InetAddress.getLocalHost(), SERVER_PORT, 10);
            } catch (UnknownHostException ex) {
                Logger.getLogger(TCPByteProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TCPByteProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
}

package tcpprotocol.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientNetwork {
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ClientNetwork(Socket socket) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public ClientNetwork(InetAddress address, int port) throws IOException {
        this(new Socket(address, port));
    }

    public void sendObject(Object object) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(out);
        objectOut.writeObject(object);
    }
}

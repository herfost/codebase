package tcpprotocol.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetwork {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ServerNetwork(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        socket = serverSocket.accept();
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public ServerNetwork(int port) throws IOException {
        this(new ServerSocket(port));
    }

    public final Object receiveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(in);
        return objectIn.readObject();
    }
}

package tcpprotocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcpprotocol.domain.Card;
import tcpprotocol.network.ClientNetwork;
import tcpprotocol.network.ServerNetwork;

public class TCPProtocol {

    public static int SERVER_PORT = 50000;

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                ServerNetwork server = new ServerNetwork(SERVER_PORT);
                Card card = (Card) server.receiveObject();
                System.out.println(card.getName());
            } catch (IOException ex) {
                Logger.getLogger(TCPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TCPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

        new Thread(() -> {
            try {
                ClientNetwork client = new ClientNetwork(InetAddress.getLocalHost(), SERVER_PORT);
                client.sendObject(new Card("Client's Card"));
            } catch (UnknownHostException ex) {
                Logger.getLogger(TCPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TCPProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
}

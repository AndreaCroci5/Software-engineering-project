package it.polimi.ingsw.am40.server.network;

import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.Protocol;

import java.io.IOException;
import java.net.Socket;

/**
 * Interface NetworkManagerServer ensures methods to manage the communications on the network and
 * to handle incoming requests from clients
 */
public interface NetworkManagerServer {

    /**
     * Method to start communications with a specific protocol
     *
     * @return
     */
    public void initCommunication();


    /**
     * Method to init the ping logic in order to know if clients are still connected
     */
     void initPing();

     public void setPort(int port);

     public int getPort();

     public void setHostName(String hostName);

     public String getHostName();

     public Protocol getUsedProtocol();

    public int connectedClientNotification(Socket socket);

    public void disconnectedClientNotification(NetworkClient client) throws IOException;

    public void sendSerializedMessage(Data message, NetworkClient client);
}

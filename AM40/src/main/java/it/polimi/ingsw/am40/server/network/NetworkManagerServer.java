package it.polimi.ingsw.am40.server.network;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
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
     */
    void initCommunication();


    /**
     * Method to init the ping logic in order to know if clients are still connected
     */
     void initPing();

    /**
     * Setter the attribute port
     * @param port the port number
     */
     void setPort(int port);

    /**
     * Getter for port
     * @return the port number used for the communications
     */
     int getPort();


    /**
     * Setter for the host name
     * @param hostName the name of the server on the network
     */
    void setHostName(String hostName);


    /**
     * Getter for host name
     * @return the host name of the server on the network
     */
     String getHostName();


    /**
     * Getter for the used protocol
     * @return the protocol used for the communications
     */
    Protocol getUsedProtocol();

    /**
     * Method to notify VVServer that a connection with a new client has been accepted
     * @param socket the socket of the client
     * @return the ID of the client
     */
    int newConnectedClientNotification(Socket socket, RemoteInterfaceClient remoteInterface);

    /**
     * Method to notify the VVServer that a client has been reconnected
     * @param clientName the host name of the client
     */
    void reconnectionNotification(String clientName);

    /**
     * Method to notify the VVServer of a client disconnection
     * @param client
     * @throws IOException
     */
    void disconnectedClientNotification(NetworkClient client) throws IOException;

    /**
     * Method to remove a Client
     */
    void removeClientNotification();

    /**
     * Method to send to a specific client a network message
     * @param message the message to send
     * @param client the recipient
     */
    void sendSerializedMessage(Data message, NetworkClient client);
}

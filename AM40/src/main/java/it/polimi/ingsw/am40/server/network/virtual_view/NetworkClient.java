package it.polimi.ingsw.am40.server.network.virtual_view;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.TCP.Streams;

import java.net.Socket;
import java.util.List;


/**
 * Class which represents a client from the network on the server
 */
public class NetworkClient {

    //ATTRIBUTES

    /**
     * Identification number of a player(client) on the server
     */
    private final int clientID;

    /**
     * Protocol used from the client to communicate on the network
     */
    private final Protocol protocol;

    /**
     * Reference to the socket used in communication (if unnecessary, put null)
     */
    private final Socket socket;

    private RemoteInterfaceClient remoteInterface;

    /**
     * Updated status of a client connection to implements persistence to disconnections
     */
    private boolean online;

    /**
     * Represents the username used by a client when logged
     */
    private String username;

    /**
     * Indicates if a client is the one that asked for a new lobby
     */
    private boolean creator;

    /**
     * Indicates if the client is currently allowed to send requests
     */
    private boolean active;

    /**
     * Sequentially increasing number ID for client
     */
    private static int clientCounter = 0;

    /**
     * Object to avoid deadlock
     */
    private static final Object lock = new Object();

    private Streams streams;

    private NetworkManagerServer manager;






    //CONSTRUCTOR METHOD

    /**
     * Constructor method for network client
     *
     * @param protocol Protocol used by the client
     * @param socket   Reference to the socket
     */
    public NetworkClient(Protocol protocol, Socket socket, RemoteInterfaceClient remoteInterface, List<NetworkManagerServer> managerContainer) {
        //More than one thread could access to this block of code, but each client must have a different ID
        switch (protocol){
            case TCP -> this.manager = managerContainer.get(1);
            case RMI -> this.manager = managerContainer.get(0);
            case null, default -> throw new RuntimeException();
        }
        synchronized (lock) {
            NetworkClient.clientCounter++;
            this.clientID = NetworkClient.clientCounter;
        }
        this.protocol = protocol;
        this.remoteInterface = remoteInterface;
        this.socket = socket;
        this.online = true;
        this.username = null;
        this.creator = false;
        this.active = false;
    }



    //GETTER METHODS

    /**
     * Getter for clientID
     * @return the identification number
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Getter for the used protocol
     * @return the protocol
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Getter for the socket
     * @return the reference of the used socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Getter for online
     * @return if the client is online
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Getter for username
     * @return the username used to log to the party
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for creator
     * @return if the client is the creator of the party
     */
    public boolean isCreator() {
        return creator;
    }

    /**
     * Getter for active
     * @return true if the client can send a request (the server can accept the request),
     *         false otherwise
     */
    public boolean isActive() {
        return active;
    }

    public RemoteInterfaceClient getRemoteInterface() {
        return remoteInterface;
    }

    public NetworkManagerServer getManager() {
        return manager;
    }

    public Streams getStreams() {
        return streams;
    }

    //SETTER METHODS

    /**
     * Setter for online
     * @param online to set the player online (true) or offline (false)
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     * Setter for active
     * @param active to set if the player is active (true = active, false = passive)
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * Setter for creator
     * @param creator if the client is the creator of a party (if in a Party)
     */
    public void setCreator(boolean creator){
        this.creator = creator;
    }

    /**
     * Setter for username
     * @param username if the client has a username in the game
     */
    public void setUsername(String username) {
        this.username = username;
    }



    public void setStreams(Streams streams) {
        this.streams = streams;
    }



    public void setManager(NetworkManagerServer manager) {
        this.manager = manager;
    }



    public void setRemoteInterface(RemoteInterfaceClient remoteInterface) {
        this.remoteInterface = remoteInterface;
    }
}
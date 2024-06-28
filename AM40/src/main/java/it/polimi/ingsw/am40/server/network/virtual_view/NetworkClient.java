package it.polimi.ingsw.am40.server.network.virtual_view;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.TCP.ClientHandlerTCP;
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

    /**
     * Remote interface for RMI (if unnecessary, put null)
     */
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
    private static final Object lock = new Object();//FIXME IMPORTANTE

    /**
     * Streams used for TCP communications
     */
    private Streams streams;

    /**
     * Manager for this client
     */
    private NetworkManagerServer manager;

    /**
     * Personal client handler for tcp
     */
    private ClientHandlerTCP clienthandler;






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
            case TCP -> {
                this.manager = managerContainer.get(1);
                break;
            }
            case RMI -> {
                this.manager = managerContainer.get(0);
                break;
            }
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
        this.username = " ";
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

    /**
     * Getter for the remote interface for RMI
     * @return the interface as reference
     */
    public RemoteInterfaceClient getRemoteInterface() {
        return remoteInterface;
    }

    /**
     * Getter for the manager class
     * @return the manager as reference
     */
    public NetworkManagerServer getManager() {
        return manager;
    }

    /**
     * Getter for the streams for TCP
     * @return the streams for this client
     */
    public Streams getStreams() {
        return streams;
    }

    /**
     * Getter for the clientHandlerTCP for this client
     * @return the client handler instance as reference
     */
    public ClientHandlerTCP getClientHandler() {
        return clienthandler;
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

    /**
     *  Setter for streams
     * @param streams the streams for this client
     */
    public void setStreams(Streams streams) {
        this.streams = streams;
    }

    /**
     * Setter for manager
     * @param manager the manager class reference
     */
    public void setManager(NetworkManagerServer manager) {
        this.manager = manager;
    }

    /**
     * Setter for the remote interface for this client
     * @param remoteInterface the remote interface for RMI
     */
    public void setRemoteInterface(RemoteInterfaceClient remoteInterface) {
        this.remoteInterface = remoteInterface;
    }

    /**
     * Setter for the client handler
     * @param clienthandler the client handler for tcp
     */
    public void setClientHandler(ClientHandlerTCP clienthandler) {
        this.clienthandler = clienthandler;
    }
}
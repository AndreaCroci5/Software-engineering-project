package it.polimi.ingsw.am40.server.network.virtual_view;

import java.net.Socket;


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
     * Updated status of a client connection to implements persistence to disconnections
     */
    private boolean online;

    /**
     * Represents the username used by a client when logged
     */
    private final String username;

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






    //CONSTRUCTOR METHOD

    /**
     * Constructor method for network client
     *
     * @param username Username of the client
     * @param protocol Protocol used by the client
     * @param socket   Reference to the socket
     */
    NetworkClient(String username, Protocol protocol, Socket socket) {
        //More than one thread could access to this block of code, but each client must have a different ID
        synchronized (lock) {
            NetworkClient.clientCounter++;
            this.clientID = NetworkClient.clientCounter;
        }
        this.protocol = protocol;
        this.socket = socket;
        this.online = true;
        this.username = username;
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

}
package it.polimi.ingsw.am40.server.network;

/**
 * Interface NetworkManagerServer ensures methods to manage the communications on the network and
 * to handle incoming requests from clients
 */
public interface NetworkManagerServer {

    /**
     * Method to start communications with a specific protocol
     */
    public void initCommunication(int port, String hostName);

    /**
     * Method to stop the communication with the clients (server not reachable on the network)
     */
    public void stopCommunication();

    /**
     * Method to init the ping logic in order to know if clients are still connected
     */
    public void initPing();

    /**
     * Method to stop the ping logic
     */
    public void stopPing();
}

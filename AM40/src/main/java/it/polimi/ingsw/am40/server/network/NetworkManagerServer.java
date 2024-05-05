package it.polimi.ingsw.am40.server.network;

/**
 * Interface NetworkManagerServer ensures methods to manage the communications on the network and
 * to handle incoming requests from clients
 */
public interface NetworkManagerServer {

    /**
     * Method to start communications with a specific protocol
     */
    public void initCommunication();
}

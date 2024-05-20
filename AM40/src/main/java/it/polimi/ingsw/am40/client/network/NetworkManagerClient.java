package it.polimi.ingsw.am40.client.network;


import it.polimi.ingsw.am40.client.ConcreteContext;

/**
 * The NetworkManagerInterface defines methods that each class which communicates through
 * the network with a specific protocol must implement
 */
public interface NetworkManagerClient extends ConcreteContext {

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    public void initCommunication(int port, String hostName, String serverAddress);

    /**
     * Getter for the client class reference
     * @return the client as reference
     */
    public Client getClient();

}

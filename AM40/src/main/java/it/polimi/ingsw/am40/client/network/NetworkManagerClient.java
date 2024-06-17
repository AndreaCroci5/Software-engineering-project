package it.polimi.ingsw.am40.client.network;


import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.ConcreteContext;
import it.polimi.ingsw.am40.data.Data;

/**
 * The NetworkManagerInterface defines methods that each class which communicates through
 * the network with a specific protocol must implement
 */
public interface NetworkManagerClient extends ConcreteContext {



    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    void initCommunication() throws NetworkProblemException;

    /**
     * Getter for the client class reference
     * @return the client as reference
     */
    Client getClient();

    void closeCommunication() throws NetworkProblemException;

    String getHostName();
    void setHostName(String hostName);

    void setPort(int port);
    int getPort();

    void setServerAddress(String serverAddress);
    String getServerAddress();

    Protocol getUsedProtocol();

    void send(Message message);
    void receive(Data data);

}


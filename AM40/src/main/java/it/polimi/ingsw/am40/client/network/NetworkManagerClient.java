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

    /**
     * Method to close the communication
     * @throws NetworkProblemException if an error during the connection closing occurs
     */
    void closeCommunication() throws NetworkProblemException;

    /**
     * Getter for the hostName of the client
     * @return the hostname
     */
    String getHostName();

    /**
     * Setter for the hostName of the client
     * @param hostName the hostName to set
     */
    void setHostName(String hostName);

    /**
     * Setter for the used port
     * @param port tho port number
     */
    void setPort(int port);

    /**
     * Getter for the port
     * @return the port number
     */
    int getPort();

    /**
     * Setter for the server address
     * @param serverAddress the IP address of the server
     */
    void setServerAddress(String serverAddress);

    /**
     * Getter for the server address
     * @return the server IP address
     */
    String getServerAddress();

    /**
     * Getter for the Enum which identifies the network protocol used by the manager
     * @return the used protocol
     */
    Protocol getUsedProtocol();

    /**
     *
     * @param message
     */
    //fixme 190624S
    void send(Message message);

    /**
     * Method used to send a serialized message on the network
     * @param message the message to send
     */
    public  void sendSerializedMessage(Data message);

}


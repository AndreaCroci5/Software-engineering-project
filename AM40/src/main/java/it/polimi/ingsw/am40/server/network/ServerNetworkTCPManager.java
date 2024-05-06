package it.polimi.ingsw.am40.server.network;

/**
 * ServerNetworkTCPManager is the class which manage communications (server-side) through TCP Socket protocol
 */
public class ServerNetworkTCPManager implements NetworkManagerServer{

    //ATTRIBUTES

    /**
     * Name of the server host on the network
     */
    private final String hostName;

    /**
     * Port on which server accept incoming connection requests
     */
    private final int portNumber;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for ServerNetworkTCPManager
     * @param port a valid number for the listening port of the server
     * @param hostName a valid hostName
     */
    public ServerNetworkTCPManager(int port, String hostName){
        this.hostName = hostName;
        this.portNumber = port;
    }



    //GETTER METHODS

    /**
     * Getter for portNumber
     * @return the port number of the server
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Getter for hostName
     * @return the host name of the server
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Method to start communications with a specific protocol
     */
    @Override
    public void initCommunication(int port, String hostName) {

    }
}

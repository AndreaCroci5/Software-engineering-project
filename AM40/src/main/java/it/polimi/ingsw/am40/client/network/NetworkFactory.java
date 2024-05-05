package it.polimi.ingsw.am40.client.network;

/**
 * The NetworkFactory class is used to actuate the factory pattern for the network protocol.
 * The user can decide the protocol used to communicate with the server
 */
public class NetworkFactory {

    /**
     * Method to create the concrete network manager class according to user preferences
     * @param protocol A string which define which protocol is used
     * @return the reference to the new concrete network manager class (null if something
     */
    public NetworkManagerClient createConcreteNetworkManager(String protocol) throws NetworkFactoryException{
        if(protocol != null){
            switch(protocol){
                case "TCP":
                    return new ClientNetworkTCPManager();
                case "RMI":
                    return new ClientNetworkRMIManager();
                default:
                    throw new NetworkFactoryException();
            }
        }
        throw new NetworkFactoryException();
    }
}

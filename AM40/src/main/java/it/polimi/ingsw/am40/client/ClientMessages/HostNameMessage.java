package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.HostNameData;

/**
 * This message is sent by the client to let the server know is host name
 */
public class HostNameMessage extends Message{

    /**
     * The hostname of the client
     */
    private final String hostname;

    /**
     * Represents the IP address of a host
     */
    private final String IpAddress;

    /**
     * The port number
     */
    private final int port;

    /**
     * Is the remote interface for RMI
     */
    private final RemoteInterfaceClient skeleton;

    /**
     * Constructor for the HostNameMessage
     * @param nickname is the name of the active client
     * @param ipAddress is the IP address of a host
     * @param port is the port of a host
     * @param skeleton is the remote interface for RMI
     */
    public HostNameMessage(String nickname, String ipAddress, int port, RemoteInterfaceClient skeleton) {
        super("HOST_NAME", nickname);
        this.hostname = nickname;
        this.IpAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new HostNameData(this.hostname,this.IpAddress,this.port,this.skeleton);
    }
}

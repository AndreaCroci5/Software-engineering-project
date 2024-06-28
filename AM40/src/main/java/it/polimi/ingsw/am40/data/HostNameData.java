package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.HostNameAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This data is sent to the server, and it contains the client information
 */
@JsonTypeName("HOST_NAME")
public class HostNameData extends Data{

    /**
     * It's the host name of the client
     */
    private String hostname;
    /**
     * This variable represents the IP address of a client in the network
     */
    private String ipAddress;
    /**
     * Represents the network port number
     */
    private int port;
    /**
     * It represents the remote interface for RMI
     */
    private RemoteInterfaceClient skeleton;

    /**
     * Constructor for HostNameData
     * @param hostname is the host name of the client
     * @param ipAddress represents the IP address of a client in the network
     * @param port represents the network port number
     * @param skeleton represents the remote interface for RMI
     */
    @JsonCreator
    public HostNameData(@JsonProperty("hostname") String hostname,
                        @JsonProperty("ipAddress") String ipAddress,
                        @JsonProperty("port") int port,
                        @JsonProperty("skeleton")RemoteInterfaceClient skeleton) {
        super("HOST_NAME", hostname);
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RemoteInterfaceClient getSkeleton() {
        return skeleton;
    }

    public void setSkeleton(RemoteInterfaceClient skeleton) {
        this.skeleton = skeleton;
    }

    /**
     * This method is called once the Data reaches the Server and creates the Action related to the Data sent by polymorphism
     * @return the corresponding Action on the Server
     */
    @Override
    public Action onServer() {
        return new HostNameAction(this.hostname,-1,this.getPlayerID(),this.hostname,this.ipAddress,this.port,skeleton);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.HostNameActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}

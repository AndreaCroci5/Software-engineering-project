package it.polimi.ingsw.am40.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.HostNameAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("HOST_NAME")
public class HostNameData extends Data{

    private String hostname;
    private String ipAddress;
    private int port;
    private RemoteInterfaceClient skeleton;

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

    @Override
    public Action onServer() {
        return new HostNameAction(this.hostname,-1,this.getPlayerID(),this.hostname,this.ipAddress,this.port,skeleton);
    }

    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.HostNameActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}

package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.JoinRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

@JsonTypeName("JOIN_GAME")
public class JoinRequestData extends Data {

    private String ipAddress;

    private int port;

    private RemoteInterfaceClient skeleton;

    public JoinRequestData(String nickname) {
        super("JOIN_GAME", nickname);
    }

    @JsonCreator
    public JoinRequestData(@JsonProperty("nickname") String nickname,
                           @JsonProperty("ipAddress") String ipAddress,
                           @JsonProperty("port") int port,
                           @JsonProperty("skeleton") RemoteInterfaceClient skeleton) {
        super("JOIN_GAME", nickname);
        this.ipAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
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


    public void setSkeleton(RemoteInterfaceClient skeleton) {
        this.skeleton = skeleton;
    }

    public RemoteInterfaceClient getSkeleton() {
        return skeleton;
    }

    @Override
    public Action onServer(){
        return new JoinRequestAction(this.getNickname(), this.getGameID(), this.getPlayerID());
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.requestJoinActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}

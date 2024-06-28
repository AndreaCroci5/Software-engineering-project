package it.polimi.ingsw.am40.data.active.flow;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.actions.Action;
import it.polimi.ingsw.am40.server.actions.active.setup.CreateRequestAction;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.RemoteException;

/**
 * This class contains the information that will be carried by being sent on the network.
 * This class is also the bridge from CreateRequestMessage on the Client to the CreateRequestAction on Server
 */
@JsonTypeName("CREATE_GAME")
public class CreateRequestData extends Data {

    /**
     * Number representing the party size chosen by the Client (it has to be between 2 and 4)
     */
    private  int numOfPlayers;

    /**
     * Ip address of the Client referring to the Socket
     */
    private String ipAddress;

    /**
     * Port of the Client referring to the Socket
     */
    private int port;

    /**
     * Remote Interface referring to RMI
     */
    private RemoteInterfaceClient skeleton;


    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
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

    //Json constructor
    @JsonCreator
    public CreateRequestData(@JsonProperty("nickname") String nickname,
                             @JsonProperty("num_of_player") int numOfPlayers,
                             @JsonProperty("ipAddress") String ipAddress,
                             @JsonProperty("port") int port,
                             @JsonProperty("skeleton") RemoteInterfaceClient skeleton) {
        super("CREATE_GAME",nickname);
        this.numOfPlayers = numOfPlayers;
        this.ipAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }

    /**
     * Override of the method onServer that returns the related CreateRequestAction on the Server
     * @return a CreateRequestAction
     */
    @Override
    public Action onServer(){
        return new CreateRequestAction(this.getNickname(),this.getGameID(), this.getPlayerID(), this.numOfPlayers);
    }

    /**
     * Method which calls the right RMI interface method for each data (with override)
     * @param skeleton the client remote interface. Null if data active
     * @param stub the server remote interface. Null if data passive
     */
    @Override
    public synchronized void doRMI(RemoteInterfaceClient skeleton, RemoteInterfaceServer stub){
        try {
            stub.requestCreateActiveFlow(this);
        } catch (RemoteException e) {
            System.out.println("RMI call went wrong! message: " + e);
        }
    }
}

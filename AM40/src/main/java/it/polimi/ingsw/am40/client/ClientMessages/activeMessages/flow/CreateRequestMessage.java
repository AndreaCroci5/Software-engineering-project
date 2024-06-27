package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.flow.CreateRequestData;

public class CreateRequestMessage extends Message {

    private final String clientNickname;

    /**
     * It's the number of player that the client wants in the new game he created
     */
    private final int numOfPlayer;

    private final String IpAddress;

    private final int port;

    private final RemoteInterfaceClient skeleton;

    /**
     * Constructor for the CreateMessage
     * This message is the request of the client of creating a new game
     * @param numOfPlayer it's the number of player that the client wants in the new game he created
     */
    public CreateRequestMessage(String nickname, int numOfPlayer, String ipAddress, int port, RemoteInterfaceClient skeleton) {
        super("CREATE_GAME", nickname);
        this.clientNickname = nickname;
        this.numOfPlayer = numOfPlayer;
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
        return new CreateRequestData(this.clientNickname,this.numOfPlayer,this.IpAddress,this.port,this.skeleton);
    }


}


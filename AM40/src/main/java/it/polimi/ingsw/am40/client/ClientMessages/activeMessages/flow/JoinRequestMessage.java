package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.flow.JoinRequestData;

public class JoinRequestMessage extends Message {

    private final String clientNickname;

    private final String IpAddress;

    private final int port;

    private final RemoteInterfaceClient skeleton;

    /**
     * Constructor fot the JoinMessage
     * This message it's the request of the client to join an existing game
     */
    public JoinRequestMessage(String nickname, String ipAddress, int port,RemoteInterfaceClient skeleton) {
        super("JOIN_GAME",nickname);
        this.clientNickname = nickname;
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
        return new JoinRequestData(this.clientNickname,this.IpAddress,this.port,this.skeleton);
    }
}

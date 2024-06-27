package it.polimi.ingsw.am40.client.ClientMessages;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.HostNameData;

public class HostNameMessage extends Message{

    private final String hostname;

    private final String IpAddress;

    private final int port;

    private final RemoteInterfaceClient skeleton;

    public HostNameMessage(String nickname, String ipAddress, int port, RemoteInterfaceClient skeleton) {
        super("HOST_NAME", nickname);
        this.hostname = nickname;
        this.IpAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }

    public Data messageToData() {
        return new HostNameData(this.hostname,this.IpAddress,this.port,this.skeleton);
    }
}

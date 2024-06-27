package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.FailedHostNameData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

public class HostNameAction extends Action{

    private String hostName;

    private String ipAddress;

    private int port;

    private RemoteInterfaceClient skeleton;


    public HostNameAction(String nickname, int gameID, int playerID, String hostName, String ipAddress, int port, RemoteInterfaceClient skeleton) {
        super("HOST_NAME", nickname, gameID, playerID);
        this.hostName = hostName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }

    public void doAction(ActionAgent agent) {
        VVServer server = (VVServer) agent;
        for (String s : server.getClientsHostNames()) {
            if (hostName.equals(s)) {
                server.sendOnNetworkUnicast(this.getPlayerID(),new FailedHostNameData(this.hostName));
                return;
            }
        }
        server.getClientsHostNames().add(this.hostName);
    }
}

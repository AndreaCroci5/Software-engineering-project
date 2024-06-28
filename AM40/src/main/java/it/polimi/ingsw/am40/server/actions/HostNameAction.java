package it.polimi.ingsw.am40.server.actions;

import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.FailedHostNameData;
import it.polimi.ingsw.am40.server.ActionAgent;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

/**
 * This class represents the Action made by the server just after a client is connected in order to set his username,
 * by checking his identity by the ipAddress and the port or the skeleton, depending on which mode he is connected
 * tah
 */
public class HostNameAction extends Action{

    /**
     * Hostname of the Client
     */
    private String hostName;

    /**
     * Ip address of the client
     */
    private String ipAddress;

    /**
     * Port of the Client
     */
    private int port;

    /**
     * Skeleton of the remote interface
     */
    private RemoteInterfaceClient skeleton;


    public HostNameAction(String nickname, int gameID, int playerID, String hostName, String ipAddress, int port, RemoteInterfaceClient skeleton) {
        super("HOST_NAME", nickname, gameID, playerID);
        this.hostName = hostName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.skeleton = skeleton;
    }


    /**
     * Override of doAction for HostNameAction
     * @param agent is the VVServer from which the Action is performed and transformed into a Data and sent
     */
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

package it.polimi.ingsw.am40.server.network.TCP;

import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;

import java.io.PrintWriter;
import java.util.Scanner;

//todo doc
public class ClientHandlerTCP implements Runnable{


    private ServerNetworkTCPManager TCPManager;
    private int clientID;




    public ClientHandlerTCP(ServerNetworkTCPManager serverNetworkTCPManager, int clientID) {
        this.clientID = clientID;
        this.TCPManager = serverNetworkTCPManager;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {

        PrintWriter out = null;
        Scanner in = null;
        try {
            out = this.TCPManager.getMainServerClass().getClientByID(this.clientID).getStreams().getOut();
            in = this.TCPManager.getMainServerClass().getClientByID(this.clientID).getStreams().getIn();
        } catch (NonExistentClientException e) {
            System.out.println("Client doesn't exist!");
            throw new RuntimeException(e);
        }
        String line;

        while (true) {
            line = in.nextLine();;
            this.getTCPManager().handleJSONMessage(line);
            out.flush();
        }

    }

    public ServerNetworkTCPManager getTCPManager() {
        return TCPManager;
    }

}

package it.polimi.ingsw.am40.server.network.TCP;

import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class which implements the running listening logic for a specific client TCP
 */
public class ClientHandlerTCP implements Runnable{

    //ATTRIBUTES

    /**
     * Manager TCP class
     */
    private ServerNetworkTCPManager TCPManager;

    /**
     * Id number for the client
     */
    private int clientID;

    /**
     * Boolean which indicates if the handler is running for a specific client
     */
    private boolean running;





    //CONSTRUCTOR METHOD

    /**
     * Constructor
     * @param serverNetworkTCPManager the manager class
     * @param clientID the client ID
     */
    public ClientHandlerTCP(ServerNetworkTCPManager serverNetworkTCPManager, int clientID) {
        this.clientID = clientID;
        this.TCPManager = serverNetworkTCPManager;
        this.running = true;
    }


    //RUNNING METHOD

    /**
     * Runs this operation.
     * Here the client handler listens to a socket for incoming messages
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

        while (this.running == true) {
            line = in.nextLine();

                try {
                    NetworkClient c = this.TCPManager.getMainServerClass().getClientByID(clientID);
                    if(line == null || c.getSocket().isClosed()){
                        this.TCPManager.disconnectedClientNotification(c);
                        break;
                    }
                } catch (NonExistentClientException e) {
                    System.out.println("Client doesn't exist!");
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    System.out.println("Problem with the disconnection");
                    throw new RuntimeException(e);
                }


            this.getTCPManager().handleJSONMessage(line);
            out.flush();
        }

    }

    //GETTER AND SETTERS

    /**
     * Getter for the TCP manager
     * @return the manager as reference
     */
    public ServerNetworkTCPManager getTCPManager() {
        return TCPManager;
    }

    /**
     * Getter for running attribute
     * @return true if the client handler is running, false otherwise
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Setter for running attribute
     * @param running true if the client handler is running, false otherwise
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}

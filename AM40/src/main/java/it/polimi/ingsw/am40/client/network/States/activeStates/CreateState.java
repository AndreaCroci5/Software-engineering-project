package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.CreateRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.ClientNetworkTCPManager;
import it.polimi.ingsw.am40.client.network.RMI.ClientNetworkRMIManager;
import it.polimi.ingsw.am40.client.network.RMI.RemoteObjectClient;
import it.polimi.ingsw.am40.client.network.State;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.RemoteObject;

public class CreateState implements State {

    /**
     * In this state the client has to choose how many players he wants in the new game that is going to be created
     * @param context is the context of the client with his view and his network communication protocol
     */
    @Override
    public void execute(Client context) {
        context.getViewManager().displayCreate();
    }

    /**
     * In this state client must insert a number that must be 2,3,4
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    @Override
    public void checkInput(Client context, String input) {

        String ipAddress = null;
        int port = -1;
        ClientNetworkRMIManager rmi = null;
        ClientNetworkTCPManager tcp = null;
        switch(context.getNetworkManager().getUsedProtocol()){
            case TCP:
                tcp = (ClientNetworkTCPManager) context.getNetworkManager();
                ipAddress = tcp.getSocket().getLocalAddress().getHostAddress();
                port = tcp.getSocket().getLocalPort();

                try {
                    Integer.parseInt(input);
                    if (Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3 || Integer.parseInt(input) == 4) {
                        context.getNetworkManager().send(new CreateRequestMessage(context.getNickname(),Integer.parseInt(input),ipAddress,port,null));
                    }
                    else {
                        System.out.println(">Players must be 2,3 or 4");
                    }
                }catch (NumberFormatException e) {
                    System.out.println(">Input must be a number ");
                }
                break;
            case RMI:
                rmi = (ClientNetworkRMIManager) context.getNetworkManager();
                try {
                    InetAddress localhost = InetAddress.getLocalHost();
                    ipAddress = localhost.getHostAddress();
                } catch (UnknownHostException e) {
                    System.out.println("Error with the IP address");
                    throw new RuntimeException(e);
                }

                try {
                    Integer.parseInt(input);
                    if (Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3 || Integer.parseInt(input) == 4) {
                        context.getNetworkManager().send(new CreateRequestMessage(context.getNickname(),Integer.parseInt(input),ipAddress,port,rmi.getSkeleton()));
                    }
                    else {
                        System.out.println(">Players must be 2,3 or 4");
                    }
                }catch (NumberFormatException e) {
                    System.out.println(">Input must be a number ");
                }
                break;


            default:
                System.out.println("Error with the network manager protocol getting");
                throw new RuntimeException();
        }



    }
}

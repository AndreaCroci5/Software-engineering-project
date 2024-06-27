package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.JoinRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.ClientNetworkTCPManager;
import it.polimi.ingsw.am40.client.network.RMI.ClientNetworkRMIManager;
import it.polimi.ingsw.am40.client.network.State;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SetUpState implements State {

    /**
     * In the set-up state the client decide if joining an existing game or create a new one
     * The message send back will be a CreateMessage or a JoinMessage
     * @param context is the context of the client with his view and network choices
     */
    @Override
    public void execute(Client context) {
        // ask the user to create or join a game
        context.getViewManager().displaySetUp();
    }

    /**
     * In this state input must be only join or create
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

                if (!input.equalsIgnoreCase("join") && !input.equalsIgnoreCase("create")) {
                    System.out.println(">You must insert 'join' or 'create' ");
                }
                else if (input.equalsIgnoreCase("join")) {
                    context.getNetworkManager().send(new JoinRequestMessage(context.getNickname(), ipAddress, port, null));
                }
                else if (input.equalsIgnoreCase("create")) {
                    context.setState(new CreateState());
                    context.getCurrentState().execute(context);
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
                if (!input.equalsIgnoreCase("join") && !input.equalsIgnoreCase("create")) {
                    System.out.println(">Wrong input");
                }
                else if (input.equalsIgnoreCase("join")) {
                    context.getNetworkManager().send(new JoinRequestMessage(context.getNickname(), ipAddress, port, rmi.getSkeleton()));
                }
                else if (input.equalsIgnoreCase("create")) {
                    context.setState(new CreateState());
                    context.getCurrentState().execute(context);
                }

                break;
            default:
                System.out.println("Error with the network manager protocol getting");
                throw new RuntimeException();
        }

       // NetworkManagerClient nm = context.getNetworkManager();
       // String ipAddress = nm.getSocket().getLocalAddress().getHostAddress();



    }
}

package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.JoinRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.ClientNetworkTCPManager;
import it.polimi.ingsw.am40.client.network.State;

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
        ClientNetworkTCPManager tcp = (ClientNetworkTCPManager) context.getNetworkManager(); //FIXME
        String ipAddress = tcp.getSocket().getLocalAddress().getHostAddress(); //FIXME
        int port = tcp.getSocket().getLocalPort(); //FIXME
        if (!input.equalsIgnoreCase("join") && !input.equalsIgnoreCase("create")) {
            System.out.println(">You must insert 'join' or 'create' ");
        }
        else if (input.equalsIgnoreCase("join")) {
            context.getNetworkManager().send(new JoinRequestMessage(context.getNickname(),ipAddress,port));
        }
        else if (input.equalsIgnoreCase("create")) {
            context.setState(new CreateState());
            context.getCurrentState().execute(context);
        }
    }
}

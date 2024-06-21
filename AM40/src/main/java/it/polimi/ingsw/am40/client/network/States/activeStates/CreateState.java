package it.polimi.ingsw.am40.client.network.States.activeStates;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.CreateRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.ClientNetworkTCPManager;
import it.polimi.ingsw.am40.client.network.State;

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

        ClientNetworkTCPManager tcp = (ClientNetworkTCPManager) context.getNetworkManager(); //FIXME
        String ipAddress = tcp.getSocket().getLocalAddress().getHostAddress(); //FIXME
        int port = tcp.getSocket().getLocalPort(); //FIXME
        try {
            Integer.parseInt(input);
            if (Integer.parseInt(input) == 2 || Integer.parseInt(input) == 3 || Integer.parseInt(input) == 4) {
                context.getNetworkManager().send(new CreateRequestMessage(context.getNickname(),Integer.parseInt(input),ipAddress,port));
            }
            else {
                System.out.println(">Players must be 2,3 or 4");
            }
        }catch (NumberFormatException e) {
            System.out.println(">Input must be a number ");
        }
    }
}

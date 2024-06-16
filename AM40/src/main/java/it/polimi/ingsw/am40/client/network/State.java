package it.polimi.ingsw.am40.client.network;

/**
 * The State interface is useful for the State Pattern
 */
public interface State {

    /**
     * It executes the instruction of a state
     * @param context is the context of the client with his view and his network communication protocol
     */
    void execute(Client context);

    /**
     * Thi method is used to check the input of the client based on the state where he is
     * @param context is the context of the client with his view and his network communication protocol
     * @param input is the input of the client
     */
    void checkInput(Client context, String input);

}

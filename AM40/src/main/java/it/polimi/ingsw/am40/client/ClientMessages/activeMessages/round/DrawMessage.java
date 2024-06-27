package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.round.DrawData;

/**
 * This message contains all the parameters that the model need in the draw phase
 */
public class DrawMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * It's the choice between golden and resource card
     * It could be res or gold
     */
    private final String choice;

    /**
     * It's the choice between picking from the deck or from the plates
     * It could be 0 (first card on the plate) or
     * 1 (second card of the plate) or
     * 2 (deck)
     */
    private final int selection;

    /**
     * Constructor for the DrawMessage
     * @param nickname is the name of the active client
     * @param choice it's the choice between golden and resource card
     *               It could be res or gold
     * @param selection it's the choice between picking from the deck or from the plates
     *                  It could be 0 (first card on the plate) or
     *                  1 (second card of the plate) or
     *                  2 (deck)
     */
    public DrawMessage(String nickname, String choice, int selection) {
        super("DRAW",nickname);
        this.clientNickname = nickname;
        this.choice = choice;
        this.selection = selection;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new DrawData(this.clientNickname,this.choice,this.selection);
    }

}


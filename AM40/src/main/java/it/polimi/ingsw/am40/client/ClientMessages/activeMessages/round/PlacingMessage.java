package it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.active.round.PlacingData;
import it.polimi.ingsw.am40.server.model.Coordinates;

/**
 * This message has all the parameters needed for the placing phase
 */
public class PlacingMessage extends Message {

    /**
     * It's the name of the active client
     */
    private final String clientNickname;

    /**
     * This is the ID of the card that the client want to place
     * between the three cards that he has in his hand
     */
    private final int handCard;

    /**
     * This represents the place where the client wants to place the card
     */
    private final Coordinates coordsToPlace;

    /**
     * This is the face of the card that is going to be placed
     * It could be front or back
     */
    private final String cardFace;

    /**
     * Constructor for PlacingMessage
     * @param nickname is the name of the active client
     * @param handCard is the ID of the card that the client wants to place
     *                 between the three cards that he has in his hand
     * @param coordsToPlace represents the place where the client want to place the card
     * @param cardFace is the face of the card that is going to be placed
     *                 it could be front or back
     */
    public PlacingMessage(String nickname, int handCard, Coordinates coordsToPlace, String cardFace) {
        super("PLACING",nickname);
        this.clientNickname = nickname;
        this.handCard = handCard;
        this.coordsToPlace = coordsToPlace;
        this.cardFace = cardFace;
    }

    /**
     * This method is used to convert the internal message of the client in
     * a data that is the object that is going through the network
     * @return the data that is going to the network
     */
    public Data messageToData() {
        return new PlacingData(this.clientNickname,this.handCard, this.coordsToPlace, this.cardFace);
    }

}


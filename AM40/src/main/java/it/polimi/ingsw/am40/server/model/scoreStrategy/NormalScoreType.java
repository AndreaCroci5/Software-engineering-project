package it.polimi.ingsw.am40.server.model.scoreStrategy;

import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.EdgeState;

import java.util.ArrayList;
import java.util.Map;

/**
 * The NormalScoreType class implements the method calculate() declared in the ScoreTypeInterface
 * as a member of the Strategy Pattern.
 * This class represents the case where a GoldResourceCard does not have a multiplier of the score
 * and the case of a ResourceCard.
 */
public class NormalScoreType implements ScoreType{
    //INTERFACE METHODS IMPLEMENTATION

    /**
     * Strategy interface method implementation
     * It implements the case where a GoldResourceCard doesn't have a multiplier and the standard case of a ResourceCard
     *
     * @param cardCorners      is the state of the corners once the card is placed
     * @param elementsCounter  to get the amount of each CardElements in a Player's PrivateBoard cardGrid
     * @param scorePoints      is the number of the points shown on a card
     * @param objectMultiplier represents the object shown on a card in case of an ObjectScoreType
     * @return the number of points shown on a card as an int
     */
    public int calculate(ArrayList<EdgeState> cardCorners, Map<CardElements, Integer> elementsCounter, int scorePoints, CardElements objectMultiplier) {
        return scorePoints;
    }
}

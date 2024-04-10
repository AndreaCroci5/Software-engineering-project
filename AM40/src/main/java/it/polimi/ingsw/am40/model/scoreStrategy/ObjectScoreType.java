package it.polimi.ingsw.am40.model.scoreStrategy;


import it.polimi.ingsw.am40.model.CardElements;
import it.polimi.ingsw.am40.model.EdgeState;

import java.util.ArrayList;
import java.util.Map;

/**
 * The ObjectScoreType class implements the method calculate() declared in the ScoreTypeInterface
 * as a member of the Strategy Pattern.
 * This class represents the case where the multiplier on a GoldResourceCard is based on the number of times that the
 * Object shown on the card is present in a Player's personal CardGrid after the card placing phase.
 */
public class ObjectScoreType implements ScoreType{
    //INTERFACE METHODS IMPLEMENTATION

    /**
     * Strategy interface method implementation
     * It implements the case where a GoldResourceCard has a score increasing multiplier based on
     * how many occurrences of the object shown are present after the placing phase
     *
     * @param cardCorners      is the state of the corners once the card is placed
     * @param elementsCounter  to get the amount of each CardElements in a Player's PrivateBoard cardGrid
     * @param scorePoints      is the number of the points shown on a card
     * @param objectMultiplier represents the object shown on a card in case of an ObjectScoreType
     * @return as an int, the score shown on a GoldResourceCard multiplied by number of times an element shown
     *         on the multiplier is present on the CardGrid
     */
    public int calculate(ArrayList<EdgeState> cardCorners, Map<CardElements, Integer> elementsCounter, int scorePoints, CardElements objectMultiplier) {
        return scorePoints*elementsCounter.get(objectMultiplier);
    }
}

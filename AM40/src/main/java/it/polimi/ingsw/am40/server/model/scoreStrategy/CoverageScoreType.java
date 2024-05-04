package it.polimi.ingsw.am40.server.model.scoreStrategy;

import it.polimi.ingsw.am40.server.model.CardElements;
import it.polimi.ingsw.am40.server.model.EdgeState;

import java.util.ArrayList;
import java.util.Map;

/**
 * The CoverageScoreType class implements the method calculate() declared in the ScoreTypeInterface
 * as a member of the Strategy Pattern.
 * This class represents the case where the multiplier on a GoldResourceCard is based on
 * how many corners on other Cards present in the cardGrid are covered by the same GoldResourceCard after being placed.
 */
public class CoverageScoreType implements ScoreType{
    //INTERFACE METHODS IMPLEMENTATION

    /**
     * Strategy interface method implementation
     * It implements the case where a GoldResourceCard has a score increasing multiplier based on
     * how many corners are set to taken during the placing phase
     *
     * @param cardCorners      is the state of the corners once the card is placed
     * @param elementsCounter  to get the amount of each CardElements in a Player's PrivateBoard cardGrid
     * @param scorePoints      is the number of the points shown on a card
     * @param objectMultiplier represents the object shown on a card in case of an ObjectScoreType
     * @return the score shown on a GoldResourceCard multiplied by the number of TAKEN corners as an int
     */
    public int calculate(ArrayList<EdgeState> cardCorners, Map<CardElements, Integer> elementsCounter, int scorePoints, CardElements objectMultiplier) {
        int counter = 0;
        for (EdgeState cardCorner: cardCorners){
            if (cardCorner == EdgeState.TAKEN) counter++;
        }
        return scorePoints*counter;
    }
}

package it.polimi.ingsw.am40.model.scoreStrategy;

import it.polimi.ingsw.am40.model.CardElements;
import it.polimi.ingsw.am40.model.EdgeState;

import java.util.ArrayList;
import java.util.Map;

/**
 * The ScoreType class is the entry point for the Strategy Pattern ued for the ResourceCard and the GoldResourceCards.
 * The existence of this class serves as a mean to return the score increased by a multiplier to a player,
 * after a Card is placed by using the calculateScore() method.
 * This class is mainly used during the declaration of the static type of the related attribute in the ResourceCard class,
 * in order to guarantee the dynamic polymorphism at runtime.
 */
public interface ScoreType {
    //INTERFACE METHODS
    /**
     * Interface method that calculates the effective score that a ResourceCard or a GoldResourceCard has after the card placing phase
     *
     * @param cardCorners      is the state of the corners once the card is placed
     * @param elementsCounter  to get the amount of each CardElements in a Player's PrivateBoard cardGrid
     * @param scorePoints      is the number of the points shown on a card
     * @param objectMultiplier represents the object shown on a card in case of an ObjectScoreType
     * @return the real score increase of a card placed
     */
    public int calculate(ArrayList<EdgeState> cardCorners, Map<CardElements, Integer> elementsCounter, int scorePoints, CardElements objectMultiplier);
}

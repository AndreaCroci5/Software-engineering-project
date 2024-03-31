package it.polimi.ingsw.am40.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class CommonBoard {
    /**
     * Deck of aim cards
     */
    private Deck<AimCard> AimDeck;
    /**
     * Deck of resource cards
     */
    private Deck<ResourceCard> ResourceDeck;
    /**
     * Deck of golden resource cards
     */
    private Deck<GoldenResourceCard> GoldenResourceDeck;
    /**
     * Deck of starting cards
     */
    private Deck<StartingCard> StartingDeck;
    /**
     * This is an ArrayList that contains the two resource cards on the plate
     */
    private ArrayList<ResourceCard> PlateResourceCard;
    /**
     * This is an ArrayList that contains the two golden resource cards on the plate
     */
    private ArrayList<GoldenResourceCard> PlateGoldenResourceCard;
    /**
     * This is an ArrayList that contains the two aim cards on the plate
     */
    private ArrayList<AimCard> PlateAimCard;

    // Constructor TO DO

    /**
     * Getter method for the AimCard deck
     * @return a reference to AimCardDeck
     */
    public Deck<AimCard> getAimDeck() {
        return AimDeck;
    }

    /**
     * Getter method for the ResourceCard deck
     * @return a reference to ResourceCardDeck
     */
    public Deck<ResourceCard> getResourceDeck() {
        return ResourceDeck;
    }

    /**
     * Getter method for GoldenResourceCard deck
     * @return a reference to GoldenResourceDeck
     */
    public Deck<GoldenResourceCard> getGoldenResourceDeck() {
        return GoldenResourceDeck;
    }

    /**
     * Getter method for StartingCard deck
     * @return a reference to StartingDeck
     */
    public Deck<StartingCard> getStartingDeck() {
        return StartingDeck;
    }

    /**
     * Getter method for the two resource cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<ResourceCard> getPlateResourceCard() {
        return PlateResourceCard;
    }

    /**
     * Getter method for the two golden resource cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<GoldenResourceCard> getPlateGoldenResourceCard() {
        return PlateGoldenResourceCard;
    }

    /**
     * Getter method for the two aim cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<AimCard> getPlateAimCard() {
        return PlateAimCard;
    }

    // TO DO
    public void iniCommonBoard() {
    }

    /**
     * This method checks if both the decks are empty
     * @return true, if they are. False, otherwise
     */
    public boolean deckEmptinessCheck() {
        return getResourceDeck().isEmpty() && getGoldenResourceDeck().isEmpty();
    }

    /**
     * This method get the card from the ResourcePlate
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    private ResourceCard pickFromResourcePlate(int selection) {
        return PlateResourceCard.get(selection);
    }

    /**
     * This method get the card from the GoldenPlate
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    private GoldenResourceCard pickFromGoldenPlate(int selection) {
        return PlateGoldenResourceCard.get(selection);
    }

    /**
     * This method get the card from the ResourceDeck
     * @return the first card of the deck
     */
    private ResourceCard pickFromResourceDeck() {
        return ResourceDeck.pickFromTop();
    }

    /**
     * This method get the card from the GoldenDeck
     * @return the first card of the deck
     */
    private GoldenResourceCard pickFromGoldenDeck() {
        return GoldenResourceDeck.pickFromTop();
    }

    /**
     * This method replace the card drawn from the plate with the first card of the resource deck, set the element null if the deck is empty
     * @param selection is the index of the card drawn from the plate
     */
    private void addCartToResourcePlate(int selection) {
        if(ResourceDeck.isEmpty()) {
            PlateResourceCard.set(selection,null);
            return;
        }
        PlateResourceCard.set(selection,ResourceDeck.pickFromTop());
    }

    /**
     * This method replace the card drawn from the plate with the first card of the golden deck, set the element null if the deck is empty
     * @param selection is the index of the card drawn
     */
    private void addCartToGoldenPlate(int selection) {
        if(GoldenResourceDeck.isEmpty()) {
            PlateGoldenResourceCard.set(selection, null);
            return;
        }
        PlateGoldenResourceCard.set(selection, GoldenResourceDeck.pickFromTop());
    }
}

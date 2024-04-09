package it.polimi.ingsw.am40.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class CommonBoard {
    /**
     * Deck of aim cards
     */
    private Deck<AimCard> aimDeck;

    /**
     * Deck of resource cards
     */
    private Deck<ResourceCard> resourceDeck;

    /**
     * Deck of golden resource cards
     */
    private Deck<GoldResourceCard> goldenResourceDeck;

    /**
     * Deck of starting cards
     */
    private Deck<StartingCard> startingDeck;

    /**
     * This is an ArrayList that contains the two resource cards on the plate
     */
    private ArrayList<ResourceCard> plateResourceCard;

    /**
     * This is an ArrayList that contains the two golden resource cards on the plate
     */
    private ArrayList<GoldResourceCard> plateGoldenResourceCard;

    /**
     * This is an ArrayList that contains the two aim cards on the plate
     */
    private ArrayList<AimCard> plateAimCard;

    // Constructor TO DO


    // Getter methods

    /**
     * Getter method for the AimCard deck
     * @return a reference to AimCardDeck
     */
    public Deck<AimCard> getAimDeck() {
        return aimDeck;
    }

    /**
     * Getter method for the ResourceCard deck
     * @return a reference to ResourceCardDeck
     */
    public Deck<ResourceCard> getResourceDeck() {
        return resourceDeck;
    }

    /**
     * Getter method for GoldenResourceCard deck
     * @return a reference to GoldenResourceDeck
     */
    public Deck<GoldResourceCard> getGoldenResourceDeck() {
        return goldenResourceDeck;
    }

    /**
     * Getter method for StartingCard deck
     * @return a reference to StartingDeck
     */
    public Deck<StartingCard> getStartingDeck() {
        return startingDeck;
    }

    /**
     * Getter method for the two resource cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<ResourceCard> getPlateResourceCard() {
        return plateResourceCard;
    }

    /**
     * Getter method for the two golden resource cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<GoldResourceCard> getPlateGoldenResourceCard() {
        return plateGoldenResourceCard;
    }

    /**
     * Getter method for the two aim cards on the plate
     * @return a reference to an ArrayList containing this two cards
     */
    public ArrayList<AimCard> getPlateAimCard() {
        return plateAimCard;
    }


    // Public methods

    /**
     * Initialization of the CommonBoard by loading the decks, shuffle them and adding the cards on the plates
     */
    public void iniCommonBoard() {
        // Resource deck
        resourceDeck.iniResourceDeck();
        resourceDeck.shuffle();

        // Aim deck
        aimDeck.iniAimDeck();
        aimDeck.shuffle();

        // Gold deck
        goldenResourceDeck.iniGoldenResourceDeck();
        goldenResourceDeck.shuffle();

        // Starting deck
        startingDeck.iniStartingDeck();
        startingDeck.shuffle();

        // Adding the two cards on the plates
        plateResourceCard.addFirst(resourceDeck.pickFromTop());
        plateResourceCard.add(1,resourceDeck.pickFromTop());

        plateGoldenResourceCard.addFirst(goldenResourceDeck.pickFromTop());
        plateGoldenResourceCard.add(1,goldenResourceDeck.pickFromTop());

        plateAimCard.addFirst(aimDeck.pickFromTop());
        plateAimCard.add(1,aimDeck.pickFromTop());
    }

    /**
     * This method checks if both the decks are empty
     * @return true, if they are. False, otherwise
     */
    public boolean deckEmptinessCheck() {
        return resourceDeck.isEmpty() && goldenResourceDeck.isEmpty();
    }

    /**
     * This method get the card from the ResourcePlate
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public ResourceCard pickFromResourcePlate(int selection) {
        return plateResourceCard.get(selection);
    }

    /**
     * This method get the card from the GoldenPlate
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public GoldResourceCard pickFromGoldenPlate(int selection) {
        return plateGoldenResourceCard.get(selection);
    }

    /**
     * This method replace the card drawn from the plate with the first card of the resource deck, set the element null if the deck is empty
     * @param selection is the index of the card drawn from the plate
     */
    public void addCartToResourcePlate(int selection) {
        if(resourceDeck.isEmpty()) {
            plateResourceCard.set(selection,null);
            return;
        }
        plateResourceCard.set(selection,resourceDeck.pickFromTop());
    }

    /**
     * This method replace the card drawn from the plate with the first card of the golden deck, set the element null if the deck is empty
     * @param selection is the index of the card drawn
     */
    public void addCartToGoldenPlate(int selection) {
        if(goldenResourceDeck.isEmpty()) {
            plateGoldenResourceCard.set(selection, null);
            return;
        }
        plateGoldenResourceCard.set(selection, goldenResourceDeck.pickFromTop());
    }
}

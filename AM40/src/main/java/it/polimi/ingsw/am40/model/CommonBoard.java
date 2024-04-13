package it.polimi.ingsw.am40.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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


    // Constructor

    /**
     * First constructor for common board.
     * It gets nothing in input, and it creates new empty decks and empty plates
     */
    public CommonBoard(){
        this.resourceDeck = new Deck<>();
        this.goldenResourceDeck = new Deck<>();
        this.startingDeck = new Deck<>();
        this.aimDeck = new Deck<>();

        this.plateResourceCard = new ArrayList<>();
        this.plateGoldenResourceCard = new ArrayList<>();
        this.plateAimCard = new ArrayList<>();
    }

    /**
     * Second constructor for common board.
     * It gets a list of four decks ((1st) resource, (2nd) gold, (3rd) starting, (4th) aim) and creates empty plates
     * @param decks
     */
    public CommonBoard(List<Deck<? extends Card>> decks){
        this.resourceDeck = (Deck<ResourceCard>) decks.get(0);
        this.goldenResourceDeck = (Deck<GoldResourceCard>) decks.get(1);
        this.startingDeck = (Deck<StartingCard>) decks.get(2);
        this.aimDeck = (Deck<AimCard>) decks.get(3);

        this.plateResourceCard = new ArrayList<>();
        this.plateGoldenResourceCard = new ArrayList<>();
        this.plateAimCard = new ArrayList<>();
    }

    /**
     * Third constructor for common board.
     * It gets the four decks in input and creates empty decks
     * Requirements: decks passed as argument cannot be null
     * @param resDeck Deck of resource cards
     * @param goldResDeck Deck of golden resource cards
     * @param startDeck Deck of starting cards
     * @param aimDeck Deck of aim cards
     */
    public CommonBoard(Deck<ResourceCard> resDeck, Deck<GoldResourceCard> goldResDeck, Deck<StartingCard> startDeck, Deck<AimCard> aimDeck){
        if (resDeck == null || goldResDeck == null || startDeck == null || aimDeck == null) {
            throw new IllegalArgumentException("Decks cannot be null");
        }

        this.resourceDeck = resDeck;
        this.goldenResourceDeck = goldResDeck;
        this.startingDeck = startDeck;
        this.aimDeck = aimDeck;

        this.plateResourceCard = new ArrayList<>();
        this.plateGoldenResourceCard = new ArrayList<>();
        this.plateAimCard = new ArrayList<>();
    }


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
     * Requirements: decks and plates should be already initialised
     */
    public void iniCommonBoard() {

        if (resourceDeck == null || goldenResourceDeck == null || startingDeck == null || aimDeck == null) {
            throw new IllegalStateException("Decks not initialized");
        }

        if(plateResourceCard == null || plateGoldenResourceCard == null || plateAimCard == null) {
            throw new IllegalStateException("Plate not initialised");
        }

        if (resourceDeck.isEmpty() || goldenResourceDeck.isEmpty() || startingDeck.isEmpty() || aimDeck.isEmpty()){
            throw new IllegalStateException("One or more decks are empty");
        }

        resourceDeck.shuffle();
        goldenResourceDeck.shuffle();
        aimDeck.shuffle();
        startingDeck.shuffle();

        // Adding the two cards on the plates
        plateResourceCard.addFirst(resourceDeck.pickFromTop());
        plateResourceCard.addLast(resourceDeck.pickFromTop());

        plateGoldenResourceCard.addFirst(goldenResourceDeck.pickFromTop());
        plateGoldenResourceCard.addLast(goldenResourceDeck.pickFromTop());

        plateAimCard.addFirst(aimDeck.pickFromTop());
        plateAimCard.addLast(aimDeck.pickFromTop());
    }

    /**
     * This method checks if both the resource deck and the golden deck are empty
     * @return true, if they are. False, otherwise
     */
    public boolean deckEmptinessCheck() {
        return (resourceDeck == null || resourceDeck.isEmpty()) && (goldenResourceDeck == null || goldenResourceDeck.isEmpty());
    }

    /**
     * This method get the card from the ResourcePlate
     * Requirements: selection can only have the values 0,1
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public ResourceCard pickFromResourcePlate(int selection) {
        if(selection < 0 || selection >= plateResourceCard.size()) {
            throw new IndexOutOfBoundsException("Selection index is out of bounds");
        }
        return plateResourceCard.get(selection);
    }

    /**
     * This method get the card from the GoldenPlate
     * Requirements: selection can only have the values 0,1
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public GoldResourceCard pickFromGoldenPlate(int selection) {
        if(selection < 0 || selection >= plateGoldenResourceCard.size()) {
            throw new IndexOutOfBoundsException("Selection index is out of bounds");
        }
        return plateGoldenResourceCard.get(selection);
    }

    /**
     * This method replace the card drawn from the plate with the first card of the resource deck, set the element null if the deck is empty
     * Requirements: selection can only have the values 0,1
     * @param selection is the index of the card drawn from the plate
     */
    public void addCartToResourcePlate(int selection) {
        if(selection < 0 || selection >= plateResourceCard.size()) {
            throw new IndexOutOfBoundsException("Selection index is out of bounds");
        }
        if(resourceDeck.isEmpty()) {
            plateResourceCard.set(selection,null);
            return;
        }
        plateResourceCard.set(selection,resourceDeck.pickFromTop());
    }

    /**
     * This method replace the card drawn from the plate with the first card of the golden deck, set the element null if the deck is empty
     * Requirements: selection can only have the values 0,1
     * @param selection is the index of the card drawn
     */
    public void addCartToGoldenPlate(int selection) {
        if(selection < 0 || selection >= plateGoldenResourceCard.size()) {
            throw new IndexOutOfBoundsException("Selection index is out of bounds");
        }
        if(goldenResourceDeck.isEmpty()) {
            plateGoldenResourceCard.set(selection, null);
            return;
        }
        plateGoldenResourceCard.set(selection, goldenResourceDeck.pickFromTop());
    }
}

package it.polimi.ingsw.am40.server.model;

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
     * This is a static array that contains the two resource cards on the plate
     */
    public static ResourceCard[] plateResourceCard;

    /**
     * This is a static array that contains the two golden resource cards on the plate
     */
    public static GoldResourceCard[] plateGoldenResourceCard;

    /**
     * This is a static array that contains the two aim cards on the plate
     */
    public static AimCard[] plateAimCard;


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

        plateResourceCard = new ResourceCard[2];
        plateGoldenResourceCard = new GoldResourceCard[2];
        plateAimCard = new AimCard[2];
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

        plateResourceCard = new ResourceCard[2];
        plateGoldenResourceCard = new GoldResourceCard[2];
        plateAimCard = new AimCard[2];
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

        this.resourceDeck = resDeck;
        this.goldenResourceDeck = goldResDeck;
        this.startingDeck = startDeck;
        this.aimDeck = aimDeck;

        plateResourceCard = new ResourceCard[2];
        plateGoldenResourceCard = new GoldResourceCard[2];
        plateAimCard = new AimCard[2];
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



    // Public methods

    /**
     * Initialization of the CommonBoard by loading the decks, shuffle them and adding the cards on the plates
     * Requirements: decks and plates should be already created
     */
    public void iniCommonBoard() {

        resourceDeck.shuffle();
        goldenResourceDeck.shuffle();
        aimDeck.shuffle();
        startingDeck.shuffle();

        // Adding the two cards on the plates
        plateResourceCard[0] = resourceDeck.pickFromTop();
        plateResourceCard[1] = resourceDeck.pickFromTop();

        plateGoldenResourceCard[0] = goldenResourceDeck.pickFromTop();
        plateGoldenResourceCard[1] = goldenResourceDeck.pickFromTop();

        plateAimCard[0] = aimDeck.pickFromTop();
        plateAimCard[1] = aimDeck.pickFromTop();
    }

    /**
     * This method checks if both the resource deck and the golden deck are both empty
     * @return true, if they are. False, otherwise
     */
    public boolean deckEmptinessCheck() {
        return (resourceDeck.isEmpty() && goldenResourceDeck.isEmpty());
    }

    /**
     * This method get the card from the ResourcePlate
     * Requirements: selection can only have the values 0,1 and ResourcePlate cannot be empty
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public ResourceCard pickFromResourcePlate(int selection) {
        return plateResourceCard[selection];
    }

    /**
     * This method get the card from the GoldenPlate
     * Requirements: selection can only have the values 0,1 and GoldenPlate cannot be empty
     * @param selection is the index that select which of the two cards pick from the plate
     * @return the card selected, null if there is no card at the index selected
     */
    public GoldResourceCard pickFromGoldenPlate(int selection) {
        return plateGoldenResourceCard[selection];
    }

    /**
     * This method replace the card drawn from the plate with the first card of the resource deck
     * If the deck is empty it set null at the position to be replaced
     * Requirements: selection can only have the values 0,1
     * @param selection is the index of the card drawn from the plate
     */
    public void addCardToResourcePlate(int selection) {
        if (resourceDeck.isEmpty()) {
            plateResourceCard[selection] = null;
        } else {
            plateResourceCard[selection] = resourceDeck.pickFromTop();
        }
    }

    /**
     * This method replace the card drawn from the plate with the first card of the golden deck
     * If the deck is empty it set null at the position to be replaced
     * Requirements: selection can only have the values 0,1
     * @param selection is the index of the card drawn
     */
    public void addCardToGoldenPlate(int selection) {
        if (goldenResourceDeck.isEmpty()) {
            plateGoldenResourceCard[selection] = null;
        }
        plateGoldenResourceCard[selection] = goldenResourceDeck.pickFromTop();
    }


    // MVC --> Network related methods
    //CommonBoard State Differences for the Network information transport

    /**
     * This method returns the cardID of the new Card that took position of the Card picked by the Player.
     * In case of an empty Plate position or Deck, this method will return a particular int (404),
     * inspired by the HTTP error 404 not found (it isn't linked directly to the HTTP protocol but the value is used due to its common popularity)
     * @param choice choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)
     * @param selection possible selection on the CommonBoard: the two cards on plate(0), plate(1); or deck(2)
     * @return the cardID of the new Card on the Plate
     */
    public int boardCardDifference(int choice, int selection) {
        if (choice == 0) {
            if (selection == 0 || selection == 1) {
                if (plateResourceCard[selection] == null) return 404;
                else return plateResourceCard[selection].getCardID();
            } else return this.deckDifference(choice);
        } else {
            if (selection == 0 || selection == 1) {
                if (plateGoldenResourceCard[selection] == null) return 404;
                else return plateGoldenResourceCard[selection].getCardID();
            } else return this.deckDifference(choice);
        }
    }

    /**
     * This method returns the cardID of the Card in the Top of the Deck (it has to be used after a draw()).
     * In case of an empty Deck, this method will return a particular int (404), inspired by the HTTP error 404 not found
     * (it isn't linked directly to the HTTP protocol but the value is used due to its common popularity)
     * @param choice choice of which type of Card to draw from the CommonBoard: resource(0) or golden(1)
     * @return the cardID of the Card on the top of the chosen deck
     */
    public int deckDifference(int choice){
        if (choice == 0) {
            //Emptiness check
            if (!this.resourceDeck.isEmpty()){
                return this.resourceDeck.peekFirstCard();
            } else {
                //If a Deck is empty, the 404 value will be returned to signal it
                return 404;
            }
        } else {
            if (!this.goldenResourceDeck.isEmpty()) {
                return this.goldenResourceDeck.peekFirstCard();
            } else {
                //If a Deck is empty, the 404 value will be returned to signal it
                return 404;
            }
        }
    }
}

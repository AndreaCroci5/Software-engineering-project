package it.polimi.ingsw.am40.client.view.TUI;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.client.smallModel.SmallCardLoader;
import it.polimi.ingsw.am40.client.view.ViewManager;
import it.polimi.ingsw.am40.server.model.CardElements;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The TUIManager is the class which make possible to display the information
 * and to manage the inputs with a command line interface on the console
 */
public class TUIManager implements ViewManager {

    //ATTRIBUTES

    /**
     * Reference to th client
     */
    private Client client;

    /**
     * Constructor for the TUIManger
     * @param client is the client on which I create the new TUIManager
     */
    public TUIManager(Client client) {
        this.client = client;
    }

    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    @Override
    public void initView() {
        this.printPixelArt("logo.txt");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public Client getClient() {
        return this.client;
    }


    // Initialisation

    /**
     * Asks for the username
     */
    @Override
    public void displayInitialisation() {
        printTitle();
        System.out.println(">Welcome to the game ");
        System.out.println(">Choose the username you want to use: ");

    }

    /**
     * Asks if the client want to join or create a game
     */
    @Override
    public void displaySetUp() {
        System.out.println(">Do you want to join an existing game or create a new game (join/create) ");
    }

    /**
     * Asks information about the creation
     */
    @Override
    public void displayCreate() {
        System.out.println(">How many players do you want in your game?");
        System.out.println(">Options are: (2,3,4)");
    }

    /**
     * Shows all gameIDs of the available games
     * @param gamesIDs are all the available games
     */
    @Override
    public void displayAllGameIds(Map<Integer, ArrayList<Integer>> gamesIDs) {
        System.out.println("\n");
        System.out.println(">Here are all the possible games you can choose: ");
        for (Integer gameID : gamesIDs.keySet()) {
            System.out.println(">Game ID: " + gameID);
        }
        System.out.println(">Write the ID of the game you want to join: ");
    }





    // First Round

    /**
     * Displays the starting game screen with player nicknames and the common board
     * @param nicknames    the list of player nicknames
     * @param commonBoard  the list of small cards representing the common board
     */
    @Override
    public void displayStartingGame(ArrayList<String> nicknames, List<SmallCard> commonBoard) {
        System.out.println("\n");
        System.out.println(">Game is starting");
        for (String nickname : nicknames) {
            System.out.println(">" + nickname + " has joined the game");
        }
        System.out.println("\n");
        displayCommonBoard(commonBoard);
        System.out.println(">The game is started ");
        System.out.println(">Up here you can find all the information of the game");
        System.out.println(">Let's start with the first phase");
    }


    /**
     * Shows the information of the starting card
     * @param startingCardID is the ID of the starting card
     */
    @Override
    public void displayStartingCardInfo(int startingCardID) {
        System.out.println("\n");
        System.out.println(">First of all you need to choose how you want to place your starting card ");
        System.out.println(">Here is your starting card: ");
        SmallCard card = SmallCardLoader.findCardById(startingCardID);
        System.out.println(">Here are all the card information: ");
        assert card != null;
        System.out.println("ID of the card: " + card.getCardID());
        printStartingFrontFace(card);
        printStartingBackFace(card);
        System.out.println(">To see the symbols legend write: (legend) ");
    }

    /**
     * Asks on which face the client wants to place the starting card
     */
    @Override
    public void askStartingFace() {
        System.out.println(">You need to choose on which face you want to place the card");
        System.out.println(">Front face means that the visible face of the card, after placing its, is going to be the front one");
        System.out.println(">Choose the starting card face: (front/back)");
    }

    /**
     * Show the result of the starting card placement at all the passive clients
     * @param nickname is the name of the active player
     * @param startingCardID is the ID of the starting card
     * @param cardFace is the face on which the client place his starting card
     */
    @Override
   public void showPassiveStartingCard(String nickname,int startingCardID,String cardFace) {
        System.out.println("\n");
        System.out.println(">The player: " + nickname + " chose this starting card");
        System.out.println(">Here are all the information of " + nickname + " starting card");
        SmallCard card = SmallCardLoader.findCardById(startingCardID);
        assert card != null;
        printStartingFrontFace(card);
        printStartingBackFace(card);
        System.out.println(">And he placed it in his " + cardFace + " face");
   }

    /**
     * Displays the possible tokens that can be chosen by the client
     * @param tokens the list of possible tokens
     */
    @Override
    public void displayPossibleTokens(List<String> tokens) {
        System.out.println("\n");
        System.out.println(">Now you need to choose the color of the token you want to use ");
        String result = String.join(", ", tokens);
        System.out.println(">The color of the tokens are: " + result);
        System.out.println(">Write the color you want to use: ");
    }


    /**
     * Displays the 3 cards in the hand of the client
     * @param myHand the list of small cards in the player's hand
     */
    @Override
    public void displayDealCardState(List<SmallCard> myHand) {
        System.out.println("\n");
        displayMyHand(myHand);
        System.out.println(">Every player has just received 2 resource cards and a gold card ");
        System.out.println(">Up here you can see the cards in your hand ");
    }

    /**
     * Displays the aim cards for the user to choose from
     * @param aimCardsID the IDs of the aim cards to be displayed
     */
    @Override
    public void displayAimCardsToChoose(List<Integer> aimCardsID) {
        System.out.println("\n");
        System.out.println(">Now you need to choose you personal aim card ");
        System.out.println(">Every aim card has a pattern that can give you points at the end of the game ");
        System.out.println(">Choose the aim card based on the pattern you prefer to recreate with your cards on you personal board ");
        System.out.println(">Here are the two possible aim cards you can choose ");
        System.out.println(">You have to choose one of them ");
        for (Integer aimCardId : aimCardsID) {
            SmallCard card = SmallCardLoader.findCardById(aimCardId);
            assert card != null;
            printAimCard(card);
        }
        System.out.println(">Write the ID of the aim card you want to choose: ");
    }

    /**
     * Shows the passive aim card result to the passive client
     * @param nickname the nickname of the active client
     */
    @Override
    public void showPassiveAimCardResult(String nickname) {
        System.out.println("\n");
        System.out.println(">" + nickname + " chose his aim card ");
    }

    /**
     * Displays the order of the players and description of the round
     * @param playerOrder the list of player names in the order of their turn
     */
    @Override
    public void displayPlayerOrder(List<String> playerOrder) {
        System.out.println("\n");
        System.out.println(">Well done, we finished the first phase of the game");
        System.out.println(">Let's start with the round");
        int i=0;
        System.out.println(">The order of the players is: ");
        for (String player : playerOrder) {
            System.out.println(">" + i + ": " + player);
            ++i;
        }
        System.out.println(">A round is composed by two phases: ");
        System.out.println("1- Placing phase in which you have to select a card from your hand and place it in your board ");
        System.out.println("2- Draw phase in which you select which card you want to draw from the common board ");
    }






    // Round

    /**
     * Display the placing card choice to the user tha has to choose the card to place
     * @param myHand  the list of small cards in the user's hand
     * @param myGrid  the list of small cards in the user's grid
     */
    @Override
    public void displayPlacingCardChoice(List<SmallCard> myHand, List<SmallCard> myGrid) {
        System.out.println("\n");
        displayMyHand(myHand);
        System.out.println(">Up here you can see the card in your hand ");
        System.out.println(">Now you need to place a card");
        System.out.println(">First of all you need to chose the card in your hand that you want to place");
        System.out.println(">Remember rules of the card");
        System.out.println("1- In order to place a gold card you have to posses the requirements resources in you board ");
        System.out.println("2- Some card gives you points as soon as you place them while other can help you gain points during the match");
        System.out.println(">If you want to see the counter of the elements in your board in order to see if you have the requirements for placing a gold card write: (counter)");
        System.out.println(">Write the ID of the card you want to place: ");
    }

    /**
     * Asks the user which corner of the card in his board want to cover
     */
    @Override
    public void displayPlacingCardToCoverChoice(List<SmallCard> myGrid) {
        System.out.println("\n");
        displayGridExample();
        System.out.println("\n");
        displayPersonalGrid(myGrid);
        System.out.println(">Up here you can see your personal grid");
        System.out.println(">Now you need to chose the position on which you want to place the card ");
        System.out.println(">Remember rules of the placing: ");
        System.out.println("1- The card must cover one or several visible corners of cards already present in their play area ");
        System.out.println("2- It cannot cover more than one corner of the same card ");
        System.out.println("3- Only the card already present in the play area may contain the necessary visible corners ");
        System.out.println("4- If you cover a corner with a resource you lose that resource ");
        System.out.println(">You need to choose a card and the corner of that card that you want to cover ");
        System.out.println(">Write the ID of the card on which you want to cover the corner ");
    }

    /**
     * How to understand the coordinates in the board
     */
    private void displayGridExample() {
        System.out.println(">To understand how coordinates work: ");
        System.out.println(">So if you see coordinates that has a +1 on the x of another card, that means they're placed on its bottom right corner");
        System.out.println(">So if you see coordinates that has a -1 on the x of another card, that means they're placed on its top left corner");
        System.out.println(">So if you see coordinates that has a +1 on the y of another card, that means they're placed on its top right corner");
        System.out.println(">So if you see coordinates that has a -1 on the y of another card, that means they're placed on its bottom left corner");
    }

    /**
     * Asks the user which face he wants to place the card on
     */
    @Override
    public void displayPlacingFaceChoice() {
        System.out.println(">Now you need to choose the face on which you want to place your card ");
        System.out.println(">Choose (front/back)");
    }

    /**
     * Asks the user which corner of the card in his board want to cover
     */
    @Override
    public void displayPlacingCornerCover() {
        System.out.println(">What about the corner you want to cover ");
        System.out.println("1 : Top left");
        System.out.println("2 : Top right");
        System.out.println("3 : Bottom left");
        System.out.println("4 : Bottom right");
        System.out.println(">Write the number associated to the corner you want to cover ");
    }


    /**
     * Asks where the user want to draw from
     * @param commonBoard is the commonBoard of the game
     */
    @Override
    public void displayDrawChoice(List<SmallCard> commonBoard) {
        System.out.println("\n");
        displayCommonBoard(commonBoard);
        System.out.println(">Up here you can see the common board");
        System.out.println(">Now you need to choose which card you want to draw ");
        System.out.println(">Options are (ResDeck/ResPlate1/ResPlate2/GoldDeck/GoldPlate1/GoldPlate2)");
    }

    /**
     * Displays the last round message to the client
     * @param clientNickname the nickname of the active player
     */
    @Override
    public void displayLastRoundMessage(String clientNickname) {
        System.out.println(">The player: " + clientNickname + " has reached 20 points");
    }

    /**
     * Displays the end game banner and the names of the winners
     * @param winners the list of names of the winners
     */
    @Override
    public void displayEndGame(List<String> winners) {
        if (winners == null) {
            displayInterruptedGame();
            return;
        }
        printEndGame();
        if (winners.size()>1) {
            System.out.println(">The winners are: ");
            for (String winner : winners) {
                System.out.println(">Player: " + winner);
            }
        }
        else {
            System.out.println(">The winner is: " + winners.getFirst());
        }
    }






    // Possible inputs

    /**
     * Displays the possible inputs available to the user
     */
    @Override
    public void showPossibleInputs() {
        System.out.println(">These are the inputs you can use in order to see the information you want: ");
        System.out.println(">To see the common board write: commonBoard");
        System.out.println(">To see your board write: myBoard");
        System.out.println(">To see your aim card write: myAimCard");
        System.out.println(">To see your hand cards write: myHand");
        System.out.println(">To see your token color write: myToken");
        System.out.println(">To see the counter of the elements in your private board write: counter");
        System.out.println(">To see the scoreboard write: scoreboard");
        System.out.println(">To see the legend of the symbols write: legends");
        System.out.println(">To see other player board write: otherBoard");
    }

    /**
     * Displays the common board, which consists of a list of small cards
     * @param commonBoard the list of small cards that make up the common board
     */
    @Override
    public void displayCommonBoard(List<SmallCard> commonBoard) {
        System.out.println(">The cards in the common board are: ");

        System.out.println(">Position: ResDeck");
        displayResourceCard(commonBoard.get(2),"back");
        System.out.println(">Position: ResPlate1");
        displayResourceCard(commonBoard.get(0),"front");
        System.out.println(">Position: ResPlate2");
        displayResourceCard(commonBoard.get(1),"front");

        System.out.println(">Position: GoldDeck");
        displayGoldCard(commonBoard.get(5),"back");
        System.out.println(">Position: GoldPlate1");
        displayGoldCard(commonBoard.get(3),"front");
        System.out.println(">Position: GoldPlate2");
        displayGoldCard(commonBoard.get(4),"front");

        System.out.println(">Position: AimDeck");
        printAimBackCard();
        System.out.println(">Position: AimPlate1");
        printAimCard(commonBoard.get(6));
        System.out.println(">Position: AimPlate2");
        printAimCard(commonBoard.get(7));

        System.out.println("\n");
    }


    /**
     * Displays the score board to the user
     * @param scoreBoard the map containing player names as keys and scores as values
     */
    @Override
    public void displayScoreBoard(Map<String,Integer> scoreBoard) {
        System.out.println("Scoreboard: ");

        // convert the entry set of the HashMap to a list
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(scoreBoard.entrySet());

        // sort the list based on the points in descending order
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue()); // Sort in descending order
            }
        });

        // print the scoreboard
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println("Name: " + entry.getKey() + ", Points: " + entry.getValue());
        }
    }

    /**
     * Displays the card in my hand
     * @param myHand the list of small cards in the player's hand
     */
    @Override
    public void displayMyHand(List<SmallCard> myHand) {
        System.out.println(">The cards in your hand are: ");
        for (SmallCard card : myHand) {
            if (card.getRequires() != null) {
                displayGoldCard(card);
            }
            else {
                displayResourceCard(card);
            }
            System.out.println("\n");
        }
    }

    /**
     * Displays the personal aim card to the user
     * @param card the small card representing the personal aim card
     */
    @Override
    public void displayPersonalAimCard(SmallCard card) {
        System.out.println(">Your aim card is: ");
        printAimCard(card);
    }


    /**
     * Displays the personal token of the client
     * @param token the personal token of the client to be displayed
     */
    @Override
    public void displayPersonalToken(String token) {
        System.out.println(">You have the " + token + " token");
    }


    /**
     * Displays the list of SmallCards representing the personal grid of the player
     * @param myGrid the list of SmallCards representing the personal grid of the player
     */
    @Override
    public void displayPersonalGrid(List<SmallCard> myGrid) {
        System.out.println(">This is your personal grid ");
        for (SmallCard card : myGrid) {
            if (card.getRequires() != null) {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayGoldCard(card,card.getFace());
            }
            else if (card.getStartingResource() == null) {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayResourceCard(card,card.getFace());
            }
            else {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayStartingCardInfo(card,card.getFace());
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    /**
     * Display the information of a starting card
     * @param card is the starting card
     * @param face is the face on which it was placed
     */
    private void displayStartingCardInfo(SmallCard card, String face) {
        System.out.println(">Here are all the card information: ");
        System.out.println("ID of the card: " + card.getCardID());
        if (face.equalsIgnoreCase("front")) {
            printStartingFrontFace(card);
        }
        else {
            printStartingBackFace(card);
        }
    }

    /**
     * Displays the grid of other players to the user
     * @param othersPlayerGrid a map containing the grid of each other player,
     *                          where the key is the player's name and the value is a list of SmallCards representing their grid.
     */
    @Override
    public void displayOtherPlayersGrid(Map<String,ArrayList<SmallCard>> othersPlayerGrid) {
        System.out.println(">These are the personal board of the other players: ");
        for (String s : othersPlayerGrid.keySet()) {
            System.out.println(s + " board: ");
            printListCard(othersPlayerGrid.get(s));
        }
    }

    /**
     * Print a list of SmallCards
     * @param myGrid is my personal grid
     */
    private void printListCard(ArrayList<SmallCard> myGrid) {
        System.out.println(">Here are all the card in his personal board");
        for (SmallCard card : myGrid) {
            if (card.getRequires() != null) {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayGoldCard(card,card.getFace());
            }
            else if (card.getStartingResource() == null) {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayResourceCard(card,card.getFace());
            }
            else {
                System.out.println(">Coordinates : (" + card.getCoordinates().getX() + ", " + card.getCoordinates().getY() + ")");
                displayStartingCardInfo(card,card.getFace());
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }

    /**
     * Displays the chat in the user interface
     */
    public void displayChat() {
        System.out.println("\n>TO BE IMPLEMENTED\n");
    }

    /**
     * This method displays the legend of the symbols used in the game UI
     */
    @Override
    public void displaySymbolLegend() {
        System.out.println("\uD83D\uDD8B  : INKWELL");
        System.out.println("\uD83C\uDF44 : FUNGI");
        System.out.println("\uD83D\uDC3A : ANIMAL");
        System.out.println("\uD83C\uDF43 : PLANT");
        System.out.println("\uD83D\uDCDC : MANUSCRIPT");
        System.out.println("\uD83E\uDD8B : INSECT");
        System.out.println("\uD83E\uDEB6 : QUILL");
        System.out.println("❌ : HIDDEN CORNER (no corner)");
        System.out.println("✔  : EMPTY CORNER (there is the corner but no resource on it)");
    }






    // Positive result and errors

    /**
     * Displays a message indicating that it is not the turn of the current player
     */
    @Override
    public void showNotYouTurn() {
        System.out.println(">It's not your turn ");
        System.out.println(">Another player is playing ");
    }


    /**
     * Displays the waiting message for players to join the game
     * @param numOfActualPlayers The number of players currently joined
     * @param numOfFinalPlayers  The total number of players needed to start the game
     */
    @Override
    public void displayWaitingForPlayers(int numOfActualPlayers, int numOfFinalPlayers) {
        System.out.println(">Waiting for " + (numOfFinalPlayers - numOfActualPlayers) + " more players to join...");
    }

    @Override
    public void displayError() {
        System.out.println(">The input you send is not a valid input ");
    }

    /**
     * Displays a message to the client indicating that there are no active parties to join, and they need to create a new game
     */
    @Override
    public void showNoActiveParties() {
        System.out.println(">There are no active parties, you must create a new game");
    }

    /**
     * Displays a message to indicate that joining a game with the given game ID has failed
     * This method is called when an error occurs during the joining process
     */
    @Override
    public void showFailedGameID() {
        System.out.println(">Something went wrong while joining the game");
    }

    /**
     * Displays the result of placing the starting card on the UI
     * @param cardID The ID of the starting card.
     * @param cardFace The face on which the client placed the starting card.
     */
    @Override
    public void displayStartingCardResult(int cardID, String cardFace) {
        System.out.println(">Well done, you chose to place your starting card in his " + cardFace + " face");
    }

    /**
     * Displays the positive token color for a specific client
     * @param clientNickname the nickname of the client
     * @param token the color of the token chosen by the client
     */
    @Override
    public void showPositiveTokenColor(String clientNickname, String token) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " chose the " + token + " token");
    }

    /**
     * Displays the positive aim card choice to the user
     */
    @Override
    public void displayPositiveAimCardChoice() {
        System.out.println(">Well done, you chose your personal aim card ");
        System.out.println(">Remember to create its pattern in order to gain points at the end of the game");
    }

    /**
     * Show that the placing went good
     */
    @Override
    public void displayPositivePlacing() {
        System.out.println(">Well done you placed the card ");
    }

    /**
     * It shows that all went good with the draw
     */
    @Override
    public void displayPositiveDraw() {
        System.out.println(">Well done you drawn a card ");
        System.out.println(">Common board, card in your hand and your grid have been updated ");
        System.out.println(">To show all the information you need write : (inputs) ");
    }

    /**
     * It shows the elements counter in the user private board
     * @param elementsCounter is the counter of the elements in the client private board
     */
    @Override
    public void diplayElementsCounter(Map<CardElements, Integer> elementsCounter) {

        System.out.println(">This is the counter of the elements in your board: ");

        for (CardElements elements : elementsCounter.keySet()) {
            System.out.println(">" + CardElementsToString(String.valueOf(elements)) + " : " + elementsCounter.get(elements));
        }
    }

    /**
     * Show that something went wrong with the draw
     */
    @Override
    public void displayNegativeDraw() {
        System.out.println("\n");
        System.out.println(">Something went wrong with the draw, please choose another card");
    }

    /**
     * Show that something went wrong with the placing
     */
    @Override
    public void displayNegativePlacing() {
        System.out.println("\n");
        System.out.println(">Something went wrong with the placing ");
        System.out.println(">Problems could be: ");
        System.out.println("1- Placing a gold card without having the requirements on your board ");
        System.out.println("2- The position where you want to place the card is not a valid position ");
    }

    /**
     * Show that a player draw a card
     * @param clientNickname is the name of the active client
     */
    @Override
    public void displayPassiveDrawResult(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " drawn a card");
    }

    /**
     * Show that another player place a card
     * @param clientNickname is the name of the active client
     */
    @Override
    public void displayPassivePlacingResult(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " placed a card ");
        System.out.println(">" + clientNickname + " is choosing which card he wants to draw ");
        System.out.println(">Wait for him to finish ");
    }





    // Passive states

    /**
     * Displays that another player is placing the starting card
     * @param clientNickname the nickname of the client active client
     */
    @Override
    public void showPassiveStartingCardState(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " is choosing how to place his starting card");
        System.out.println(">Wait for him to finish ");
    }

    /**
     * Shows that another client is choosing a token
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void showPassiveTokenState(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " is choosing his token color");
        System.out.println(">Wait for him to finish ");
    }

    /**
     * Displays that another player is choosing his aim card
     * @param clientNickname the nickname of the active client
     */
    @Override
    public void showPassiveAimState(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " is choosing his personal aim card ");
        System.out.println(">Wait for him to finish ");
    }

    /**
     * Display that another player is placing
     * @param clientNickname is the name of the active client
     */
    @Override
    public void displayPassivePlacingState(String clientNickname) {
        System.out.println("\n");
        System.out.println(">" + clientNickname + " is placing a card ");
        System.out.println(">Wait for him to finish ");
    }

    /**
     * Show that the game has been interrupted
     */
    @Override
    public void displayInterruptedGame() {
        printEndGame();
        System.out.println("\n");
        System.out.println("GAME HAS BEEN INTERRUPTED BY THE DISCONNECTION OF ANOTHER PLAYER!");
        System.out.println("Closing application...");
        System.exit(0);
    }




    // Printer

    /**
     * Displays the title of the game
     * The title is displayed using ASCII art
     */
    private void printTitle() {
        System.out.println( "\n" +
                " ██████  ██████  ██████  ███████ ██   ██     ███    ██  █████  ████████ ██    ██ ██████   █████  ██      ██ ███████ \n" +
                "██      ██    ██ ██   ██ ██       ██ ██      ████   ██ ██   ██    ██    ██    ██ ██   ██ ██   ██ ██      ██ ██      \n" +
                "██      ██    ██ ██   ██ █████     ███       ██ ██  ██ ███████    ██    ██    ██ ██████  ███████ ██      ██ ███████ \n" +
                "██      ██    ██ ██   ██ ██       ██ ██      ██  ██ ██ ██   ██    ██    ██    ██ ██   ██ ██   ██ ██      ██      ██ \n" +
                " ██████  ██████  ██████  ███████ ██   ██     ██   ████ ██   ██    ██     ██████  ██   ██ ██   ██ ███████ ██ ███████ \n" +
                "                                                                                                                    \n" +
                "                                                                                                                    \n" );
    }

    /**
     * Print the endGame banner
     */
    private void printEndGame() {
        System.out.println("\n" +
                "███████ ███    ██ ██████       ██████   █████  ███    ███ ███████ \n" +
                "██      ████   ██ ██   ██     ██       ██   ██ ████  ████ ██      \n" +
                "█████   ██ ██  ██ ██   ██     ██   ███ ███████ ██ ████ ██ █████   \n" +
                "██      ██  ██ ██ ██   ██     ██    ██ ██   ██ ██  ██  ██ ██      \n" +
                "███████ ██   ████ ██████       ██████  ██   ██ ██      ██ ███████ \n" +
                "                                                                  \n" +
                "                                                                  \n");
    }

    /**
     * Print the aim card based on its pattern
     * @param card is the aim card
     */
    private void printAimCard(SmallCard card) {
        System.out.println(">Description of the card: ");
        System.out.println("The ID of the card is " + card.getCardID());
        if (card.getChecker().equalsIgnoreCase("AimCheckerDiagonalPattern")) {
            System.out.println("You score " + card.getPoints() + " points every time you have this pattern in your board");
            if (card.getRotation().equalsIgnoreCase("y")) {
                System.out.println("               _____ ");
                System.out.println("              | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("               ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("        ----- ");
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println(" ----- ");
            }
            else if (card.getRotation().equalsIgnoreCase("x")) {

                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println(" ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("        ----- ");
                System.out.println("               _____ ");
                System.out.println("              | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("               ----- ");
            }
        }

        else if (card.getChecker().equalsIgnoreCase("AimCheckerLPattern")) {
            System.out.println("You score " + card.getPoints() + " points every time you have this pattern in your board: ");
            if (card.getRotation().equalsIgnoreCase("x")) {
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println(" ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println("        ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println("        ----- ");

            }
            else if (card.getRotation().equalsIgnoreCase("y")) {
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println("        ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println("        ----- ");
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println(" ----- ");
            }
            else if (card.getRotation().equalsIgnoreCase("-x")) {
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println(" ----- ");
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println(" ----- ");
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("        ----- ");
            }
            else if (card.getRotation().equalsIgnoreCase("-y")) {
                System.out.println("        _____ ");
                System.out.println("       | " + CardElementsToString(card.getCheckResources().get(0)) + " |");
                System.out.println("        ----- ");
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println(" ----- ");
                System.out.println(" _____ ");
                System.out.println("| " + CardElementsToString(card.getCheckResources().get(1)) + " |");
                System.out.println(" ----- ");
            }
        }
        else if (card.getChecker().equalsIgnoreCase("AimCheckerResource")) {
            List<String> res = new ArrayList<>();
            for (String req : card.getCheckResources()) {
                res.add(CardElementsToString(req));
            }
            System.out.println("You score " + card.getPoints() + " points every time you have this pattern in your board: " + String.join(" ",res));
        }
    }

    /**
     * Print the pixel art of the game
     * @param fileName is the name of the logo file
     */
    private void printPixelArt(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + fileName);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the front face of a gold card
     * @param card is the gold card
     */
    private void printGoldFrontFace(SmallCard card) {
        if (card.getScoreType() == null) {
            System.out.println("Points of the card: " + card.getScorePoints());
        }

        else {
            if (card.getScoreType().equalsIgnoreCase("CoverageScoreType")) {
                System.out.println("You score " + card.getScorePoints() + " points for every corner you cover when you place this card");
            }
            if (card.getScoreType().equalsIgnoreCase("ObjectScoreType")) {
                System.out.println("You score " + card.getScorePoints() + " points for every " + CardElementsToString(card.getObjectScoreType()) + " you have in your personal board");
            }
            if (card.getScoreType().equalsIgnoreCase("NormalScoreType")) {
                System.out.println("Points of the card: " + card.getScorePoints());
            }
        }

        if (card.getRequires() == null) {
            System.out.println(">No requires in order to play this card");
        }
        else {
            List<String> res = new ArrayList<>();
            for (String req : card.getRequires()) {
                res.add(CardElementsToString(req));
            }
            System.out.println("Resource required in order to place the card in its front face: " + String.join(" ",res));
        }
        printResourceFrontFace(card);
    }

    /**
     * Print the front face of a resource card
     * @param card is the resource card
     */
    private void printResourceFrontFace(SmallCard card) {
        System.out.println("Front of the card: ");
        displayEdgeResources(card.getFrontEdgeResources());
    }

    /**
     * Print the back face of a resource card
     * @param card is the resource card
     */
    private void printResourceBackFace(SmallCard card) {
        System.out.println("Back of the card: ");
        System.out.println("Resource in the back center of the card: " + CardElementsToString(card.getCardElement()));
        List<String> edgeResources = new ArrayList<String>();
        for (int i=0; i<4; ++i) {
            edgeResources.add("empty");
        }
        displayEdgeResources(edgeResources);
    }

    /**
     * Show the information of a resource card
     * @param card is the resource card
     */
    public void displayResourceCard(SmallCard card) {
        System.out.println(">Description of the card: ");
        System.out.println("The ID of the card is " + card.getCardID());
        System.out.println("The type of card is: RESOURCE");
        System.out.println("Element of the card " + CardElementsToString(card.getCardElement()));
        System.out.println("Points of the card: " + card.getScorePoints());
        printResourceFrontFace(card);
        printResourceBackFace(card);
    }

    /**
     * Show the information of a resource card based on its face
     * @param card is the resource card
     */
    public void displayResourceCard(SmallCard card,String face) {
        System.out.println(">Description of the card: ");
        System.out.println("The ID of the card is " + card.getCardID());
        System.out.println("The type of card is: RESOURCE");
        System.out.println("Element of the card " + CardElementsToString(card.getCardElement()));
        System.out.println("Points of the card: " + card.getScorePoints());
        if (face.equalsIgnoreCase("front")) {
            printResourceFrontFace(card);
        }
        else {
            printResourceBackFace(card);
        }
    }


    /**
     * Show the information of a gold card
     * @param card is the gold card
     */
    public void displayGoldCard(SmallCard card) {
        System.out.println(">Description of the card: ");
        System.out.println("The ID of the card is " + card.getCardID());
        System.out.println("The type of card is: GOLD");
        System.out.println("Element of the card " + CardElementsToString(card.getCardElement()));
        printGoldFrontFace(card);
        printResourceBackFace(card);
    }

    /**
     * Show the information of a gold card based on its face
     * @param card is the gold card
     */
    public void displayGoldCard(SmallCard card,String face) {
        System.out.println(">Description of the card: ");
        System.out.println("The ID of the card is " + card.getCardID());
        System.out.println("The type of card is: GOLD");
        System.out.println("Element of the card " + CardElementsToString(card.getCardElement()));
        if (face.equalsIgnoreCase("front")) {
            printGoldFrontFace(card);
        }
        else {
            printResourceBackFace(card);
        }
    }


    /**
     * Map the elements of the card in Unicode symbols
     * @param cardElement is the element of the card
     * @return a unicode symbols that represents the element
     */
    private String CardElementsToString(String cardElement) {
        if (  cardElement.equalsIgnoreCase("animal")) {
            return "🐺";
        }
        if (cardElement.equalsIgnoreCase("fungi")) {
            return "🍄";
        }
        if (cardElement.equalsIgnoreCase("plant")) {
            return "☘";
        }
        if (cardElement.equalsIgnoreCase("insect")) {
            return "🦋";
        }
        if (cardElement.equalsIgnoreCase("inkwell")) {
            return "⚗";
        }
        if (cardElement.equalsIgnoreCase("manuscript")) {
            return "📜";
        }
        if (cardElement.equalsIgnoreCase("quill")) {
            return "\uD83E\uDEB6";
        }
        if (cardElement.equalsIgnoreCase("empty")) {
            return "✔";
        }
        return "❌";
    }

    /**
     * Displays the resources on the edges
     * @param edgeResources are the resources on the edges
     */
    private void displayEdgeResources(List<String> edgeResources) {
        String resource1 = CardElementsToString(edgeResources.get(0));
        String resource2 = CardElementsToString(edgeResources.get(1));
        String resource3 = CardElementsToString(edgeResources.get(2));
        String resource4 = CardElementsToString(edgeResources.get(3));

        System.out.format("%s_____%s%n|      |%n%s_____%s%n", resource1, resource2, resource3, resource4);
    }

    /**
     * Show the front face of a starting card
     * @param card is the starting card
     */
    private void printStartingFrontFace(SmallCard card) {
        System.out.println("Front face of the card:");
        List<String> startingResources = card.getStartingResource().stream().map(this::CardElementsToString).collect(Collectors.toList());
        String result = String.join(" ", startingResources);
        System.out.println("The starting resources in the front middle of the card are: " + result);
        displayEdgeResources(card.getFrontEdgeResources());
    }

    /**
     * Show the back face of a starting card
     * @param card is the starting card
     */
    private void printStartingBackFace(SmallCard card) {
        System.out.println("Back of the card: ");
        displayEdgeResources(card.getBackEdgeResources());
    }

    /**
     * Show the aim back card
     */
    private void printAimBackCard() {
        System.out.println(" _____ ");
        System.out.println("|     |");
        System.out.println(" ----- ");
    }



}

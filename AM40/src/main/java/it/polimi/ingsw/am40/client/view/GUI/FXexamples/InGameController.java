package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.ClientMessages.ClientDisconnectedMessage;
import it.polimi.ingsw.am40.client.ClientMessages.InterruptedGameMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.AimCardChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round.DrawMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.round.PlacingMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.server.model.Coordinates;
import it.polimi.ingsw.am40.server.model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the controller of the main Game scene from where the players play the game
 */
public class InGameController extends GeneralController {
    //ATTRIBUTES
    /**
     * Reference to the Client information
     */
    private Client client;

    /**
     * Reference to the current Stage
     */
    private Stage stage;

    /**
     * Reference to the current scene
     */
    private Scene scene;
    /**
     * Reference to the Parent root of the current scene
     */
    private Parent root;

    /**
     * Reference to a Group that is displayed in the foreground for the most important event occurring in the scene.
     * Mostly used during the first round for the setup
     */
    private Group primaryEvent;

    /**
     * Reference to a Group that keeps all the tokens sorted inside and represents the token shown on the tab/billboard
     * in the top right corner of the scene
     */
    private Group tokenTab;

    /**
     * Reference to a Group that keeps all the tokens sorted inside and represents the token shown on the scoreboard
     */
    private Group commonBoardTokens;

    /**
     * Value of the card chosen to place saved as graphical resource url
     */
    private String placingChoice;

    /**
     * Reference to a Group that keeps all the Coordinates prevision sorted inside in order to be easily removed after
     * the placing decision
     */
    private Group placingCoordinatesPrevisions;

    /**
     * Color of the token selected by the Client
     */
    private String token;

    /**
     * Reference to a Map that keeps saved as key the name of a player and as value the respective token chosen
     */
    Map<String, String> playersNameAndToken;

    /**
     * The name of firstPlayer that has the right to play its turn
     */
    String firstPlayer;

    /**
     * Reference to a Group that keep all the cards shown on a grid sorted inside, in order to manage all the children
     * elements with ease
     */
    @FXML
    private Group cardPlacedInGrid;


    //ATTRIBUTES FXML
    /**
     * Reference to the Players information billboard located on the top-right of the scene
     */
    @FXML
    private VBox playersTab;

    /**
     * Reference to the commonBoard located in the bottom-left of the screen
     */
    @FXML
    private GridPane commonBoard;


    /**
     * Reference to the handDeck where a Player keeps the Cards to play
     */
    @FXML
    private HBox handDeck;

    /**
     * Orientation of the Card in the handDeck: true (FRONT), false (BACK)
     */
    private boolean cardFaceShown;

    /**
     * Reference to the faceButton needed to flip the faces of the Cards in the commonBoard
     */
    @FXML
    private Button faceButton;

    /**
     * Reference to the ImageView containing the private AimCard
     */
    @FXML
    private ImageView aimCard;

    /**
     * Reference to the Label located in the top centre of the Scene that notifies the primary event that is happening
     */
    @FXML
    private Label globalEventLabel;

    /**
     * Reference to the Label located in the top centre of the Scene that notifies in which turn the players are currently in
     */
    @FXML
    private Label turnEventLabel;

    /**
     * Reference to the scoreBoard Pane located in the top left corner of the scene, from where the global scores are
     * shown
     */
    @FXML
    private Pane scoreBoard;

    /**
     * Reference to the button that allows the Client to see the other players cardGrid situation
     */
    @FXML
    private Button boardsButton;

    /**
     * Reference to the StackPane containing the Client's cardGrid
     */
    @FXML
    private StackPane cardGrid;

    /**
     * Reference to the quitButton that allows the Client to quit the Game
     */
    @FXML
    private Button quitButton;

    /**
     * Reference to the Label that is shown when the final rounds condition is triggered
     */
    @FXML
    private Label finalRounds;



    //GETTER AND SETTER


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }


    //EVENT METHODS

    //FXML INJECTION

    @FXML
    public void chat() {

    }

    /**
     * This method is used when a player wants to quit and clicks on the quitButton
     * @param e is the mouseClick on the quitButton
     * @throws IOException in case of an input exception
     */
    @FXML
    public void quit (ActionEvent e) throws IOException {
        this.client.getNetworkManager().send(new ClientDisconnectedMessage(this.client.getNickname()));
    }

    /**
     * This method is used when a player wants to flip the face of the cards in his handDeck
     * @param e is the mouseClick on the faceButton
     */
    @FXML
    public void face(ActionEvent e) {
        if (this.cardFaceShown) this.cardFaceShown = false;
        else this.cardFaceShown = true;
        this.updateHandDeck(this.getIDsFromHandDeck());
    }

    /**
     * This method is used when a player wants to see the other players cardGrid situation
     * @throws IOException is the exception thrown in case of an error in loading the fxml file
     */
    @FXML
    public void boards() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boards.fxml"));
        Parent root = fxmlLoader.load();

        Scene s = new Scene(root);
        BoardsController boardsController = fxmlLoader.getController();
        boardsController.setClient(this.client);

        boardsController.setStage(this.stage);
        boardsController.setScene(s);
        boardsController.setRoot(root);

        boardsController.sceneSetup(this, this.playersNameAndToken, this.firstPlayer);


        this.stage.setScene(s);

        this.stage.show();
    }

    //EVENT METHODS
    //FIRST ROUND

    /**
     * This method is called when the Game is starting in order to load the first graphical components
     * @param nicknames is a reference to an ArrayList containing the name of the players
     * @param resource is a reference to a List containing the IDs of CommonBoard ResourceCards
     * @param golden is a reference to a List containing the IDs of CommonBoard GoldenResourceCards
     * @param aim is a reference to a List containing the IDs of CommonBoard AimCards
     */
    public void startingSetup (ArrayList<String> nicknames,  List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        //PlayersTAB Update
        for (int i = 0; i < nicknames.size(); i++) {
            Label playerLabel =(Label) this.playersTab.getChildren().get(i);
            playerLabel.setId(nicknames.get(i));
            playerLabel.setText(nicknames.get(i));
        }
        //CommonBoard update
        this.updateCommonBoard(resource, golden, aim);
        //Deactivate commonBoard
        this.deactivateCommonBoard();
        //Deactivate the FaceButton
        this.faceButton.setDisable(true);
        //Deactivate the boardsButton
        this.boardsButton.setDisable(true);
        //Initialize the primaryEventGroup
        this.primaryEvent = new Group();
        //Initialize TokenTab
        this.tokenTab = new Group();
        //Initialize CommonBoardTokens
        this.commonBoardTokens = new Group();
        this.playersNameAndToken = new HashMap<>();

        Pane paneToOperate = (Pane) this.root;
        paneToOperate.getChildren().add(this.tokenTab);
        paneToOperate.getChildren().add(this.commonBoardTokens);
    }

    /**
     * This method is called when a player selects the face on which he wants to play the StartingCard previously
     * displayed on screen and sends the result to the server
     * @param e is the mouseClick on the StartingCard face Imageview
     */
    public void startingCardSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();

        String cardFace = fetcher.faceFromURL(panel.getId());
        Pane paneToOperate = (Pane) this.root;
        ImageView startingCardInGrid = new ImageView();
        this.startingCardDecorator(startingCardInGrid);
        startingCardInGrid.setImage(new Image(getClass().getResourceAsStream(panel.getId())));
        this.cardPlacedInGrid.getChildren().add(startingCardInGrid);
        paneToOperate.getChildren().removeLast();
        this.client.getNetworkManager().send(new StartingCardChoiceMessage(client.getNickname(), cardFace));
    }

    /**
     * This method is called when a player selects the color of his Token previously displayed on screen
     * and sends the result to the server
     * @param e is the mouseClick on the token Imageview
     */
    public void tokenSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        String color = fetcher.tokenFromURL(panel.getId());
        this.token = color;
        //TOKEN
        ImageView tokenImgView = new ImageView();
        this.tokenOnCard(tokenImgView);
        tokenImgView.setImage(new Image(getClass().getResourceAsStream(fetcher.findTokenResource(this.token))));
        this.cardPlacedInGrid.getChildren().add(tokenImgView);

        Pane paneToOperate = (Pane) this.root;

        paneToOperate.getChildren().removeLast();

        this.client.getNetworkManager().send(new TokenChoiceMessage(client.getNickname(), color));
    }


    /**
     * This method is called when a player selects which AimCard he wants to keep from the two AimCards previously displayed
     * and sends the result to the server
     * @param e is the mouseClick on the AimCard face Imageview
     */
    public void aimCardSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();

        int aimChoiceID = fetcher.cardIDFromURL(panel.getId());
        Pane paneToOperate = (Pane) this.root;
        String url = panel.getId();
        this.aimCard.setImage(new Image(getClass().getResourceAsStream(url)));

        paneToOperate.getChildren().removeLast();

        this.client.getNetworkManager().send(new AimCardChoiceMessage(client.getNickname(), aimChoiceID));
    }





    //NET METHODS
    //FIRST ROUND

    /**
     * This method is called by override from the GUI manager after the net notifies the client with the information regarding
     * his StartingCard.
     * This method proceed to show the StartingCard faces and assigns the respective onClick event behaviour to the imageView shown
     * @param cardID is the ID of the StartingCard
     */
    @Override
    public void startingCardInfo (int cardID) {
        this.globalEventLabel.setText("Choose a StartingCard");

        Pane paneToOperate = (Pane) this.root;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Image frontCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "FRONT")));
        Image backCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "BACK")));


        ImageView startingCardFront = new ImageView(frontCard);
        startingCardFront.setFitHeight(73);
        startingCardFront.setFitWidth(110);
        startingCardFront.setX(350);
        startingCardFront.setY(150);

        ImageView startingCardBack = new ImageView(backCard);
        startingCardBack.setFitHeight(73);
        startingCardBack.setFitWidth(110);
        startingCardBack.setX(570);
        startingCardBack.setY(150);

        startingCardBack.setId(fetcher.findCardResource(cardID, "BACK"));
        startingCardFront.setId(fetcher.findCardResource(cardID, "FRONT"));

        startingCardFront.setOnMouseClicked(this::startingCardSelection);
        startingCardBack.setOnMouseClicked(this::startingCardSelection);

        this.primaryEvent.getChildren().add(this.eventRectangleCreator());
        this.primaryEvent.getChildren().add(startingCardFront);
        this.primaryEvent.getChildren().add(startingCardBack);

        paneToOperate.getChildren().add(this.primaryEvent);
    }

    /**
     * This method is called by the GUI manager through override when another player is choosing his StartingCard
     * @param nickname is the nickname of the player that is choosing his StartingCard
     */
    @Override
    public void showPassiveStartingCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Starting Card");
    }


    /**
     * This method is called through override from the GUI manager after the net notifies the client with the information regarding
     * the colors available for the Token.
     * This method proceeds to show the Tokens remaining and assigns the respective onClick event behaviour to the imageView shown
     * @param tokens are the remaining colors
     */
    @Override
    public void tokenInfo (List<String> tokens) {
        this.globalEventLabel.setText("Choose a Token");
        Pane paneToOperate = (Pane) this.root;
        this.primaryEvent = new Group();
        this.primaryEvent.getChildren().add(this.eventRectangleCreator());
        int tokenPadding = 100;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (String color: tokens) {
            Image tokenColor = new Image(getClass().getResourceAsStream(fetcher.findTokenResource(color)));
            ImageView tokenView = new ImageView(tokenColor);
            tokenView.setY(150);
            tokenView.setX(229+tokenPadding);
            tokenView.setFitHeight(70);
            tokenView.setFitWidth(70);
            tokenView.setOnMouseClicked(this::tokenSelection);
            tokenView.setId(fetcher.findTokenResource(color));
            this.primaryEvent.getChildren().add(tokenView);
            tokenPadding += 100;
        }
        paneToOperate.getChildren().add(this.primaryEvent);

    }

    /**
     * This method is called by the GUI manager through override when another player is choosing his Token
     * @param nickname is the nickname of the player that is choosing his Token
     */
    @Override
    public void showPassiveToken (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Token");
    }

    /**
     * This method is called by the GUI manager through override when the Server confirms the color selection of the token
     * made by a Player, including the Client
     * @param clientNickname is the name of the Player that has just chosen the Token
     * @param token is the color of the Token
     */
    @Override
    public void acceptedToken (String clientNickname, String token) {
        this.globalEventLabel.setText("");
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        ImageView billBoardToken = new ImageView();
        ImageView scoreBoardToken = new ImageView();

        if (this.tokenTab.getChildren().size() > 0) {
            ImageView lastToken = (ImageView) this.tokenTab.getChildren().getLast();
            billBoardToken.setY(lastToken.getY() + 55);
        } else {
            billBoardToken.setY(80);
        }
        billBoardToken.setX(950);
        billBoardToken.setFitHeight(30);
        billBoardToken.setFitWidth(30);
        Image colorImg = new Image(getClass().getResourceAsStream(fetcher.findTokenResource(token)));
        billBoardToken.setImage(colorImg);
        billBoardToken.setId(clientNickname);

        this.playersNameAndToken.put(clientNickname, token);
        this.tokenTab.getChildren().add(billBoardToken);

        Pane tokenLocation = (Pane) this.scoreBoard.getChildren().get(1);

        if (tokenLocation.getChildren().size() > 0) {
            ImageView lastToken = (ImageView) tokenLocation.getChildren().getLast();
            scoreBoardToken.setX(lastToken.getX() + 5);
        } else {
            scoreBoardToken.setX(-3);
        }
        scoreBoardToken.setFitHeight(20);
        scoreBoardToken.setFitWidth(20);
        scoreBoardToken.setImage(colorImg);
        scoreBoardToken.setId(clientNickname);
        tokenLocation.getChildren().add(scoreBoardToken);
    }

    /**
     * This method is called by the GUI manager through override when the Server responds to the dealCards request from
     * the client with the information of the cards that the client got
     * @param handDeckIDs is a reference to an ArrayList containing the ID of the cards in the Client handDeck
     */
    @Override
    public void dealCards (ArrayList<Integer> handDeckIDs) {
        this.globalEventLabel.setText("Cards dealing");
        this.cardFaceShown = true;
        this.updateHandDeck(handDeckIDs);
        this.faceButton.setDisable(false);
        this.setCommonBoardActions();

        for (Node n : this.handDeck.getChildren()) {
            ImageView handCard = (ImageView) n;
            handCard.setOnMouseClicked(this::placingCardSelection);
            this.disableHandDeck();
        }

        List<SmallCard> cardsOnBoard = client.getSmallModel().getCommonBoard();
        final List<Integer> resource = new ArrayList<>();
        final List<Integer> golden = new ArrayList<>();
        final List<Integer> aim = new ArrayList<>();

        int slider;
        for (int i = 0; i<3; i++) {
            resource.add(cardsOnBoard.get(i).getCardID());
        }
        slider = resource.removeLast();
        resource.addFirst(slider);
        for (int i = 3; i<6; i++) {
            golden.add(cardsOnBoard.get(i).getCardID());
        }
        slider = golden.removeLast();
        golden.addFirst(slider);
        for (int i = 6; i<9; i++) {
            aim.add(cardsOnBoard.get(i).getCardID());
        }
        slider = aim.removeLast();
        aim.addFirst(slider);

        this.updateCommonBoard(resource, golden, aim);


        this.globalEventLabel.setText("");
    }


    /**
     * This method is called by override from the GUI manager after the net notifies the client with the information regarding
     * his AimCards.
     * This method proceeds to show the two AimCards on screen and assigns the respective onClick event behaviour to the imageView shown
     * @param aimIDs is a reference to a List containing the IDs of the AimCards
     */
    @Override
    public void aimCardsInfo (List<Integer> aimIDs) {
        this.globalEventLabel.setText("Choose an AimCard");
        this.primaryEvent = new Group();
        Pane paneToOperate = (Pane) this.root;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Image aimImg1 = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aimIDs.getFirst(), "FRONT")));
        Image aimImg2 = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aimIDs.getLast(), "FRONT")));

        ImageView aimCard1 = new ImageView(aimImg1);
        aimCard1.setFitHeight(73);
        aimCard1.setFitWidth(110);
        aimCard1.setX(350);
        aimCard1.setY(150);

        ImageView aimCard2 = new ImageView(aimImg2);
        aimCard2.setFitHeight(73);
        aimCard2.setFitWidth(110);
        aimCard2.setX(570);
        aimCard2.setY(150);

        aimCard1.setId(fetcher.findCardResource(aimIDs.getFirst(), "FRONT"));
        aimCard2.setId(fetcher.findCardResource(aimIDs.getLast(), "FRONT"));

        aimCard1.setOnMouseClicked(this::aimCardSelection);
        aimCard2.setOnMouseClicked(this::aimCardSelection);

        this.primaryEvent.getChildren().add(this.eventRectangleCreator());
        this.primaryEvent.getChildren().add(aimCard1);
        this.primaryEvent.getChildren().add(aimCard2);
        paneToOperate.getChildren().add(this.primaryEvent);
    }

    /**
     * This method is called by the GUI manager through override when another player is choosing his AimCard
     * @param nickname is the nickname of the player that is choosing his AimCard
     */
    @Override
    public void showPassiveAimCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his AimCard");
    }

    /**
     * This method is called by override from the GUI manager after the net notifies the clients with the information regarding
     * the new playersOrder ongoing for the rest of the Game
     * @param namesInOrder is a reference to a List containing the names of the player sorted by their turn's right to play
     */
    @Override
    public void playersOrder (List<String> namesInOrder) {
        for (int i = 0; i < namesInOrder.size(); i++) {
            Label playerLabel = (Label) this.playersTab.getChildren().get(i);
            playerLabel.setId(namesInOrder.get(i));
            playerLabel.setText(namesInOrder.get(i));
        }
        this.firstPlayer = namesInOrder.getFirst();

        this.rearrangeTokenOnBillBoard();

        this.globalEventLabel.setText(namesInOrder.getFirst() + " is playing...");
        this.turnEventLabel.setText(namesInOrder.getFirst() + "'s turn");

        this.boardsButton.setDisable(false);

        this.cardGridUpdate();
    }


    //EVENT METHODS
    //ROUND

    /**
     * This method is called when the Client chooses what card he wants to place, the scene draws the possible coordinates
     * @param e is the click on the Card Imageview in handDeck
     */
    private void placingCardSelection (MouseEvent e) {
        this.globalEventLabel.setText("Select the Coordinates");
        ImageView panel = (ImageView) e.getSource();

        this.placingChoice = panel.getId();

        this.disableHandDeck();

        ArrayList<Coordinates> placingCoordinates = this.client.getSmallModel().getPlacingCoordinates();


        this.placingCoordinatesPrevisions = new Group();
        Label coordsPrevision;
        for (Coordinates coordinates : placingCoordinates) {
            int x = coordinates.getX();
            int y = coordinates.getY();
            coordsPrevision = new Label("(" + x + "," + y + ")");

            this.coordinatePrevisionDecorator(coordsPrevision);
            this.coordinatesPrevisionsLocation(coordsPrevision, coordinates);

            coordsPrevision.setOnMouseClicked(this::coordinatesSelection);

            this.placingCoordinatesPrevisions.getChildren().add(coordsPrevision);
        }

        this.cardGrid.getChildren().add(this.placingCoordinatesPrevisions);
    }

    /**
     * This method is called when the Client decides where to place the card chosen in his on handDeck by clicking
     * one of the Coordinates previsions.
     * This method, then, proceeds to notify the server
     * @param e is the click on the Coordinates Label in cardGrid
     */
    private void coordinatesSelection (MouseEvent e) {
        Label coordsChosenLabel = (Label) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        int handCardChoice = 0;

        for (int i=0; i<this.handDeck.getChildren().size(); i++) {
            ImageView card = (ImageView) this.handDeck.getChildren().get(i);
            if (this.placingChoice.equals(card.getId())) handCardChoice = i;
        }

        Coordinates coordsChosen = fetcher.coordinatesFromString(coordsChosenLabel.getText());
        String cardFace = fetcher.faceFromURL(this.placingChoice);

        this.cardGrid.getChildren().removeLast();

        this.client.getNetworkManager().send(new PlacingMessage(client.getNickname(), handCardChoice, coordsChosen,cardFace));
    }

    /**
     * This method is called when the Client chooses what Card draw from the CommonBoard and then proceeds to notify the Server
     * @param e is the click on the Card Imageview in CommonBoard
     */
    private void drawSelection (MouseEvent e) {
        ImageView cardSelected = (ImageView) e.getSource();

        String url = cardSelected.getId();

        String choice = this.choiceDrawSelector(url);
        int selection = this.selectionDrawSelector(url);

        this.deactivateCommonBoard();

        this.client.getNetworkManager().send(new DrawMessage(client.getNickname(), choice, selection));

    }

    //NET METHODS
    //ROUND

    /**
     * This method is called by the GUI manager through override when the Client has the right to place a card on the grid.
     * It enables the placing functions on the handDeck
     * @param myHand is a reference to a List containing the card IDs of the Client handDeck
     * @param myGrid is a reference to a List containing the cards in the Client cardGrid
     */
    @Override
    public void placing (List<Integer> myHand, List<SmallCard> myGrid) {
        this.globalEventLabel.setText("Select a Card to place from your hand");
        this.turnEventLabel.setText(client.getNickname() + "'s turn");
        this.updateHandDeck(myHand);
        for (Node n : this.handDeck.getChildren()) {
            ImageView card = (ImageView) n;
            card.setDisable(false);
        }
    }

    /**
     * This method is called by the GUI manager through override when the Client placing has got a positive feedback by
     * the server.
     * Then, it proceeds to enable the draw function for the CommonBoard
     */
    @Override
    public void positivePlacing () {
        this.cardGridUpdate();

        this.updateScoreBoard(this.client.getNickname());
        this.disableHandDeck();
        this.activateCommonBoard();
        this.globalEventLabel.setText("Draw a Card");
    }

    /**
     * This method is called by the GUI manager through override when the Client placing has got a negative feedback by
     * the server.
     * Then, it proceeds to enable again the placing feature from the Client handDeck
     */
    @Override
    public void negativePlacing() {
        this.globalEventLabel.setText("Something went wrong during the placing... Try again");
        for (Node n : this.handDeck.getChildren()) {
            ImageView card = (ImageView) n;
            card.setDisable(false);
        }
    }

    /**
     * This method is called by the GUI manager through override when another player is placing a card
     * @param nickname is the nickname of the player that is active in the current turn
     */
    @Override
    public void passivePlacingState(String nickname) {
        this.globalEventLabel.setText(nickname + " is playing...");
        this.turnEventLabel.setText(nickname + "'s turn");
    }

    /**
     * This method is called by the GUI manager through override when another player has placed a card.
     * Then, it proceeds to refresh the score of everyone on the scoreboard to check differences
     * @param nickname is the nickname of the player that is active in the current turn
     */
    @Override
    public void passivePlacingResult (String nickname) {
        this.updateScoreBoard(nickname);
    }

    /**
     * This method is called by the GUI manager through override when a draw has given a positive feedback from the server.
     * Then, it proceeds to refresh the CommonBoard and the handDeck
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    @Override
    public void positiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        this.updateCommonBoard(resource, golden, aim);
        final List<Integer> handDeck = new ArrayList<>();
        for (SmallCard s: this.client.getSmallModel().getMyHand()) {
            handDeck.add(s.getCardID());
        }
        this.updateHandDeck(handDeck);
    }

    /**
     * This method is called by the GUI manager through override when another player has made a draw.
     * Then, it proceeds to refresh the CommonBoard
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    @Override
    public void passiveDraw(List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        this.updateCommonBoard(resource, golden, aim);
    }

    /**
     * This method is called by the GUI manager through override when a draw has given a negative feedback from the server.
     * Then, it proceeds to enable again the CommonBoard draw feature in the Cards ImageView
     */
    @Override
    public void negativeDraw() {
        activateCommonBoard();
    }


    /**
     * This method is called by the GUI manager through override when the lastRounds condition is triggered.
     * It proceeds to show a special label on the screen as a marker of the event
     * @param nickname is the name of the client that triggered the lastRounds condition
     */
    @Override
    public void lastRounds (String nickname) {
        this.finalRounds.setVisible(true);
    }

    /**
     * This method is called by the GUI manager through override when the endgame is reached, so the lastRounds ended.
     * It proceeds to switch scene and announce the winners
     * @param winners is a reference to the List containing the names of the winners
     * @throws IOException in case the endgame.fxml is not correctly loaded
     */
    @Override
    public void endGame(List<String> winners) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/endgame.fxml"));
        this.root = fxmlLoader.load();

        this.scene = new Scene(root);
        EndGameController endgameController = fxmlLoader.getController();
        endgameController.setClient(this.client);
        HelloApplication.controller = endgameController;
        this.stage.setScene(this.scene);

        endgameController.setStage(this.stage);
        endgameController.setScene(this.scene);
        endgameController.setRoot(this.root);
        endgameController.sceneSetup(winners);

        this.stage.show();
    }

    //UTILITY METHODS

    /**
     * This method updates the CommonBoard on the GUI when invoked
     * @param resource is the reference to a List containing the resource deck and plate cards IDs
     * @param golden is the reference to a List containing the golden deck and plate cards IDs
     * @param aim is the reference to a List containing the aim deck and plate cards IDs
     */
    @Override
    public void updateCommonBoard( List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        //Resource
        for (int i = 0; i < 3 ; i++) {
            ImageView imageView = (ImageView) this.commonBoard.getChildren().get(i);
            GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
            Image cardImg;
            if (i == 0) {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(resource.get(i), "BACK")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(resource.get(i), "BACK"));
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(resource.get(i), "FRONT")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(resource.get(i), "FRONT"));
            }
        }
        //Golden
        for (int i = 0; i<3; i++) {
            ImageView imageView = (ImageView) this.commonBoard.getChildren().get(i+3);
            GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
            Image cardImg;
            if (i == 0) {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(golden.get(i), "BACK")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(golden.get(i), "BACK"));
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(golden.get(i), "FRONT")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(golden.get(i), "FRONT"));
            }
            imageView.setImage(cardImg);
        }
        //Aim
        for (int i = 0; i<3; i++) {
            ImageView imageView = (ImageView) this.commonBoard.getChildren().get(i+6);
            GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
            Image cardImg;
            if (i == 0) {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aim.get(i), "BACK")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(aim.get(i), "BACK"));
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aim.get(i), "FRONT")));
                imageView.setImage(cardImg);
                imageView.setId(fetcher.findCardResource(aim.get(i), "FRONT"));
            }
            imageView.setImage(cardImg);
        }
    }

    /**
     * This method updates the handDeck on the GUI when invoked
     * @param handDeckIDs is a reference to a List containing the card IDs of the Client handDeck
     */
    public void updateHandDeck (List<Integer> handDeckIDs) {
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        if (handDeckIDs.size()>2) {
            for (int i=0; i<3; i++) {
                ImageView card = (ImageView) this.handDeck.getChildren().get(i);
                Image cardImg;
                if (this.cardFaceShown) {
                    cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(handDeckIDs.get(i), "FRONT")));
                    card.setId(fetcher.findCardResource(handDeckIDs.get(i), "FRONT"));
                } else {
                    cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(handDeckIDs.get(i), "BACK")));
                    card.setId(fetcher.findCardResource(handDeckIDs.get(i), "BACK"));
                }
                card.setImage(cardImg);
            }
        } else {
            for (int i=0; i<2; i++) {
                ImageView card = (ImageView) this.handDeck.getChildren().get(i);
                Image cardImg;
                if (this.cardFaceShown) {
                    cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(handDeckIDs.get(i), "FRONT")));
                    card.setId(fetcher.findCardResource(handDeckIDs.get(i), "FRONT"));
                } else {
                    cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(handDeckIDs.get(i), "BACK")));
                    card.setId(fetcher.findCardResource(handDeckIDs.get(i), "BACK"));
                }
                card.setImage(cardImg);
            }
        }
    }

    /**
     * This method creates an Arraylist of cardIDs referring to the IDs on the handDeck
     * @return an Arraylist of cardIDs referring to the IDs on the handDeck
     */
    private ArrayList<Integer> getIDsFromHandDeck() {
        ArrayList<Integer> handDeckIDs = new ArrayList<>();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (Node node : this.handDeck.getChildren()) {
            int id = fetcher.cardIDFromURL(node.getId());
            handDeckIDs.add(id);
        }
        return handDeckIDs;
    }

    /**
     * This method creates a rectangle responsible to catch the eye during the first round and to give a background
     * to the primary events (StartingCard, Token and AimCard selection)
     * @return a decorated Rectangle
     */
    private Rectangle eventRectangleCreator() {
        Rectangle r = new Rectangle(389,200, Paint.valueOf("white"));
        r.setLayoutX(319);
        r.setLayoutY(100);
        return r;
    }

    /**
     * This method, when invoked, changes the order of the token in the billboard as a consequence of the DecidePlayerOrder
     * event in the Game
     */
    private void rearrangeTokenOnBillBoard () {
        for (Node n: this.tokenTab.getChildren()) {
            ImageView tokenImgView = (ImageView) n;
            for (int i = 0; i<this.playersTab.getChildren().size(); i++) {
                Label playerLabel = (Label) this.playersTab.getChildren().get(i);
                if (tokenImgView.getId().equalsIgnoreCase(playerLabel.getText()))  {
                    if (i == 0) tokenImgView.setY(80);
                    else tokenImgView.setY(80 + (55*i));
                }
            }
        }
    }

    /**
     * This method, when invoked, disables the placing feature for the handDeck cards
     */
    private void disableHandDeck() {
        for (Node n : this.handDeck.getChildren()) {
            ImageView handCard = (ImageView) n;
            handCard.setDisable(true);
        }
    }

    /**
     * This method redraws the personal cardGrid when invoked
     */
    private void cardGridUpdate() {
        //CardGrid
        this.cardGrid.getChildren().removeLast();
        List<SmallCard> cardGrid = new ArrayList<>(this.client.getSmallModel().getMyGrid());
        this.cardPlacedInGrid = new Group();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (SmallCard s : cardGrid) {
            ImageView cardToDraw = new ImageView();
            cardToDraw.setFitWidth(72);
            cardToDraw.setFitHeight(48);
            Image cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(s.getCardID(), s.getFace())));
                cardToDraw.setImage(cardImg);
            this.placeCard(cardToDraw, s.getCoordinates());
        }
        //TOKEN
        ImageView tokenImgView = new ImageView();
        this.tokenOnCard(tokenImgView);
        tokenImgView.setImage(new Image(getClass().getResourceAsStream(fetcher.findTokenResource(this.token))));
        this.cardPlacedInGrid.getChildren().add(tokenImgView);

        //BLACK TOKEN
        Label firstPlayer = (Label) this.playersTab.getChildren().getFirst();
        if (this.client.getNickname().equalsIgnoreCase(firstPlayer.getText())) {
            ImageView blackTokenImgView = new ImageView();
            this.blackTokenOnCard(blackTokenImgView);
            blackTokenImgView.setImage(new Image(getClass().getResourceAsStream(fetcher.findTokenResource("black"))));
            this.cardPlacedInGrid.getChildren().add(blackTokenImgView);
        }
        this.cardGrid.getChildren().add(this.cardPlacedInGrid);
    }

    /**
     * This method, when invoked, set the commonBoard draw feature for the ImageViews
     */
    private void setCommonBoardActions() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setOnMouseClicked(this::drawSelection);
            card.setDisable(true);
        }
    }

    /**
     * This method, when invoked, enables the commonBoard draw feature for the ImageViews
     */
    private void activateCommonBoard() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setDisable(false);
        }
    }

    /**
     * This method, when invoked, disables the commonBoard draw feature for the ImageViews
     */
    private void deactivateCommonBoard() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setDisable(true);
        }
    }

    /**
     * This method creates a String by confronting the commonBoard Children IDs with the url to catch
     * from which type of cards is the Client making the draw: resource or golden.
     * Then it proceeds to format the result to the draw message choice String
     * @param url is the ID of the Card in the commonBoard on which we want to check
     * @return "res" or "gold" depending on where the Client has drawn the Card
     */
    private String choiceDrawSelector(String url) {
        int choice = -1;
        for (int i=0; i<6; i++) {
            if (url.equals(this.commonBoard.getChildren().get(i).getId()))
                choice = i;
        }
        if (choice>=0 && choice<3) {
            return "res";
        } else if (choice>=3 && choice<6) {
            return "gold";
        } else {
            return null;
        }
    }

    /** This method creates an int by confronting the commonBoard Children IDs with the url to catch
     * from which position is the Client making the draw: Deck (2), Plate(0), Plate(1)
     * Then it proceeds to format the result to the draw message choice String
     * @param url is the ID of the Card in the commonBoard on which we want to check
     * @return the position of the Card in the CommonBoard that the client is drawing
     */
    private int selectionDrawSelector(String url) {
        int choice = -1;
        for (int i=0; i<6; i++) {
            if (url.equals(this.commonBoard.getChildren().get(i).getId()))
                choice = i;
        }
        if (choice == 0 || choice == 3) {
            return 2;
        } else if (choice == 1 || choice == 4) {
            return 0;
        } else if (choice == 2 || choice == 5) {
            return 1;
        } else return -44;
    }

    /**
     * This method places a Card in the cardGrid by rotating the Axis of the Coordinates used for the Game to the JavaFX StackPaneCoordinates
     * @param cardImgView is the reference to the ImageViewContaining the card to place
     * @param coords is the reference to desired coordinates to place the card
     */
    private void placeCard(ImageView cardImgView, Coordinates coords) {

        int x = coords.getX();
        int y = coords.getY();

        double xPrime = x * Math.cos(Math.toRadians(45)) + y * Math.sin(Math.toRadians(45));
        double yPrime = x * Math.sin(Math.toRadians(45)) - y * Math.cos(Math.toRadians(45));
        double xTranslation = 0.0;
        double yTranslation = 0.0;

        if (xPrime>0) {
            xTranslation += 81.57 *xPrime;
            yTranslation += 20.0;
        } else if (xPrime<0){
            xTranslation += 82.57*xPrime;
            yTranslation += 20.0;
        }
        if (yPrime>0) {
            yTranslation += -20 + 40*yPrime;
        } else if (yPrime<0) {
            yTranslation += -20 + 40 *yPrime;
        }


        cardImgView.setX(xTranslation);
        cardImgView.setY(yTranslation);

        this.cardPlacedInGrid.getChildren().add(cardImgView);

    }

    /**
     * This method updates the ScoreBoard by moving the Tokens in the correct place linked to one Player's score
     * @param nickname is the nickname of the player that triggered the update
     */
    public void updateScoreBoard (String nickname) {
        int score = this.client.getSmallModel().getScoreBoard().get(nickname);
        ImageView scoreBoardToken;

        for (int i=1; i<this.scoreBoard.getChildren().size(); i++) {
            if (score!=i-1) {
                Pane tokenLocation = (Pane) this.scoreBoard.getChildren().get(i);
                if (!tokenLocation.getChildren().isEmpty()) {
                    for (int j=0; j<tokenLocation.getChildren().size(); j++) {
                        ImageView tmpImgView = (ImageView) tokenLocation.getChildren().get(j);
                        if (tmpImgView.getId().equalsIgnoreCase(nickname)) {
                            scoreBoardToken = tmpImgView;
                            tokenLocation.getChildren().remove(tmpImgView);
                            if (score>0 && score<30) {
                                Pane paneToOperate = (Pane) this.scoreBoard.getChildren().get(score+1);
                                this.tokenScoreBoardDecorator(paneToOperate, scoreBoardToken);
                                paneToOperate.getChildren().add(scoreBoardToken);
                            } else {
                                Pane paneToOperate = (Pane) this.scoreBoard.getChildren().getLast();
                                this.tokenScoreBoardDecorator(paneToOperate, scoreBoardToken);
                                paneToOperate.getChildren().add(scoreBoardToken);
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * This method sets the layout of a coordinatePrevision label in the cardGrid by rotating the Axis of the
     * Coordinates used for the Game to the JavaFX StackPaneCoordinates
     * @param coordsLabel is the label to position
     * @param coordinates are the coordinates of the prevision
     */
    private void coordinatesPrevisionsLocation (Label coordsLabel, Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        double xPrime = x * Math.cos(Math.toRadians(45)) + y * Math.sin(Math.toRadians(45));
        double yPrime = x * Math.sin(Math.toRadians(45)) - y * Math.cos(Math.toRadians(45));
        double xTranslation = 0.0;
        double yTranslation = 0.0;

        if (xPrime>0) {
            xTranslation = 30 + 50*xPrime;
        } else if (xPrime<0){
            xTranslation = -40 + 50*xPrime;
        }
        if (yPrime>0) {
            yTranslation = 15 + 50*yPrime;
        } else if (yPrime<0) {
            yTranslation = -15 + 50 *yPrime;
        }

        coordsLabel.setTranslateX(xTranslation);
        coordsLabel.setTranslateY(yTranslation);
    }

    //DECORATORS

    /**
     * This method takes an ImageView containing a token and resizes it to be shown on the scoreBoard.
     * It also sets the X coordinate on the screen to avoid a 100% overlapping
     * @param p is a reference to the pane where the token will be placed and is used to check if there are other tokens in that position
     * @param tokenImgView is the token to add in the Pane
     */
    private void tokenScoreBoardDecorator (Pane p, ImageView tokenImgView) {
        if (p.getChildren().size() > 0) {
            ImageView lastToken = (ImageView) p.getChildren().getLast();
            tokenImgView.setX(lastToken.getX() + 5);
        } else {
            tokenImgView.setX(-3);
        }
        tokenImgView.setFitHeight(20);
        tokenImgView.setFitWidth(20);
    }

    private void coordinatePrevisionDecorator (Label coordsLabel) {
        coordsLabel.setStyle("-fx-text-fill: black; -fx-background-color: yellow");
    }

    /**
     * Decorator method that resizes a StartingCard
     * @param startingCard is the ImageView to resize
     */
    private void startingCardDecorator (ImageView startingCard) {
        startingCard.setFitWidth(72);
        startingCard.setFitHeight(48);
    }

    /**
     * Decorator method that resizes a token to be placed on the StartingCard as requirements
     * @param tokenImgView is the ImageView to resize
     */
    private void tokenOnCard(ImageView tokenImgView) {
        tokenImgView.setFitHeight(20);
        tokenImgView.setFitWidth(20);
        tokenImgView.setTranslateX(25);
    }

    /**
     * Decorator method that resizes the black Token to be placed on the StartingCard as requirements
     * @param blackTokenImgView is the ImageView to resize
     */
    private void blackTokenOnCard(ImageView blackTokenImgView) {
        blackTokenImgView.setFitHeight(20);
        blackTokenImgView.setFitWidth(20);
        blackTokenImgView.setTranslateY(-20);
        blackTokenImgView.setTranslateX(25);
    }
}

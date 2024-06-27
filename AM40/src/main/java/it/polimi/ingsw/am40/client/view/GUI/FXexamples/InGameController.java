package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

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

//TODO add JAVADOC
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


    private Group primaryEvent;

    private Group tokenTab;

    private Group commonBoardTokens;

    private String placingChoice;

    private Group placingCoordinatesPrevisions;
    private String token;

    Map<String, String> playersNameAndToken;

    String firstPlayer;

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
     * Reference to the Label located in the
     */
    @FXML
    private Label globalEventLabel;

    @FXML
    private Label turnEventLabel;

    @FXML
    private Pane scoreBoard;

    @FXML
    private Button boardsButton;


    @FXML
    private StackPane cardGrid;

    @FXML
    private Button quitButton;



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


    @FXML
    public void chat() {

    }

    @FXML
    public void quit (ActionEvent e) {

    }

    @FXML
    public void face(ActionEvent e) {
        if (this.cardFaceShown) this.cardFaceShown = false;
        else this.cardFaceShown = true;
        this.updateHandDeck(this.getIDsFromHandDeck());
    }

    @FXML
    public void boards() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/boards.fxml"));
        Parent root = fxmlLoader.load();

        Scene s = new Scene(root);
        BoardsController boardsController = fxmlLoader.getController();
        boardsController.setClient(this.client);
        //Fixme try without this
        //HelloApplication.controller = inGameController;

        boardsController.setStage(this.stage);
        boardsController.setScene(s);
        boardsController.setRoot(root);

        boardsController.sceneSetup(this, this.playersNameAndToken, this.firstPlayer);


        this.stage.setScene(s);

        this.stage.show();
    }


    /**
     * This method is called when the Game is starting in order to load the first graphical components
     * @param nicknames is a reference to an ArrayList containing the name of the players
     * @param resource is a reference to a List containing the IDs of CommonBoard ResourceCards
     * @param golden is a reference to a List containing the IDs of CommonBoard GoldenResourceCards
     * @param aim is a reference to a List containing the IDs of CommonBoard AimCards
     */
    public void startingSetup(ArrayList<String> nicknames,  List<Integer> resource, List<Integer> golden, List<Integer> aim) {
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
        //
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


    public void tokenSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        //FIXME for, make it for each by putting the tokens in the last node
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
    @Override
    public void startingCardInfo(int cardID) {
        //FIXME add rectangle to primaryEvent
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


    @Override
    public void showPassiveStartingCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Starting Card");
    }



    @Override
    public void tokenInfo(List<String> tokens) {
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

    @Override
    public void showPassiveToken (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Token");
    }

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
        //FIXME Check index problems in case
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


    @Override
    public void aimCardsInfo(List<Integer> aimIDs) {
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


    @Override
    public void showPassiveAimCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his AimCard");
    }

    @Override
    public void playersOrder(List<String> namesInOrder) {
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



    //ROUND

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

    @Override
    public void positivePlacing () {
        this.cardGridUpdate();

        //FIXME ADD NULL CARD WHEN UPGRADE HERE
        /*
        List<Integer> handDeck = new ArrayList<>();
        for (SmallCard sC : this.client.getSmallModel().getMyHand()) {
            handDeck.add(sC.getCardID());
        }

        this.updateHandDeck(handDeck);

         */
        this.updateScoreBoard(this.client.getNickname());
        this.disableHandDeck();
        this.activateCommonBoard();
        this.globalEventLabel.setText("Draw a Card");
    }

    private void placingCardSelection (MouseEvent e) {
        this.globalEventLabel.setText("Select the Coordinates");
        ImageView panel = (ImageView) e.getSource();

        this.placingChoice = panel.getId();
        System.out.println("Placing selection " + this.placingChoice);

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


    @Override
    public void positiveDraw (List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        this.updateCommonBoard(resource, golden, aim);
        final List<Integer> handDeck = new ArrayList<>();
        for (SmallCard s: this.client.getSmallModel().getMyHand()) {
            handDeck.add(s.getCardID());
        }
        this.updateHandDeck(handDeck);
    }

    @Override
    public void passiveDraw(List<Integer> resource, List<Integer> golden, List<Integer> aim) {
        this.updateCommonBoard(resource, golden, aim);
    }

    @Override
    public void negativePlacing() {
        this.globalEventLabel.setText("Something went wrong during the placing... Try again");
        for (Node n : this.handDeck.getChildren()) {
            ImageView card = (ImageView) n;
            card.setDisable(false);
        }
    }

    @Override
    public void passivePlacingState(String nickname) {
        this.globalEventLabel.setText(nickname + " is playing...");
        this.turnEventLabel.setText(nickname + "'s turn");
    }

    @Override
    public void passivePlacingResult (String nickname) {
        this.updateScoreBoard(nickname);
    }
    //Utility methods


    //FIXME remove all these fetchers initialization
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


    private ArrayList<Integer> getIDsFromHandDeck() {
        ArrayList<Integer> handDeckIDs = new ArrayList<>();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (Node node : this.handDeck.getChildren()) {
            int id = fetcher.cardIDFromURL(node.getId());
            handDeckIDs.add(id);
        }
        return handDeckIDs;
    }

    private Rectangle eventRectangleCreator() {
        Rectangle r = new Rectangle(389,200, Paint.valueOf("white"));
        r.setLayoutX(319);
        r.setLayoutY(100);
        return r;
    }

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

    private void cardGridUpdate() {
            this.cardGrid.getChildren().removeLast();
        List<SmallCard> cardGrid = new ArrayList<>(this.client.getSmallModel().getMyGrid());
        this.cardPlacedInGrid = new Group();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (SmallCard s : cardGrid) {
            ImageView cardToDraw = new ImageView();
            cardToDraw.setFitWidth(72);
            cardToDraw.setFitHeight(48);
            Image cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(s.getCardID(), s.getFace())));
            System.out.println(fetcher.findCardResource(s.getCardID(), s.getFace()));
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


    private void setCommonBoardActions() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setOnMouseClicked(this::drawSelection);
            card.setDisable(true);
        }
    }


    private void drawSelection(MouseEvent e) {
        ImageView cardSelected = (ImageView) e.getSource();

        String url = cardSelected.getId();

        String choice = this.choiceDrawSelector(url);
        int selection = this.selectionDrawSelector(url);

        System.out.println(choice + "  " + selection);

        this.deactivateCommonBoard();

        this.client.getNetworkManager().send(new DrawMessage(client.getNickname(), choice, selection));

    }

    private void activateCommonBoard() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setDisable(false);
        }
    }

    private void deactivateCommonBoard() {
        for (int i = 0; i < 6; i++) {
            ImageView card = (ImageView) this.commonBoard.getChildren().get(i);
            card.setDisable(true);
        }
    }

    private String choiceDrawSelector(String url) {
        System.out.println(url);
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
            //FIXME this serves as a checker
            return null;
        }
    }

    private int selectionDrawSelector(String url) {
        System.out.println(url);
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
        } else return -44; //FIXME to remove
    }


    private void placeCard(ImageView cardImgView, Coordinates coords) {

        int x = coords.getX();
        int y = coords.getY();

        double xPrime = x * Math.cos(Math.toRadians(45)) + y * Math.sin(Math.toRadians(45));
        double yPrime = x * Math.sin(Math.toRadians(45)) - y * Math.cos(Math.toRadians(45));
        System.out.println(x +" "+ y +" --> "+ xPrime + " " + yPrime);
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

    private void disableHandDeck() {
        for (Node n : this.handDeck.getChildren()) {
            ImageView handCard = (ImageView) n;
            handCard.setDisable(true);
        }
    }

    //Decorators
    private void coordinatePrevisionDecorator (Label coordsLabel) {
        coordsLabel.setStyle("-fx-text-fill: black; -fx-background-color: yellow");
    }

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

    private void startingCardDecorator (ImageView startingCard) {
        startingCard.setFitWidth(72);
        startingCard.setFitHeight(48);
    }

    private void tokenOnCard(ImageView tokenImgView) {
        tokenImgView.setFitHeight(20);
        tokenImgView.setFitWidth(20);
        tokenImgView.setTranslateX(25);
    }

    private void blackTokenOnCard(ImageView blackTokenImgView) {
        blackTokenImgView.setFitHeight(20);
        blackTokenImgView.setFitWidth(20);
        blackTokenImgView.setTranslateY(-20);
        blackTokenImgView.setTranslateX(25);
    }

    @Override
    public void lastRounds (String nickname) {
        this.globalEventLabel.setText(nickname + " triggered the last rounds condition");
    }

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
}

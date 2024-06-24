package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.AimCardChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

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


    //FIXME add a new Node as temporary node in order to delete all the tokens
    private int groupQuantity;



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
    public void face(ActionEvent e) {
        if (this.cardFaceShown) this.cardFaceShown = false;
        else this.cardFaceShown = true;
        this.updateHandDeck(this.getIDsFromHandDeck());
    }

    @FXML
    public void boards() {

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
            playerLabel.setText(nicknames.get(i));
        }
        //CommonBoard update
        this.updateCommonBoard(resource, golden, aim);
        //TODO set draw Action and disable for plate and deck
        //Deactivate the FaceButton
        this.faceButton.setDisable(true);
    }


    public void startingCardSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();

        String cardFace = fetcher.faceFromURL(panel.getId());
        Pane paneToOperate = (Pane) this.root;
        paneToOperate.getChildren().removeLast();
        paneToOperate.getChildren().removeLast();
        this.client.getNetworkManager().send(new StartingCardChoiceMessage(client.getNickname(), cardFace));
    }


    public void tokenSelection (MouseEvent e) {
        this.globalEventLabel.setText("");
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        //FIXME for, make it for each by putting the tokens in the last node
        String color = fetcher.tokenFromURL(panel.getId());
        Pane paneToOperate = (Pane) this.root;
        for (int i = 0; i<this.groupQuantity; i++) {
            paneToOperate.getChildren().removeLast();
        }
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
        paneToOperate.getChildren().removeLast();
        this.client.getNetworkManager().send(new AimCardChoiceMessage(client.getNickname(), aimChoiceID));
    }





    //NET METHODS
    @Override
    public void startingCardInfo(int cardID) {
        //FIXME finish
        this.globalEventLabel.setText("Choose a StartingCard");

        Pane paneToOperate = (Pane) this.root;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Image frontCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "FRONT")));
        Image backCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "BACK")));

        ImageView startingCardFront = new ImageView(frontCard);
        startingCardFront.setFitHeight(73);
        startingCardFront.setFitWidth(110);
        startingCardFront.setX(400);
        startingCardFront.setY(200);
        ImageView startingCardBack = new ImageView(backCard);
        startingCardBack.setFitHeight(73);
        startingCardBack.setFitWidth(110);
        startingCardBack.setX(600);
        startingCardBack.setY(200);
        startingCardBack.setId(fetcher.findCardResource(cardID, "BACK"));
        startingCardFront.setId(fetcher.findCardResource(cardID, "FRONT"));
        startingCardFront.setOnMouseClicked(this::startingCardSelection);

        startingCardBack.setOnMouseClicked(this::startingCardSelection);


        paneToOperate.getChildren().add(startingCardFront);
        paneToOperate.getChildren().add(startingCardBack);
    }


    @Override
    public void showPassiveStartingCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Starting Card");
    }



    @Override
    public void tokenInfo(List<String> tokens) {
        this.globalEventLabel.setText("Choose a Token");
        Pane paneToOperate = (Pane) this.root;
        this.groupQuantity = tokens.size();
        int tokenpadding = 100;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        for (String color: tokens) {
            Image tokenColor = new Image(getClass().getResourceAsStream(fetcher.findTokenResource(color)));
            ImageView tokenView = new ImageView(tokenColor);
            tokenView.setY(200);
            tokenView.setX(150+tokenpadding);
            tokenView.setFitHeight(70);
            tokenView.setFitWidth(70);
            tokenView.setOnMouseClicked(this::tokenSelection);
            tokenView.setId(fetcher.findTokenResource(color));
            paneToOperate.getChildren().add(tokenView);
            tokenpadding += 100;
        }

    }

    @Override
    public void showPassiveToken (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his Token");
    }



    @Override
    public void dealCards (ArrayList<Integer> handDeckIDs) {
        this.globalEventLabel.setText("Cards dealing");
        this.cardFaceShown = true;
        this.updateHandDeck(handDeckIDs);
        this.faceButton.setDisable(false);
        this.globalEventLabel.setText("");
    }


    @Override
    public void aimCardsInfo(List<Integer> aimIDs) {
        this.globalEventLabel.setText("Choose an AimCard");
        Pane paneToOperate = (Pane) this.root;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Image aimImg1 = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aimIDs.getFirst(), "FRONT")));
        Image aimImg2 = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aimIDs.getLast(), "FRONT")));

        ImageView aimCard1 = new ImageView(aimImg1);
        aimCard1.setFitHeight(73);
        aimCard1.setFitWidth(110);
        aimCard1.setX(400);
        aimCard1.setY(200);
        ImageView aimCard2 = new ImageView(aimImg2);
        aimCard2.setFitHeight(73);
        aimCard2.setFitWidth(110);
        aimCard2.setX(600);
        aimCard2.setY(200);
        aimCard1.setId(fetcher.findCardResource(aimIDs.getFirst(), "FRONT"));
        aimCard2.setId(fetcher.findCardResource(aimIDs.getLast(), "FRONT"));
        aimCard1.setOnMouseClicked(this::aimCardSelection);

        aimCard2.setOnMouseClicked(this::aimCardSelection);


        paneToOperate.getChildren().add(aimCard1);
        paneToOperate.getChildren().add(aimCard2);
    }


    @Override
    public void showPassiveAimCard (String nickname) {
        this.globalEventLabel.setText(nickname + " is choosing his AimCard");
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
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(resource.get(i), "FRONT")));
            }
            imageView.setImage(cardImg);
            System.out.println(resource.get(i));
        }
        //Golden
        for (int i = 0; i<3; i++) {
            ImageView imageView = (ImageView) this.commonBoard.getChildren().get(i+3);
            GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
            Image cardImg;
            if (i == 0) {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(golden.get(i), "BACK")));
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(golden.get(i), "FRONT")));
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
            } else {
                cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(aim.get(i), "FRONT")));
            }
            imageView.setImage(cardImg);
        }
    }

    public void updateHandDeck (ArrayList<Integer> handDeckIDs) {
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
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
}

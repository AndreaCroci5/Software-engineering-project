package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.StartingCardChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.firstRound.TokenChoiceMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//TODO add JAVADOC
public class InGameController extends GeneralController {
    private Client client;

    @FXML
    private VBox playersTab;

    @FXML
    private GridPane commonBoard;

    private Stage stage;
    private Scene scene;
    private Parent root;

    //FIXME add a new Node as temporary node in order to delete all the tokens
    private int groupQuantity;

    @FXML
    private Label globalEventLabel;







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


    @FXML
    public void chat() {

    }

    @FXML
    public void face() {

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
        //TODO set draw Action and disable for plate and deck
        //CommonBoard update
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




    //EVENT
    public void startingCardSelection (MouseEvent e) {
        ImageView panel = (ImageView) e.getSource();
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();

        String cardFace = fetcher.faceFromURL(panel.getId());
        Pane paneToOperate = (Pane) this.root;
        paneToOperate.getChildren().removeLast();
        paneToOperate.getChildren().removeLast();
        this.client.getNetworkManager().send(new StartingCardChoiceMessage(client.getNickname(), cardFace));
    }


    public void tokenSelection (MouseEvent e) {
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







    //NET
    @Override
    public void startingCardInfo(int cardID) {
        //FIXME finish
        Pane paneToOperate = (Pane) this.root;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Image frontCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "FRONT")));
        Image backCard = new Image(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "BACK")));
        System.out.println(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "FRONT")));
        System.out.println(getClass().getResourceAsStream(fetcher.findCardResource(cardID, "FRONT")));

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
    public void tokenInfo(List<String> tokens) {
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





}

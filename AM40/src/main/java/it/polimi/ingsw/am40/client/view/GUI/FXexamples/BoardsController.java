package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.smallModel.SmallCard;
import it.polimi.ingsw.am40.server.model.Coordinates;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

public class BoardsController extends GeneralController {

    //ATTRIBUTES

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

    private InGameController primaryController;

    private Map<String, String> playersAndTokens;

    @FXML
    private GridPane billBoard;

    @FXML
    private StackPane cardGrid;


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

    //METHODS
    public void sceneSetup (InGameController primaryController, Map<String, String> playersAndTokens) {
        this.primaryController = primaryController;
        this.playersAndTokens = playersAndTokens;
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        ArrayList<String> playersNames = new ArrayList<>();
        ArrayList<String> tokens = new ArrayList<>();

        for (Map.Entry<String, String> entry : playersAndTokens.entrySet()) {
            playersNames.add(entry.getKey());
            tokens.add(entry.getValue());
        }

        //Player nickname addition
        for (int i = 0; i < playersAndTokens.keySet().size(); i++) {
            Node n = this.billBoard.getChildren().get(i);
            Label playerLabel = (Label) n;
            playerLabel.setText(playersNames.get(i));
            if (playersNames.get(i).equalsIgnoreCase(this.client.getNickname()))
                playerLabel.setOnMouseClicked(this::showPersonalBoardOnClick);
            else
                playerLabel.setOnMouseClicked(this::showBoardOnClick);
        }


        //Player nickname addition
        for (int i = 0; i < playersAndTokens.keySet().size(); i++) {
            Node n = this.billBoard.getChildren().get(i+4);
            ImageView tokenImgView = (ImageView) n;
            Image tokenImg = new Image(getClass().getResourceAsStream(fetcher.findTokenResource(tokens.get(i))));
            tokenImgView.setImage(tokenImg);
        }

    }


    @FXML
    public void back(ActionEvent e) {
        this.stage.setScene(this.primaryController.getScene());
    }

    public void showPersonalBoardOnClick(MouseEvent e) {
        this.stage.setScene(this.primaryController.getScene());
    }


    public void showBoardOnClick(MouseEvent e) {
        GraphicResourceFetcher fetcher = new GraphicResourceFetcher();
        Label nameLabel = (Label) e.getSource();
        String nickname = nameLabel.getText();

        List<SmallCard> grid = client.getSmallModel().getOtherPlayersGrid().get(nickname);
        if (!this.cardGrid.getChildren().isEmpty())
            this.cardGrid.getChildren().removeLast();

        Group gridShown = new Group();

        for (SmallCard s : grid) {
            ImageView cardToDraw = new ImageView();
            cardToDraw.setFitWidth(72);
            cardToDraw.setFitHeight(48);
            Image cardImg = new Image(getClass().getResourceAsStream(fetcher.findCardResource(s.getCardID(), s.getFace())));
            cardToDraw.setImage(cardImg);
            this.cardPlacer(cardToDraw, s.getCoordinates());
            gridShown.getChildren().add(cardToDraw);
        }
        this.cardGrid.getChildren().add(gridShown);


    }


    private void cardPlacer(ImageView cardImgView, Coordinates coords) {

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

        this.cardGrid.getChildren().add(cardImgView);

    }
}

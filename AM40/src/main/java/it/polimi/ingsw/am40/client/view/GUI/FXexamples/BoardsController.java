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

public class BoardsController extends GeneralController {

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
     * Reference to the controller of the main Scene of the Game
     */
    private InGameController primaryController;

    /**
     * Reference to a Map that keeps saved as key the name of a player and as value the respective token chosen
     */
    private Map<String, String> playersAndTokens;

    /**
     * Name of the firstPlayer, in order to assign correctly the black token
     */
    private String firstPlayer;

    //FXML ATTRIBUTES

    /**
     * Reference to the GridPane int the top-right corner containing names and ImageViews for the tokens
     * associated to the players Label
     */
    @FXML
    private GridPane billBoard;

    /**
     * Reference to the StackPane containing the current cardGrid visualized (it changes, based on the player chosen)
     */
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

    /**
     * This method sets up the scene
     * @param primaryController is the reference to the controller of the main Scene of the Game
     * @param playersAndTokens is the reference to a Map that keeps saved as key the name of a player and as value the respective token chosen
     * @param firstPlayer is the name of the firstPlayer, in order to assign correctly the black token
     */
    public void sceneSetup (InGameController primaryController, Map<String, String> playersAndTokens, String firstPlayer) {
        this.primaryController = primaryController;
        this.playersAndTokens = playersAndTokens;
        this.firstPlayer = firstPlayer;

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


        //Token addition
        for (int i = 0; i < playersAndTokens.keySet().size(); i++) {
            Node n = this.billBoard.getChildren().get(i+4);
            ImageView tokenImgView = (ImageView) n;
            Image tokenImg = new Image(getClass().getResourceAsStream(fetcher.findTokenResource(tokens.get(i))));
            tokenImgView.setImage(tokenImg);
        }

    }

    /**
     * This method is called when the Client clicks on the backButton and brings the player back to the main scene
     * @param e is the mouseclick on the backButton
     */
    @FXML
    public void back(ActionEvent e) {
        this.stage.setScene(this.primaryController.getScene());
    }

    /**
     * In case a Client decides to see his personal cardGrid by clicking his name he will be brought back to the main scene
     * @param e is the mouseclick on the Label containing the name of the Client
     */
    public void showPersonalBoardOnClick(MouseEvent e) {
        this.stage.setScene(this.primaryController.getScene());
    }


    /**
     * This method enables the feature of visualizing a player's cardGrid by clicking on his label in the billBoard
     * @param e
     */
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

        //TOKEN
        ImageView tokenImgView = new ImageView();
        this.tokenOnCard(tokenImgView);
        tokenImgView.setImage(new Image(getClass().getResourceAsStream(fetcher.findTokenResource(this.playersAndTokens.get(nickname)))));
        gridShown.getChildren().add(tokenImgView);


        if (nickname.equalsIgnoreCase(this.firstPlayer)) {
            //BLACK TOKEN
            ImageView blackTokenImgView = new ImageView();
            this.blackTokenOnCard(blackTokenImgView);
            blackTokenImgView.setImage(new Image(getClass().getResourceAsStream(fetcher.findTokenResource("black"))));
            gridShown.getChildren().add(blackTokenImgView);
        }

        this.cardGrid.getChildren().add(gridShown);


    }

    /**
     * This method places a Card in the cardGrid by rotating the Axis of the Coordinates used for the Game to the JavaFX StackPaneCoordinates
     * @param cardImgView is the reference to the ImageViewContaining the card to place
     * @param coords is the reference to desired coordinates to place the card
     */
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

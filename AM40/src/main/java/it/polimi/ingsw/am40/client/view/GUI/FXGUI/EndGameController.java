package it.polimi.ingsw.am40.client.view.GUI.FXGUI;

import it.polimi.ingsw.am40.client.network.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class EndGameController extends GeneralController{
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

    //FXML ATTRIBUTES

    /**
     * Reference to the Label showing the event that has occurred
     */
    @FXML
    private Label primaryEvent;


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


    /**
     * Setup of the scene: in case winner is empty or null, the Game has been shutdown by the Server; in other cases
     * the winner or the winners will be printed on screen
     * @param winners is the Arraylist containing the winners, or not containing anything (carrying the information of forced game end)
     */
    public void sceneSetup (List<String> winners) {
        if (winners.isEmpty() || winners == null) {
            this.primaryEvent.setText("The server has interrupted the game...");
        } else if (winners.size() == 1) {
            this.primaryEvent.setText(winners.getFirst() + " has won the game!");
        } else {
            String toPrint = "";
            for (String name : winners) {
                toPrint += name;
                toPrint += " - ";
            }
            toPrint += "have won the game!";
            this.primaryEvent.setText(toPrint);
        }
    }

    /**
     * This method closes the Application when clicked
     * @param e is the mouse click on the quitButton
     */
    @FXML
    public void quit (ActionEvent e) {
        Platform.exit();
    }


}

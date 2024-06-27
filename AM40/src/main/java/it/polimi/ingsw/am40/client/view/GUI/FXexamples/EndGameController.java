package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

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


    public void sceneSetup (List<String> winners) {
        //fixme check winners == null
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

    @FXML
    public void quit (ActionEvent e) {
        Platform.exit();
    }


}

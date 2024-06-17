package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Button playButton;
    @FXML
    private Button exitButton;
    /*@FXML
    private Pane logPane;
    @FXML
    private TextField username;
    */
    private Button createButton;
    private Button joinButton;


    Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void play(ActionEvent e) {
        Pane c = (Pane) stage.getScene().getRoot();
        VBox vBox = (VBox) c.getChildren().get(3);
        vBox.getChildren().remove(playButton);
        vBox.getChildren().remove(exitButton);
        //logPane.setVisible(true);

        createButton = new Button("Create Game");
        joinButton = new Button("Join Game");
        vBox.getChildren().add(createButton);
        vBox.getChildren().add(joinButton);
    }
    @FXML
    public void exit(ActionEvent e) {
        Platform.exit();
    }
}
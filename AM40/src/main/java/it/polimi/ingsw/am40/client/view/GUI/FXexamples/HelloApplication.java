package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("intro.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Codex Naturalis");
        stage.setScene(scene);
        stage.show();
    }

    public void startGUI() {
        launch();
    }

}
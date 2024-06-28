package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.view.GUI.GUIManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represent the launch site for the GUI Application
 */
public class HelloApplication extends Application {
    //ATTRIBUTES
    /**
     * Static reference to the Client that has started the GUI
     */
    public static Client client;

    /**
     * Static reference to the controller that is used in a certain moment at Runtime.
     * It changes when there is a scene change, in order that the GUIManager when invoking a method, gets the override
     * by the implementation of the subtype controller assigned
     */
    public static GeneralController controller;

    /**
     * This method is called when the application is launched and, beyond setting up the application, it passes the Client
     * reference to the scene controller and assigns the scene controller to the static attribute controller in this class
     * @param stage is the default Stage
     * @throws IOException in case a fxml file is not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        Client tmp = client;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/intro.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        HelloController introController = (HelloController) fxmlLoader.getController();
        HelloApplication.controller = introController;
        introController.setClient(tmp);
        stage.setResizable(false);
        stage.setTitle("Codex Naturalis");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is invoked by the GUI manager when a Client choose to play the Game in Graphical User Interface mode
     */
    public void startGUI() {
        this.launch();
    }

    /**
     * Getter for the client
     * @return a reference for the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Setter for client
     * @param client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }
}
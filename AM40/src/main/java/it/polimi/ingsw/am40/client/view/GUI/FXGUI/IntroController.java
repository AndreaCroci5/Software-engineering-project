package it.polimi.ingsw.am40.client.view.GUI.FXGUI;

import it.polimi.ingsw.am40.client.network.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the controller of the intro scene
 */
public class IntroController extends GeneralController {
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



    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    //EVENT METHODS

    /**
     * This method is called when the playButton is pressed and changes the Scene to the login
     * @param e is the event that caused the changed, in this case a click from the mouse
     * @throws IOException in case a fxml file is not found
     */
    @FXML
    public void play(ActionEvent e) throws IOException {
        //Loads the next scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        this.root = fxmlLoader.load();

        //Change the current Stage and set the login scene previously loaded
        this.stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        LoginController loginController = fxmlLoader.getController();
        loginController.setClient(this.client);

        //Set the new loginController to the general controller
        GUIApplication.controller = loginController;

        //Scene change
        this.stage.setScene(this.scene);
        loginController.setStage(this.stage);
        loginController.setScene(this.scene);
        loginController.setRoot(this.root);
        this.stage.show();


    }

    /**
     * This method is called when the exitButton is pressed and closes the application
     * @param e is the click on the button
     */
    @FXML
    public void exit(ActionEvent e) {
        Platform.exit();
    }
}
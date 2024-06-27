package it.polimi.ingsw.am40.client.view.GUI.FXexamples;

import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.CreateRequestMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.GameIdChoiceMessage;
import it.polimi.ingsw.am40.client.ClientMessages.activeMessages.flow.JoinRequestMessage;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.ClientNetworkTCPManager;
import it.polimi.ingsw.am40.client.network.RMI.ClientNetworkRMIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

//TODO when ready add the "username already taken feature"

/**
 * This class is the controller of the login scene
 */
public class LoginController extends GeneralController {
    //ATTRIBUTES
    /**
     * Reference to the Client information
     */
    private Client client;

    /**
     * Reference used to temporary store the username chosen by the Client
     */
    private String username;

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
     * FXML reference in order to load the createButton
     */
    @FXML
    private Button createButton;

    /**
     * FXML reference in order to load the joinButton
     */
    @FXML
    private Button joinButton;

    @FXML
    private TextField usernameField;

    /**
     * FXML reference in order to load the label located over the TextField
     */
    @FXML
    private Label info;

    /**
     * FXML reference in order to load the joinButton
     */
    @FXML
    private Button sendButton;

    @FXML
    private Label wrongUsername;

    @FXML
    private Label parsingError;

    /**
     * Reference to a Label put on the top of the scene in order to notify the Client what happened on Server, regarding
     * the party chosen
     */
    private Label eventsNotificator;


    //Getter and Setter

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    //EVENT METHODS

    /**
     * This method is used when a Player wants to Join a party
     * @param e is the mouse click on the joinButton
     */
    @FXML
    public void join(ActionEvent e) {
        //Gets the username written by the Client in the TextField
        this.username = this.usernameField.getText();
        this.client.setNickname(this.username);
        this.usernameField.clear();

        //Disables the unnecessary Buttons
        this.createButton.setDisable(true);
        this.joinButton.setDisable(true);


        String ipAddress = null;
        int port = -1;
        ClientNetworkRMIManager rmi = null;
        ClientNetworkTCPManager tcp = null;
        switch(this.client.getNetworkManager().getUsedProtocol()){
            case TCP:
                tcp = (ClientNetworkTCPManager) this.client.getNetworkManager();
                ipAddress = tcp.getSocket().getLocalAddress().getHostAddress(); //FIXME
                port = tcp.getSocket().getLocalPort(); //FIXME
                this.client.getNetworkManager().send(new JoinRequestMessage(this.username, ipAddress, port, null));
                break;
            case RMI:
                rmi = (ClientNetworkRMIManager) this.client.getNetworkManager();
                try {
                    InetAddress localhost = InetAddress.getLocalHost();
                    ipAddress = localhost.getHostAddress();
                } catch (UnknownHostException e1) {
                    System.out.println("Error with the IP address");
                    throw new RuntimeException(e1);
                }
                this.client.getNetworkManager().send(new JoinRequestMessage(this.username, ipAddress, port, ((ClientNetworkRMIManager) this.client.getNetworkManager()).getSkeleton()));
                break;
            default:
                System.out.println("Error with the network manager protocol getting");
                throw new RuntimeException();
        }

        ;
    }


    /**
     * This method is used when a Player wants to create a party
     * @param e is the mouse click on the createButton
     */
    @FXML
    public void create(ActionEvent e) {
        //Gets the username written by the Client in the TextField
        this.username = usernameField.getText();
        this.client.setNickname(this.username);

        //Ask the Client the desired party size
        this.info.setText("Insert the party size: ");
        this.info.setLayoutX(this.info.getLayoutX()-90);

        //Disables the unnecessary Buttons
        this.createButton.setDisable(true);
        this.joinButton.setDisable(true);
        this.usernameField.clear();

        //Activate the sendButton onActionEvent
        this.sendButton.setOnAction(event -> {
            this.selectSizeOfParty();
        });
        this.sendButton.setVisible(true);
    }

    /**
     * This method is assigned as onActionEvent for the sendButton after a Client clicks the createButton
     */
    private void selectSizeOfParty() {
        //Size parsing from the TextField
        int sizeParsed;
        try {
            sizeParsed = Integer.parseInt(this.usernameField.getText());

            if (sizeParsed<2 || sizeParsed > 5) throw new NumberFormatException();

            this.parsingError.setVisible(false);
            String ipAddress = null;
            int port = -1;
            ClientNetworkRMIManager rmi = null;
            ClientNetworkTCPManager tcp = null;
            switch(this.client.getNetworkManager().getUsedProtocol()){
                case TCP:
                    tcp = (ClientNetworkTCPManager) this.client.getNetworkManager();
                    ipAddress = tcp.getSocket().getLocalAddress().getHostAddress(); //FIXME
                    port = tcp.getSocket().getLocalPort(); //FIXME
                    this.client.getNetworkManager().send(new CreateRequestMessage(this.username,sizeParsed, ipAddress, port, null));
                    break;
                case RMI:
                    rmi = (ClientNetworkRMIManager) this.client.getNetworkManager();
                    try {
                        InetAddress localhost = InetAddress.getLocalHost();
                        ipAddress = localhost.getHostAddress();
                    } catch (UnknownHostException e1) {
                        System.out.println("Error with the IP address");
                        throw new RuntimeException(e1);
                    }
                    this.client.getNetworkManager().send(new CreateRequestMessage(this.username, sizeParsed, ipAddress, port, ((ClientNetworkRMIManager) this.client.getNetworkManager()).getSkeleton()));
                    break;
                default:
                    System.out.println("Error with the network manager protocol getting");
                    throw new RuntimeException();
            }
        } catch (NumberFormatException e) {
            this.parsingError.setVisible(true);
        }
    }


    /**
     * This method is assigned as onActionEvent for the sendButton after a Client receives from the net the list of
     * the available GameIDs by the method displayAllGameIDs
     */
    public void gameIDChoice() {
        int gameIDChoice;
        try {
            gameIDChoice = Integer.parseInt(this.usernameField.getText());

            this.parsingError.setVisible(false);

            //Send the gameIDChoice
            this.client.getNetworkManager().send(new GameIdChoiceMessage(this.username, gameIDChoice));
        } catch (NumberFormatException e) {
            this.parsingError.setVisible(true);
        }




    }


    //NET EVENTS METHODS

    /**
     * This method is called by the GUIManger through override, when a Player joins the party that the Client is in, or
     * the Client creates a new a party. This method shows how many players are required before starting the Game
     * @param playersRequired is the amount of the Players required in order to start the Game
     */
    @Override
    public void waitingForPlayersEvent(int playersRequired) {
        this.wrongUsername.setVisible(false);

        Pane paneToOperate = (Pane) this.root;
        this.eventsNotificator = new Label("Waiting for " + playersRequired + " more players");
        this.labelDecorator(this.eventsNotificator);
        this.eventsNotificator.setLayoutX(385);
        this.eventsNotificator.setLayoutY(10);
        this.usernameField.setDisable(true);
        this.sendButton.setDisable(true);
        this.usernameField.clear();

        if(paneToOperate.getChildren().getLast() instanceof Label) paneToOperate.getChildren().removeLast();
        paneToOperate.getChildren().add(this.eventsNotificator);
    }

    /**
     * This method is called by the GUIManger through override, after a Client sends a join request and receives a Server response.
     * Then proceeds to show the Parties available
     * @param gameIDsNotification is the description formatted of the parties available
     */
    @Override
    public void displayAllGameIDs(String gameIDsNotification) {
        this.wrongUsername.setVisible(false);

        Label gameIDs = new Label(gameIDsNotification);
        this.labelGameIDsDecorator(gameIDs);
        this.info.setText("Write the ID of the game you want to join: ");
        this.info.setLayoutX(this.info.getLayoutX()-200);

        Pane paneToOperate =  (Pane) this.root;
        paneToOperate.getChildren().add(gameIDs);

        this.sendButton.setOnAction(event -> {
            this.gameIDChoice();
        });
        this.sendButton.setVisible(true);
    }


    /**
     * This method is called by the GUIManger through override, when the party reaches the required size and through the net arrives at the Client a GameInitData
     * that triggers a StartingGameMessage
     *
     * @param nicknames are the nicknames chosen by the Players
     * @param resource  is the situation of the ResourceCards in CommonBoard
     * @param golden is the situation of the GoldenResourceCards in CommonBoard
     * @param aim is the situation of the AimCards in CommonBoard
     * @throws IOException in case the FXML file is not found
     */
    @Override
    public void startingGame(ArrayList<String> nicknames, List<Integer> resource, List<Integer> golden, List<Integer> aim) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/setup.fxml"));
        this.root = fxmlLoader.load();

        this.scene = new Scene(root);
        InGameController inGameController = fxmlLoader.getController();
        inGameController.setClient(this.client);
        HelloApplication.controller = inGameController;
        this.stage.setScene(this.scene);

        inGameController.setStage(this.stage);
        inGameController.setScene(this.scene);
        inGameController.setRoot(this.root);
        inGameController.startingSetup(nicknames, resource, golden, aim);

        this.stage.show();
    }


    @Override
    public void failedGameID() {
        this.parsingError.setVisible(true);
    }


    //Decorator methods
    private void labelDecorator (Label l) {
        l.setFont(new Font("Papyrus", 20));
        l.setStyle("-fx-text-fill: white;");
    }

    private void labelGameIDsDecorator (Label l) {
        l.setFont(new Font("Papyrus", 13));
        l.setStyle("-fx-background-color: white;\n" +
                  "-fx-font-family: Papyrus;\n");
    }

}

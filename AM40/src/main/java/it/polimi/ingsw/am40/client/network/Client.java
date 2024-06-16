package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.UserInputReader;
import it.polimi.ingsw.am40.client.smallModel.SmallModel;
import it.polimi.ingsw.am40.client.view.ViewFactory;
import it.polimi.ingsw.am40.client.view.ViewFactoryException;
import it.polimi.ingsw.am40.client.view.ViewManager;

import java.util.Scanner;

/**
 * Client class is the main class of the client. It manages both the view and the network.
 * It is like a mediator which decide, basing on the current state, what to do.
 */
public class Client implements AbstractContext{

    //ATTRIBUTES

    /**
     * Current state of the FSM on the client
     */
    public State currentState;

    /**
     * Concrete network manager
     */
    private NetworkManagerClient networkManager;

    /**
     * Concrete view manager
     */
    private ViewManager viewManager;

    //fixme doc + revisione

    private SmallModel smallModel;

    private String nickname;

    private UserInputReader inputReader;

    public SmallModel getSmallModel() {
        return smallModel;
    }

    public State getCurrentState() {
        return currentState;
    }

    public NetworkManagerClient getNetworkManager() {
        return networkManager;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public String getNickname() {
        return nickname;
    }

    public UserInputReader getInputReader() {
        return inputReader;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setInputReader(UserInputReader inputReader) {
        this.inputReader = inputReader;
    }
//ABSTRACT CONTEXT METHODS

    /**
     * Method to set the current state of the FSM
     * @param newState the new state to set
     */
    @Override
    public void setState(State newState) {
        this.currentState = newState;

    }



    //INIT METHOD

    /**
     * Method which initializes the application with the user preferences
     *
     * @param portPrefTCP    server port preference for TCP
     * @param portPrefRMI   server port preference for RMI
     * @param hostPref    host name preference
     * @param serverPref  server address preference
     * @param network     network protocol to use
     * @param view        view system to use
     */
    public void initApplication(Integer portPrefTCP, Integer portPrefRMI, String hostPref, String serverPref, String network, String view) {

        //Creates the concrete network manager with factory pattern
        NetworkFactory netFactory = new NetworkFactory();
        try {
            this.networkManager = netFactory.createConcreteNetworkManager(network, this);
        } catch (NetworkFactoryException e) {
            System.out.println("Network protocol launching went wrong!");
            throw new RuntimeException(e);
        }

        //Creates the concrete view manager with factory pattern
        ViewFactory viewFactory = new ViewFactory();
        try {
            this.viewManager = viewFactory.createConcreteView(view, this);
        } catch (ViewFactoryException e) {
            System.out.println("View system launching went wrong!");
            throw new RuntimeException(e);
        }

        //Setting the right port preference basing on the chosen protocol

        switch (this.networkManager.getUsedProtocol()){
            case TCP:
                this.networkManager.setPort(portPrefTCP);
                break;

            case RMI:
                this.networkManager.setPort(portPrefRMI);
                break;
        }


        //Setting the host name and the server address on the networkManager
        this.networkManager.setHostName(hostPref);
        this.networkManager.setServerAddress(serverPref);

        //Trying to initialize the communication with the server on the network manager with the right preferences
        //If something goes wrong, there is a loop to retry the connection or to end the application
        try {
            networkManager.initCommunication();
        } catch (NetworkProblemException e) {
            System.out.println("First try went wrong!");
            this.takeInputFromUser();
        }


        //Starting the application with all the correct parameters from the user
        System.out.println("Client '" + this.networkManager.getHostName() + "' is now running on the network with " + this.networkManager.getUsedProtocol() + " on port " + this.networkManager.getPort());



        //"Cleaning" of the CLI
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }


        //"Launching the application" notification on client with a little delay
        System.out.println("Launching application...");
       try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Initializing the concrete view
        this.viewManager.initView();



    }



    private void takeInputFromUser(){

        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean retry = true;

        while(retry){

            System.out.println("Type 'quit' to turn off the application, 'restart' to retry the connection to the server");
            input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case ("quit"):
                    System.out.println("Turning off the application...");
                    retry = false;
                    break;

                case ("restart"):
                    System.out.println("Restarting the connection...");
                    try {
                        networkManager.initCommunication();
                    } catch (NetworkProblemException e) {
                        try {
                            networkManager.closeCommunication();
                        } catch (NetworkProblemException ex) {
                            System.out.println("Connection closing failed!");
                            throw new RuntimeException(ex);
                        }
                        System.out.println("Restarting went wrong!");
                    }
                    break;

                default:
                    break;
            }
        }

        try {
            networkManager.closeCommunication();
        } catch (NetworkProblemException e) {
            System.out.println("Connection closing went wrong!");
            throw new RuntimeException(e);
        }
        System.out.println("GOODBYE FRIEND!!!");
        System.exit(0);
    }

    public void handleMessage(Message message) {
        // based on the message it sets the new state
        message.process(this);

        // it executes the new state
        currentState.execute(this);
    }


}

package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.ClientMessages.HostNameMessage;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.UserInputReader;
import it.polimi.ingsw.am40.client.network.RMI.ClientNetworkRMIManager;
import it.polimi.ingsw.am40.client.smallModel.SmallModel;
import it.polimi.ingsw.am40.client.view.ViewFactory;
import it.polimi.ingsw.am40.client.view.ViewFactoryException;
import it.polimi.ingsw.am40.client.view.ViewManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    /**
     * Small Model class to align the information incoming from the server on the client
     */
    private SmallModel smallModel;

    /**
     * Nickname (for a party)
     */
    private String nickname;

    /**
     * Input Reader to get input from command line
     */
    private UserInputReader inputReader;





    //GETTER METHODS

    /**
     * Getter for the small model class
     * @return the reference to the small model
     */
    public SmallModel getSmallModel() {
        return smallModel;
    }

    /**
     * Getter for the current state of the FSM
     * @return the reference to the current state
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * Getter for the network manager
     * @return the reference to the concrete network manager
     */
    public NetworkManagerClient getNetworkManager() {
        return networkManager;
    }

    /**
     * Getter for the view manager
     * @return the reference to the concrete view manager
     */
    public ViewManager getViewManager() {
        return viewManager;
    }

    /**
     * Getter for the nickname
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for the input reader
     * @return the reference to the input reader
     */
    public UserInputReader getInputReader() {
        return inputReader;
    }



    //SETTER METHODS

    /**
     * Setter for nickname
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Setter for the input reader
     * @param inputReader the reference to the input reader to set
     */
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

            default:
                System.out.println("Invalid value for the used protocol in network manager!");
                throw new RuntimeException();
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

        String ipAddress = null;
        int port = -1;
        ClientNetworkRMIManager rmi = null;
        ClientNetworkTCPManager tcp = null;
        switch(this.getNetworkManager().getUsedProtocol()){
            case TCP:
                tcp = (ClientNetworkTCPManager) this.getNetworkManager();
                ipAddress = tcp.getSocket().getLocalAddress().getHostAddress();
                port = tcp.getSocket().getLocalPort();

                this.networkManager.send(new HostNameMessage(this.networkManager.getHostName(),ipAddress,port,null));

                break;
            case RMI:
                rmi = (ClientNetworkRMIManager) this.getNetworkManager();
                try {
                    InetAddress localhost = InetAddress.getLocalHost();
                    ipAddress = localhost.getHostAddress();
                } catch (UnknownHostException e) {
                    System.out.println("Error with the IP address");
                    throw new RuntimeException(e);
                }

                this.networkManager.send(new HostNameMessage(this.networkManager.getHostName(),ipAddress,port,rmi.getSkeleton()));

                break;


            default:
                System.out.println("Error with the network manager protocol getting");
                throw new RuntimeException();
        }


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

        //Initializing small model
        this.smallModel = new SmallModel();

        //Initializing the concrete view
        this.viewManager.initView();


    }

    //UTILITY METHODS

    /**
     * Method which take input parameters from user to retry the connection or to close
     * the game in case something went wrong at game starting
     */
    private void takeInputFromUser(){

        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean retry = true;

        //If retry flag is true, the while loop continues to ask the user if he wants to retry the connection or quit
        while(retry){

            System.out.println("Type 'quit' to turn off the application, 'restart' to retry the connection to the server");
            input = scanner.nextLine();

            switch (input.toLowerCase()) {

                    //Closing the application
                case ("quit"):
                    System.out.println("Turning off the application...");
                    retry = false;
                    break;

                    //Retrying the initCommunication
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
                    System.out.println("Incorrect input!");
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


    /**
     * This method processes a message and then execute it
     * @param message the message to handle
     */
    public void handleMessage(Message message) {

        // Basing on the message it sets the new state
        message.process(this);

        // It executes the new state
        currentState.execute(this);
    }

}

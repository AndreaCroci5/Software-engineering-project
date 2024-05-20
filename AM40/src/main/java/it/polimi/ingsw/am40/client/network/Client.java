package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.client.view.ViewFactory;
import it.polimi.ingsw.am40.client.view.ViewFactoryException;
import it.polimi.ingsw.am40.client.view.ViewManager;

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




    //ABSTRACT CONTEXT METHODS

    /**
     * Method to set the current state of the FSM
     *
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
            this.viewManager = viewFactory.createConcreteView(view);
            this.viewManager.setClient(this);
        } catch (ViewFactoryException e) {
            System.out.println("View system launching went wrong!");
            throw new RuntimeException(e);
        }

        //Loading of the application on client with a little delay
        System.out.println("Launching application...");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Cleaning of the CLI
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

        //fixme use polimorf
        switch (network){
            case "tcp":
                this.networkManager.initCommunication(portPrefTCP, hostPref, serverPref);
                //Starting the application with all the correct parameters from the user
                System.out.println("Client '" + hostPref + "' is now running on the network with " + network.toUpperCase() + " on port " + portPrefTCP);

                //Starting the application
                this.networkManager.initCommunication(portPrefTCP, hostPref, serverPref);
                break;
            case "rmi":
                this.networkManager.initCommunication(portPrefRMI, hostPref, serverPref);
                //Starting the application with all the correct parameters from the user
                System.out.println("Client '" + hostPref + "' is now running on the network with " + network.toUpperCase() + " on port " + portPrefRMI);

                //Starting the application
                this.networkManager.initCommunication(portPrefRMI, hostPref, serverPref);
                break;
        }

        this.viewManager.initView();
    }
}

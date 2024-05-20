package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.server.network.RMI.RemoteObjectServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Concrete network protocol implementation for RMI
 */
public class ClientNetworkRMIManager implements NetworkManagerClient{

    //ATTRIBUTES

    /**
     * Reference to the main class of the client
     */
    private Client client;

    /**
     * Flag attribute which says if the communication logic is on or off
     */
    private boolean communicationOn;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for ClientNetworkRMIManager
     * @param clientClass the reference to Client class
     */
    public ClientNetworkRMIManager(Client clientClass) {
        this.client = clientClass;
    }


    //NETWORK MANAGER METHODS

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    @Override
    public void initCommunication(int port, String hostName, String serverAddress) {

        Registry registry = null;

        try {
            //Getting the server registry
            registry = LocateRegistry.getRegistry(hostName);

            //Getting the server stub
            RemoteObjectServer stub = (RemoteObjectServer) registry.lookup("ServerRemote");

            //fixme retry + doc
            String response = stub.example();
            System.out.println(response);
        } catch (RemoteException e) {
            System.out.println("Something went wrong with the server remote interface research");
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.out.println("Something went wrong with the lookup on RMI registry");
            throw new RuntimeException(e);
        }


    }


    @Override
    public Client getClient() {
        return this.client;
    }

    public boolean isCommunicationOn() {
        return communicationOn;
    }

    public void setCommunicationOn(boolean communicationOn) {
        this.communicationOn = communicationOn;
    }
}

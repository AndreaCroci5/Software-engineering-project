package it.polimi.ingsw.am40.client.network;

import it.polimi.ingsw.am40.server.network.RMI.RemoteObjectServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static it.polimi.ingsw.am40.client.network.Protocol.RMI;

/**
 * Concrete network protocol implementation for RMI
 */
public class ClientNetworkRMIManager implements NetworkManagerClient{

    //ATTRIBUTES

    /**
     * Reference to the main class of the client
     */
    private Client client;

    private String hostName;

    private Integer port;

    private String serverAddress;

    public final Protocol usedProtocol = RMI;





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
    public void initCommunication() {

        Registry registry = null;

        try {
            //Getting the server registry
            registry = LocateRegistry.getRegistry();

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

    @Override
    public void closeCommunication() {

    }

    @Override
    public String getHostName() {
        return null;
    }

    @Override
    public void setHostName(String hostName) {

    }

    @Override
    public void setPort(int port) {

    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public void setServerAddress(String serverAddress) {

    }

    @Override
    public String getServerAddress() {
        return null;
    }

    @Override
    public Protocol getUsedProtocol() {
        return this.usedProtocol;
    }}

package it.polimi.ingsw.am40.client.network.RMI;

import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.NetworkManagerClient;
import it.polimi.ingsw.am40.client.network.Protocol;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.RMI.RemoteInterfaceServer;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static it.polimi.ingsw.am40.client.network.Protocol.RMI;

/**
 * Concrete network protocol implementation for RMI
 */
public class ClientNetworkRMIManager implements NetworkManagerClient {

    //ATTRIBUTES

    /**
     * Reference to the main class of the client
     */
    private Client client;

    private String hostName;

    private Integer port;

    private String serverAddress;

    public final Protocol usedProtocol = RMI;

    private RemoteInterfaceServer stub;
    private RemoteInterfaceClient skeleton;





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
        this.startStub();
        this.startSkeleton();
        System.out.println("RMI is correctly running on port " + this.port);
    }

    private void startStub(){
        Registry registry = null;

        try {
            //Getting the server registry
            registry = LocateRegistry.getRegistry(this.port);

            //Getting the server stub
            RemoteInterfaceServer stub = (RemoteInterfaceServer) registry.lookup("ServerRemote");
            this.stub = stub;
        } catch (RemoteException e) {
            System.out.println("Something went wrong with the server remote interface research");
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.out.println("Something went wrong with the lookup on RMI registry");
            throw new RuntimeException(e);
        }
    }

    private void startSkeleton(){
        try {
            RemoteInterfaceClient skeleton = (RemoteInterfaceClient) UnicastRemoteObject.exportObject(new RemoteObjectClient(), 0);
            Registry registry = LocateRegistry.getRegistry(this.port);
            registry.rebind("ClientRemote", skeleton);
            this.skeleton = skeleton;
            this.stub.registerClient(this.skeleton);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong trying to create the client remote interface on the registry");
            throw new RuntimeException(e);//fixme retry
        }
    }




    @Override
    public Client getClient() {
        return this.client;
    }

    @Override
    public void closeCommunication() {


        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(this.port);
        } catch (RemoteException e) {
            System.out.println("Something went wrong with the RMI registry");
            throw new RuntimeException(e);
        }
        try {
            registry.unbind("ServerRemote");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.out.println("Something went wrong with the registry stub unbind");
            throw new RuntimeException(e);
        }

        try {
            UnicastRemoteObject.unexportObject(this.stub, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Something went wrong with the registry unexport");
            throw new RuntimeException(e);
        }

        try {
            UnicastRemoteObject.unexportObject(registry, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Something went wrong with the registry unexport");
            throw new RuntimeException(e);
        }

        this.stub = null;
        System.out.println("RMI communication closed");
    }

    @Override
    public String getHostName() {
        return this.hostName;
    }

    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public String getServerAddress() {
        return this.serverAddress;
    }

    @Override
    public Protocol getUsedProtocol() {
        return this.usedProtocol;
    }

    @Override
    public void send(Message message) {

    }

    @Override
    public void receive(Data data) {

    }
}


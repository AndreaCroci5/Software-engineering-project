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

    /**
     * Host name for the client
     */
    private String hostName;

    /**
     * Port number of the RMI server
     */
    private Integer port;

    /**
     * Server IP address
     */
    private String serverAddress;

    /**
     * Identifier for the used protocol in this implementation
     */
    public final Protocol usedProtocol = RMI;

    /**
     * Stub for calling methods on server as remote interface in remote registry
     */
    private RemoteInterfaceServer stub;

    /**
     * Skeleton to allow the server to call methods on this client as a remote interface
     */
    private RemoteInterfaceClient skeleton;

    /**
     * Identifier of this client on the server NetworkClient class
     */
    private Integer identifier;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for ClientNetworkRMIManager
     * @param clientClass the reference to Client class
     */
    public ClientNetworkRMIManager(Client clientClass) {
        this.client = clientClass;
        this.skeleton = null;
        this.stub = null;
        this.identifier = -1;
    }



    //GETTER METHODS

    /**
     * Getter for the client
     * @return the client class as reference
     */
    @Override
    public Client getClient() {
        return this.client;
    }

    /**
     * Getter for the hostName
     * @return the hostName of the client
     */
    @Override
    public String getHostName() {
        return this.hostName;
    }

    /**
     * Getter for the port
     * @return the port number of the server
     */
    @Override
    public int getPort() {
        return this.port;
    }

    /**
     * Getter for the server address
     * @return the server address
     */
    @Override
    public String getServerAddress() {
        return this.serverAddress;
    }

    /**
     * Getter for the used protocol
     * @return the used protocol
     */
    @Override
    public Protocol getUsedProtocol() {
        return this.usedProtocol;
    }

    /**
     * Getter for the identifier
     * @return the identifier
     */
    public Integer getIdentifier() {
        return identifier;
    }

    public RemoteInterfaceClient getSkeleton() {
        return skeleton;
    }

    //SETTER METHODS

    /**
     * Setter for the hostName
     * @param hostName the hostName to set
     */
    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Setter for the port
     * @param port tho port number
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Setter for the server address
     * @param serverAddress the IP address of the server
     */
    @Override
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }



    //NETWORK MANAGER METHODS

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    @Override
    public void initCommunication() {
        //Init the server stub
        this.startStub();

        //Init the client skeleton
        this.startSkeleton();

        System.out.println("RMI is correctly running on port " + this.port);
    }

    /**
     * Method to close the client communication
     */
    @Override
    public void closeCommunication() {

        //Getting the registry
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(this.port);
        } catch (RemoteException e) {
            System.out.println("Something went wrong with the RMI registry");
            throw new RuntimeException(e);
        }


        //Trying to unregister this client from the server
        try {
            if (this.stub != null) {
                this.stub.unregisterClient(this.identifier);
            }
        } catch (RemoteException e) {
            System.out.println("Failed to unregister client remote object from server!");
            e.printStackTrace();
        }


        // Trying to unbind the client interface
        try {
            registry.unbind("ClientRemote" + this.identifier.toString());
        } catch (RemoteException e) {
            System.out.println("Failed to unbind client remote interface!");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Client remote interface not bound!");
            e.printStackTrace();
        }


        //Trying to unbind the server interface
        try {
            registry.unbind("ServerRemote");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            System.out.println("Something went wrong with the registry stub unbind");
            throw new RuntimeException(e);
        }

        //Trying to unexport the stub
        try {
            UnicastRemoteObject.unexportObject(this.stub, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Something went wrong with the registry unexport");
            throw new RuntimeException(e);
        }

        //Trying to unexport the client skeleton
        try {
            UnicastRemoteObject.unexportObject(this.skeleton, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Something went wrong with the client skeleton unexport");
            throw new RuntimeException(e);
        }

        //Trying to unexport the registry
        try {
            UnicastRemoteObject.unexportObject(registry, true);
        } catch (NoSuchObjectException e) {
            System.out.println("Something went wrong with the registry unexport");
            throw new RuntimeException(e);
        }

        this.identifier = -1;
        this.skeleton = null;
        this.stub = null;
        System.out.println("RMI communication closed");
    }


    /**
     *
     * @param message
     */
    @Override
    public void send(Message message) {
        Data d = message.messageToData();
        sendSerializedMessage(d);
    }

    /**
     * Method used to send a serialized message on the network
     *
     * @param message the message to send
     */
    @Override
    public void sendSerializedMessage(Data message) {
        message.doRMI(null, stub);
    }


    //AUXILIARY METHODS

    /**
     * Method to start the server stub on the client
     */
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

    /**
     * Method to start the client skeleton
     */
    private void startSkeleton(){
        try {
            RemoteObjectClient robjc = new RemoteObjectClient();

            // Check if the object is already exported, if so, unexport it
            try {
                UnicastRemoteObject.unexportObject(robjc, true);
            } catch (NoSuchObjectException e) {
                // It's okay if the object was not previously exported
            }

            //Exporting the remote interface
            RemoteInterfaceClient skeleton = (RemoteInterfaceClient) UnicastRemoteObject.exportObject(robjc, 0);

            robjc.setClient(this.client);

            //Setting the skeleton
            this.skeleton = skeleton;


            //Logging this client on the server with an RMI call
            this.identifier = this.stub.registerClient(this.skeleton, this.hostName);

            if(this.identifier == -1){
                System.out.println("Error! Your hostname isn't unique in the network!");
                throw new RuntimeException();
            }

            //Looking for the registry and logging the interface
            Registry registry = LocateRegistry.getRegistry(this.port);
            registry.rebind("ClientRemote" + this.identifier.toString(), skeleton);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong trying to create the client remote interface on the registry");
            throw new RuntimeException(e);
        }
    }




}


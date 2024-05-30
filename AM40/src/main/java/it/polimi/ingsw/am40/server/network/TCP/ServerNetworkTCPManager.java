package it.polimi.ingsw.am40.server.network.TCP;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.NetworkClient;
import it.polimi.ingsw.am40.server.network.virtual_view.NonExistentClientException;
import it.polimi.ingsw.am40.server.network.virtual_view.Protocol;
import it.polimi.ingsw.am40.server.network.virtual_view.VVServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ServerNetworkTCPManager is the class which manage communications (server-side) through TCP Socket protocol
 */
public class ServerNetworkTCPManager implements NetworkManagerServer {

    //ATTRIBUTES

    /**
     * Name of the server host on the network
     */
    private String hostName;

    /**
     * Port on which server accept incoming connection requests
     */
    private int port;

    /**
     * The reference to the VVServer, which is the class that manage all the incoming requests and is part of the MVC
     */
    private final VVServer mainServerClass;

    private final Protocol usedProtocol = Protocol.TCP;







    //CONSTRUCTOR METHOD

    /**
     * Constructor for ServerNetworkTCPManager
     */
    public ServerNetworkTCPManager(VVServer server){
        this.mainServerClass = server;
    }



    //GETTER METHODS


    /**
     * Getter for hostName
     * @return the host name of the server
     */
    @Override
    public String getHostName() {
        return hostName;
    }

    @Override
    public Protocol getUsedProtocol() {
        return this.usedProtocol;
    }

    /**
     * Getter for server reference
     * @return the VVServer as reference
     */
    public VVServer getMainServerClass() {
        return mainServerClass;
    }


    @Override
    public int getPort() {
        return this.port;
    }


//SETTER METHODS
    @Override

    /**
     * Setter for host name
     * @param hostName the host name of the server
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }


//NETWORK MANAGER METHODS

    /**
     * Method to start communications with a specific protocol.
     * This method initializes the TCP modules to communicate with the TCP clients
     *
     * @return
     */
    @Override
    public void initCommunication() {
        //Calls runListeningLogic
        Thread listeningTCPThread = new Thread(() -> {
            this.runListeningLogic(port);
        });
        listeningTCPThread.start();
    }


    /**
     * Method to init the ping logic in order to know if clients are still connected
     */
    @Override
    public void initPing() {
        Thread pingerTCPThread = new Thread(() -> {
            while(true){
                List<NetworkClient> pongers = this.mainServerClass.getAllTCPClients();
                if(pongers != null && !pongers.isEmpty()){
                    for(NetworkClient nc : pongers){
                        if(nc.isOnline()){

                            PrintWriter out = nc.getStreams().getOut();
                            Scanner in = nc.getStreams().getIn();
                            //fixme A+S ping with data
                            System.out.println("ping sent");
                            if(in.nextLine() != "pong"){
                                System.out.println("Client doesn't answer to the ping");
                                try {
                                    this.disconnectedClientNotification(nc);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }
                    }
                }

                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pingerTCPThread.start();

    }




    //OTHER METHODS

   /**
    * Method to parse the strings serialized with JSON file from the client.
    * It calls the methods on the Server class in order to handle the network message
    *
    * @param message the message to pars as serialized string
    */
    public synchronized void handleJSONMessage(String message) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data myObject = objectMapper.readValue(message, Data.class);
            System.out.println("Deserialized object: " + myObject);

            //fixme A+S

            this.mainServerClass.notifyListeners(myObject.onServer(), this.mainServerClass.getListeners());
        } catch (Exception e) {
            e.printStackTrace();
        } //fixme azione
    }

    @Override
    public synchronized void sendSerializedMessage(Data message, NetworkClient client){

        ObjectMapper objectMapper = new ObjectMapper();

        String json = null; //fixme A+S
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        client.getStreams().getOut().println(json);
        //fixme A+S rimuovere message e end*/
    }


    /**
     * Private method to run the listening logic.
     * It uses a thread from an ExecutorService or creates a new Thread for each incoming
     * connection request
     * @param port the listening port of the server from prefs
     */
    private void runListeningLogic(int port) {


        ExecutorService executor = null;
        try {
            executor = Executors.newCachedThreadPool();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("TCP Server is running on port " + port);


            while (true) {

                try {
                    Socket clientSocket = serverSocket.accept();

                    System.out.println("Accepting...");

                    // Delegate client handling to ClientHandler

                    int clientID = this.connectedClientNotification(clientSocket);

                    ClientHandlerTCP clientHandler = new ClientHandlerTCP(this, clientID);

                    executor.submit(clientHandler);



                } catch (IOException e) {
                    System.out.println("Something went wrong with the server socket acceptation");
                    e.printStackTrace();
                    break;
                    //throw new RuntimeException(e);
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong with the server socket initialization on the port " + port);
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }


    @Override
    public synchronized int connectedClientNotification(Socket clientSocket){



        int clientID = this.mainServerClass.createNewOrphanClient(Protocol.TCP, clientSocket);
        try {
            this.mainServerClass.setClientOnline(this.mainServerClass.getClientByID(clientID));
            this.mainServerClass.getClientByID(clientID).setStreams(new Streams(new Scanner(clientSocket.getInputStream()), new PrintWriter(clientSocket.getOutputStream())));
        } catch (NonExistentClientException e) {
            System.out.println("Client doesn't exist");
        } catch (IOException e) {
            System.out.println("Problem with the creation of the buffer reader and/or print writer");
        }
        VVServer.activeConnections++;
        System.out.println("Accepted");
        System.out.println(VVServer.activeConnections + " clients are logged with TCP now");
        //fixme bind socket e gestione riconnessione client con nickname

        //fixme per fine partita devo fare in modo che si sciolga il party
        return clientID;
    }


    @Override
    public synchronized void disconnectedClientNotification(NetworkClient client) throws IOException {

        if(client != null && client.isOnline()){
            try {
                this.mainServerClass.getOrphanClientByID(client.getClientID());
                VVServer.activeConnections--;
                System.out.println("1 client has definitely disconnected from TCP");

               client.setOnline(false);
                System.out.println(VVServer.activeConnections + " clients are logged with TCP now");
                 client.getSocket().close();
            } catch (NonExistentClientException e) {
                try {
                    NetworkClient cInParty = this.mainServerClass.getClientInAPartyByID(client.getClientID());
                    if(cInParty != null){
                        System.out.println("1 client has temporarily disconnected from TCP");
                        //fixme action per propagazione a model
                        client.setOnline(false);
                        client.getSocket().close();
                    }
                } catch (NonExistentClientException ex) {
                    System.out.println("Trying to disconnect a non-existent client!");
                }
            }

        }
        //fixme chiusura clienthandler
    }
}


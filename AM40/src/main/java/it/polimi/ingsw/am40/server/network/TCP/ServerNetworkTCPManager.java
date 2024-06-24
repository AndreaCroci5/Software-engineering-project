package it.polimi.ingsw.am40.server.network.TCP;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.am40.client.network.RMI.RemoteInterfaceClient;
import it.polimi.ingsw.am40.data.Data;
import it.polimi.ingsw.am40.data.PingData;
import it.polimi.ingsw.am40.data.active.flow.CreateRequestData;
import it.polimi.ingsw.am40.data.active.flow.JoinRequestData;
import it.polimi.ingsw.am40.data.passive.flow.CreateResultData;
import it.polimi.ingsw.am40.server.actions.active.setup.GameIDChoiceAction;
import it.polimi.ingsw.am40.server.network.NetworkManagerServer;
import it.polimi.ingsw.am40.server.network.virtual_view.*;

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
     * Port on which server accept incoming connection requests for TCP
     */
    private int port;

    /**
     * The reference to the VVServer, which is the class that manage all the incoming network data and is part of the MVC
     */
    private final VVServer mainServerClass;

    /**
     * Enum used to recognize the used network protocol for polymorphism
     */
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
     * Getter for hostName.
     * @return the host name of the server
     */
    @Override
    public String getHostName() {
        return hostName;
    }

    /**
     * Getter for usedProtocl
     * @return the used network protocol (in this case TCP)
     */
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

    /**
     * Getter for the port number for TCP
     * @return the port number
     */
    @Override
    public int getPort() {
        return this.port;
    }



//SETTER METHODS

    /**
     * Setter for host name
     * @param hostName the host name of the server
     */
    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Setter for the port
     * @param port the port number
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }



//NETWORK MANAGER METHODS

    /**
     * Method to start communications with a specific protocol.
     * This method initializes the TCP modules to communicate with the TCP clients
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

                            Data pingPacket = new PingData();
                            this.sendSerializedMessage(pingPacket, nc);
                            //fixme * problema che potrei ricevere altro
                            //fixme A+S ping with data + set the streams at reconnect
                            System.out.println("ping sent");
                            /*if(in.nextLine() != "pong"){
                                System.out.println("Client doesn't answer to the ping");
                                try {
                                    this.disconnectedClientNotification(nc);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }*/

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
    public  void handleJSONMessage(String message) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data myObject = objectMapper.readValue(message,Data.class);
            System.out.println("SIAMO QUI: " + myObject.getDescription());

            if (myObject.getDescription().equalsIgnoreCase("JOIN_GAME")) {
                JoinRequestData data = (JoinRequestData) myObject;
                System.out.println("Ip address : " + data.getIpAddress());
                System.out.println("Port : " + data.getPort());
                for (NetworkClient c : this.mainServerClass.getOrphanClients()) {
                    System.out.println("C ipAddress : " + c.getSocket().getInetAddress().getHostAddress());
                    System.out.println("C port : " + c.getSocket().getPort());
                    if (c.getSocket().getInetAddress().getHostAddress().equals(data.getIpAddress()) && c.getSocket().getPort() == data.getPort()) {
                        c.setUsername(data.getNickname());
                    }
                }
            }

            if (myObject.getDescription().equalsIgnoreCase("CREATE_GAME")) {
                CreateRequestData data = (CreateRequestData) myObject;
                System.out.println("Ip address : " + data.getIpAddress());
                System.out.println("Port : " + data.getPort());
                for (NetworkClient c : this.mainServerClass.getOrphanClients()) {
                    System.out.println("C ipAddress : " + c.getSocket().getInetAddress().getHostAddress());
                    System.out.println("C port : " + c.getSocket().getPort());
                    if (c.getSocket().getInetAddress().getHostAddress().equals(data.getIpAddress()) && c.getSocket().getPort() == data.getPort()) {
                        c.setUsername(data.getNickname());
                    }
                }
            }


            // Fetching client network information based on client's username
            for (NetworkClient c : this.mainServerClass.getOrphanClients()) {
                if (c.getUsername().equals(myObject.getNickname())) {
                    myObject.setPlayerID(c.getClientID());
                }
            }
            // Server filter
            if (myObject.getDescription().equalsIgnoreCase("CREATE_GAME")) {
                this.mainServerClass.onEvent(myObject.onServer());
            } else if (myObject.getDescription().equalsIgnoreCase("GAME_ID_CHOICE")) {
                this.mainServerClass.onEvent(myObject.onServer());
            } else if (myObject.getDescription().equalsIgnoreCase("JOIN_GAME")) {
                this.mainServerClass.onEvent(myObject.onServer());
            }else if (myObject.getDescription().equalsIgnoreCase("READY_TO_PLAY")){
                this.mainServerClass.onEvent(myObject.onServer());
            }else {
                // once the game is initialised filter
                for (NetworkParty p : this.mainServerClass.getActiveParties()) {
                    for (NetworkClient c : p.getClients()) {
                        if (c.getClientID() == myObject.getPlayerID()) {
                            // GameID/PartyID fetch
                            myObject.setGameID(p.getPartyID());
                        }
                    }
                }
                this.mainServerClass.notifyListeners(myObject.onServer(), this.mainServerClass.getListeners());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void sendSerializedMessage(Data message, NetworkClient client){

        ObjectMapper objectMapper = new ObjectMapper();

        String json = ""; //fixme A+S
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        client.getStreams().getOut().println(json);
        client.getStreams().getOut().flush();
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

                    //fixme * meccanismo di sessione con hostname

                    int clientID = this.newConnectedClientNotification(clientSocket, null);

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
    public  int newConnectedClientNotification(Socket clientSocket, RemoteInterfaceClient remoteInterface){


        int clientID = this.mainServerClass.createNewOrphanClient(Protocol.TCP, clientSocket, null);
        try {
            this.mainServerClass.setClientOnline(this.mainServerClass.getClientByID(clientID));
            this.mainServerClass.getClientByID(clientID).setStreams(new Streams(new Scanner(clientSocket.getInputStream()), new PrintWriter(clientSocket.getOutputStream())));
        } catch (NonExistentClientException e) {
            System.out.println("Client doesn't exist");
        } catch (IOException e) {
            System.out.println("Problem with the creation of the buffer reader and/or print writer");
        }
        VVServer.activeConnections++;
        System.out.println("Accepted TCP client");
        System.out.println(VVServer.activeConnections + " clients are logged now");
        //fixme bind socket e gestione riconnessione client con nickname

        //fixme per fine partita devo fare in modo che si sciolga il party
        return clientID;
    }

    /**
     * Method to notify the VVServer that a client has been reconnected
     *
     * @param clientName the host name of the client
     */
    @Override
    public void reconnectionNotification(String clientName) {
        //todo
    }


    @Override
    public  void disconnectedClientNotification(NetworkClient client) throws IOException {

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

    @Override
    public void removeClientNotification() {
//todo
    }
}


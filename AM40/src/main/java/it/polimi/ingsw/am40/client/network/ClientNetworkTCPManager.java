package it.polimi.ingsw.am40.client.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.am40.client.ClientMessages.Message;
import it.polimi.ingsw.am40.data.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static it.polimi.ingsw.am40.client.network.Protocol.TCP;

/**
 * Concrete network protocol implementation for TCP
 */
public class ClientNetworkTCPManager implements NetworkManagerClient{

    //ATTRIBUTES

    /**
     * Reference to the main client class
     */
    private final Client client;

    /**
     * Host name for the client
     */
    private String hostName;

    /**
     * Identifier for the used protocol in this implementation
     */
    private final Protocol usedProtocol = TCP;

    /**
     * Port number of the TCP server
     */
    private Integer port;

    /**
     * Server IP address
     */
    private String serverAddress;

    /**
     * Input and output streams for TCP socket
     */
    private NetworkStreams streams;

    /**
     * Socket used by this client to communicate with an instance of client handler on the server
     */
    private Socket socket;

    /**
     * Thread used to listen to incoming network message from the server
     */
    private Thread listeningThread;





    //CONSTRUCTOR METHOD

    /**
     * Constructor for ClientNetworkTCPManager
     * @param client the reference to the Client class
     */
    public ClientNetworkTCPManager(Client client){
        this.client = client;
        this.streams = new NetworkStreams();
        this.socket  = null;
        this.listeningThread = null;
    }



    //GETTER METHODS

    /**
     * Getter for client
     * @return client as reference
     */
    @Override
    public Client getClient() {
        return this.client;
    }

    /**
     * Getter for the hostName
     * @return the hostName of this client
     */
    public String getHostName() {
        return this.hostName;
    }

    /**
     * Getter for port
     * @return the port number for TCP
     */
    @Override
    public int getPort() {
        return this.port;
    }

    /**
     * Getter for the server address
     * @return the server IP address
     */
    @Override
    public String getServerAddress() {
        return this.serverAddress;
    }

    /**
     * Getter for the input and output streams (packed in the same class)
     * @return the streams as reference
     */
    public NetworkStreams getStreams() {
        return streams;
    }

    /**
     * Getter for the client socket
     * @return the TCP socket as reference
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Getter for the listening thread on the client
     * @return the reference to the thread
     */
    public Thread getListeningThread() {
        return listeningThread;
    }

    /**
     * Getter for the used protocol
     * @return the used protocol as enum
     */
    @Override
    public Protocol getUsedProtocol() {
        return usedProtocol;
    }



    //SETTER METHODS

    /**
     * Setter for the hostName
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Setter for the TCP port
     * @param port the port number
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

    /**
     * Setter for the input and output streams
     * @param streams the streams to set
     */
    public void setStreams(NetworkStreams streams) {
        this.streams = streams;
    }

    /**
     * Setter for the TCP socket
     * @param socket the new socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Setter for the listening thread
     * @param listeningThread the new thread to set
     */
    public void setListeningThread(Thread listeningThread) {
        this.listeningThread = listeningThread;
    }



    //NETWORK MANAGER METHODS IMPLEMENTATION

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     * @throws NetworkProblemException if there is a problem with the creation of the socket, the creation of the
     *                                  input or output stream or the closing of the communications
     */
    @Override
    public void initCommunication() throws NetworkProblemException {

        //Trying to connect with the server by Socket
        try {
            this.socket = new Socket(this.serverAddress, this.port);
        } catch (IOException e) {
            System.out.println("Something went wrong with the socket server connection! The server may be offline, you could be offline or maybe your network config are wrong.");
            throw new NetworkProblemException("Socket problem!");
        }

        //Creation of the output and input stream
        try {
            this.streams.setOut(new PrintWriter(socket.getOutputStream(), true));
        } catch (IOException e) {
            System.out.println("There is a problem with the creation of the creation of the socket printer (out) stream!");
            throw new NetworkProblemException("Output stream problem!");
        }

        try{
            this.streams.setIn(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (IOException e) {
            System.out.println("There is a problem with the creation of the socket reader (in) stream!");
            throw new NetworkProblemException("Input stream problem!");
        }

        //Initialize the thread which listens to the server
        //Massive exceptions handling for debug and network diagnostic
        this.listeningThread = new Thread(() -> {
            try {
                this.runListeningLogic();
            } catch (NetworkServerCutOffException e) {
                try {
                    System.out.println("Trying to close the connection (Server cut off)...");
                    this.closeCommunication();
                } catch (NetworkProblemException ex) {
                    System.out.println("Interruption of the connection due to problem with the connection closing");
                    throw new RuntimeException(ex);
                }
                System.out.println("Closed");
            } catch (NetworkProblemException e) {
                try {
                    System.out.println("Trying to close the connection (Network Problems)...");
                    this.closeCommunication();
                } catch (NetworkProblemException ex) {
                    System.out.println("Interruption of the connection due to problem with the connection closing");
                    throw new RuntimeException(ex);
                }
                System.out.println("Closed");
            }
        });

        //Starting the thread
        listeningThread.start();
    }

    /**
     * Method to close the connection with the server
     * @throws NetworkProblemException if there are problems with the closing of the streams, of the socket or of the thread
     */
    @Override
    public void closeCommunication() throws NetworkProblemException {

        //Closing the socket
        if(this.socket != null){
            try {
                this.socket.close();
                this.socket = null;
            } catch (IOException e) {
                System.out.println("Problem occurred trying to close the socket!");
                throw new NetworkProblemException();
            }
        }

        //Closing the streams
        if(this.streams.getIn() != null) {
            try {
                this.streams.getIn().close();
                this.streams.setIn(null);
            } catch (IOException e) {
                throw new NetworkProblemException("Problem occurred trying to close the input stream!");
            }
        }

        if(this.streams.getOut() != null) {
            this.streams.getOut().close();
            this.streams.setOut(null);
        }

        //Closing the thread
        if(this.listeningThread != null) {
            this.listeningThread.interrupt();
            this.listeningThread = null;
        }

    }


    /**
     * Send implementation for TCP
     * Transform message to the data that is going on the network
     * Call send serialised message to send the data on the network
     * @param message is the internal message of the client
     */
    @Override
    public void send(Message message) {
        Data data = message.messageToData();
        sendSerializedMessage(data);
    }


    //AUXILIARY METHODS

    /**
     * Method which handles the incoming messages from the server by giving an immediate response or by calling
     * other method on Client class
     * @throws NetworkServerCutOffException if the server closes the connection (response == null) or when
     *                                      there is an internal IOException with the streams
     */
    private void runListeningLogic() throws NetworkServerCutOffException, NetworkProblemException {

        //Loop to listen to the incoming messages from the server
        while(true){

            //Read the received line
            String response = null;
            try {
                response = this.streams.getIn().readLine();
            } catch (IOException e) {
                System.out.println("Input line is null");
                throw new NetworkServerCutOffException();
            }

            //If the response is null it means that the server has interrupted the connection
            if (response == null) {
                System.out.println("Server closed the connection");
                throw new NetworkServerCutOffException();
            }

            this.handleJSONMessage(response);
        }
    }

    /**
     * Method which sends network messages to the server using JSON serialization
     * @param message the network message (as object), which will be converted in a JSON (jackson) string
     */
    public synchronized void sendSerializedMessage(Data message){
        ObjectMapper objectMapper = new ObjectMapper();

        String json = null;
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong with the json mapping!");
            throw new RuntimeException(e);
        }
        this.streams.getOut().println(json);
    }

    /**
     * Method to handle the json message incoming from the network
     * @param json is the json string
     */
    private void handleJSONMessage(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data myObject = objectMapper.readValue(json, Data.class);
            Message message = myObject.onClient();
            client.handleMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


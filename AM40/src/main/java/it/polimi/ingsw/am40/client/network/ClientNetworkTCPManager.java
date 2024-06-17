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

//fixme hostname on client (rmi too)
    /**
     * Host name for the client
     */
    private String hostName;

    private final Protocol usedProtocol = TCP;

    private Integer port;
    private String serverAddress;

    private NetworkStreams streams;

    private Socket socket;

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





    //SETTER METHODS




    //NETWORK MANAGER METHODS IMPLEMENTATION

    /**
     * Method to initialize the entire communication protocol and to set initial parameters
     */
    @Override
    public void initCommunication() throws NetworkProblemException {

        //Trying to connect with the server by Socket
        try {
            this.socket = new Socket(serverAddress, port);
        } catch (IOException e) {
            System.out.println("Something went wrong with the socket server connection! The server may be offline, you could be offline or maybe your network config are wrong.");
            throw new NetworkProblemException("Socket problem!");
        }

        //Creation of the output and input stream

        try {
            streams.setOut(new PrintWriter(socket.getOutputStream(), true));
        } catch (IOException e) {
            System.out.println("There is a problem with the creation of the creation of the socket printer (out) stream!");
            throw new NetworkProblemException("Output stream problem!");
        }

        try{
            streams.setIn(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (IOException e) {
            System.out.println("There is a problem with the creation of the socket reader (in) stream!");
            throw new NetworkProblemException("Input stream problem!");
        }

        //Initialize the thread which listens to the server
        listeningThread = new Thread(() -> {
            try {
                this.runListeningLogic();
            } catch (NetworkServerCutOffException e) {
                try {
                    this.closeCommunication();
                } catch (NetworkProblemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        listeningThread.start();
    }

    @Override
    public void closeCommunication() throws NetworkProblemException {
        this.socket = null;
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


        if(this.listeningThread != null) {
            this.listeningThread.interrupt();
            this.listeningThread = null;
        }

    }

    /**
     * Method which handles the incoming messages from the server by giving an immediate response or by calling
     * other method on Client class
     */
    private void runListeningLogic() throws NetworkServerCutOffException {

        while(true){
            try {
                String response = this.streams.getIn().readLine();

                if (response == null) {
                    System.out.println("Server closed the connection.");
                        throw new NetworkServerCutOffException();//fixme il client entra in mod offline
                }

                switch(response.toLowerCase()){
                    case "ping":
                        System.out.println("ping received");
                       this.streams.getOut().println("pong");
                        break;
                    case "message"://fixme A+S remove (all with data)
                        String subResponse;
                        StringBuilder jsonBuilder = new StringBuilder();
                        while ((subResponse = this.streams.getIn().readLine()) != null && !subResponse.isEmpty()) {
                            if (subResponse.equals("endmessage")){
                                String json = jsonBuilder.toString();
                                this.handleJSONMessage(json);
                                break;
                            }else{
                                jsonBuilder.append(subResponse);
                            }
                        }
                        break;
                    default:
                        System.out.println(response);
                        break;
                }
            } catch (IOException e) {
                System.out.println("Disconnected from the server");
                throw new NetworkServerCutOffException();
            }
        }
    }


    /**
     * Method which sends network messages to the server using JSON serialization
     * @param message the network message (as object), which will be converted in a JSON (jackson) string
     */
    private synchronized void sendSerializedMessage(Data message){
        ObjectMapper objectMapper = new ObjectMapper();

        String json = null;
        try {
            json = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.streams.getOut().println(json);
    }


    private void handleJSONMessage(String json){//fixme
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data myObject = objectMapper.readValue(json, Data.class);
            //System.out.println("Deserialized object: " + myObject);
            Message message = myObject.onClient();
            client.handleMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        } //fixme azione
    }



    public String getHostName() {
        return hostName;
    }

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

    public NetworkStreams getStreams() {
        return streams;
    }

    public void setStreams(NetworkStreams streams) {
        this.streams = streams;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Thread getListeningThread() {
        return listeningThread;
    }

    public void setListeningThread(Thread listeningThread) {
        this.listeningThread = listeningThread;
    }

    @Override
    public Protocol getUsedProtocol() {
        return usedProtocol;
    }


    // **TO FIX**
    @Override
    public void send(Message message) {
        Data data = message.messageToData();
        sendSerializedMessage(data);
    }

    // **TO FIX**
    @Override
    public void receive(Data data) {

    }
}


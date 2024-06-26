package it.polimi.ingsw.am40.client.application;

import it.polimi.ingsw.am40.client.UserInputReader;
import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.network.States.activeStates.SetUpState;
import it.polimi.ingsw.am40.client.network.States.activeStates.UsernameChoiceState;
import it.polimi.ingsw.am40.server.application.InvalidArgsPrefsException;
import it.polimi.ingsw.am40.server.application.InvalidJSONPrefsException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The class which contains the main method for our application on the client
 */
public class CodexNaturalisClient {

    //ATTRIBUTES

    /**
     * The path of the file JSON with the preferences
     */
    private static String sourcePrefsFile="ClientPrefs.json";





    //MAIN CLIENT

    /**
     * Main method of the application.
     * It creates the Client class and initializes the application taking in input from the user the initial parameters
     * @param args The argument of the application (from boot config)
     */
    public static void main(String[] args) {

        //Creation of the application and client class
        CodexNaturalisClient application = new CodexNaturalisClient();
        Client client = new Client();

        //Initialization of the preferences variables that will be filled up with the values read from JSON and/or from Args
        Integer portPrefTCP = null;
        Integer portPrefRMI = null;
        String hostPref = null;
        String serverPref = null;

        boolean JSONCheck = true;


        /* System for parameters parsing.
           If JSON or args fails, the other is taken as prefs source.
           If both fail, a runtime exception occurs.
           If both succeed, args prefs overwrite JSON prefs
        */
        try {
            //trying to parse from JSON
            List<Object> params = application.parsingJSONPrefs();
            portPrefTCP = (Integer) params.get(0);
            portPrefRMI = (Integer) params.get(1);
            hostPref = (String) params.get(2);
            serverPref = (String) params.get(3);

        } catch (InvalidJSONPrefsException e) {

            //if parsing fails, the flag is set as false
            JSONCheck = false;
        }


        try {
            //trying to parse from args
            List<Object> params = application.parsingArgsPrefs(args);
            portPrefTCP = (Integer) params.get(0);
            portPrefRMI = (Integer) params.get(1);
            hostPref = (String) params.get(2);
            serverPref = (String) params.get(3);

            System.out.println("Args prefs are set as parameters");

        } catch (InvalidArgsPrefsException e) {

            //if something goes wrong, error handling
            if(!JSONCheck) {
                System.out.println("ERROR! All given prefs (from both JSON and args) are invalid!");
                throw new RuntimeException(e);
            }else{
                System.out.println("JSON prefs are set as parameters");
            }
        }

        //Getting the network and view initial configuration from command line
        Scanner scanner = new Scanner(System.in);
        boolean retry1 = true;
        String network = null;

        //Retry until the parameters aren't correct
        do{
            try {
                network = application.askParamNetwork(scanner);
                retry1 = false;
            } catch (IllegalValueException e) {
                System.out.println("Please retry...");
            }
        }while (retry1);


        boolean retry2 = true;
        String view = null;
        do{
            try {
                view = application.askParamView(scanner);
                retry2 = false;
            } catch (IllegalValueException e) {
                System.out.println("Please retry...");
            }
        }while (retry2);


        //launching the application
        client.initApplication(portPrefTCP, portPrefRMI, hostPref, serverPref, network, view);

        // set the first state of the client FSM
        client.setInputReader(new UserInputReader(client));
        // Creating a new thread to constantly listen for input
        Thread reallyImportantThread = new Thread(client.getInputReader());
        reallyImportantThread.start();
        client.setState(new UsernameChoiceState());
        client.getCurrentState().execute(client);

    }


    //AUXILIARY CHECK METHODS

    /**
     * Method to check if the port number is valid
     * @param port the port of the server
     * @return true if valid, false otherwise
     */
    private boolean checkPort(int port){
        if( port > 1024 && port < 49151){
            return true;
        }
        return false;
    }

    /**
     * Method to check if the host name is valid
     * @param hostName the host name of the server
     * @return true if valid, false otherwise
     */
    private boolean checkHostName(String hostName){
        return true;
    }

    /**
     * Method to check if the server address is valid
     * @param serverAddress the host name of the server
     * @return true if valid, false otherwise
     */
    private boolean checkServerAddress(String serverAddress){
        String ipRegex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return Pattern.compile(ipRegex).matcher(serverAddress).matches();
    }



    //AUXILIARY PARSING METHODS

    /**
     * Method to parse prefs from JSON
     * @return a list of obj (0 - TCP port as Integer, 1 - RMI port as Integer, 2 - hostName as String, 3 - server IP address as String)
     * @throws InvalidJSONPrefsException if the key values doesn't exist,
     *                                   if it's impossible to parse the file or if prefs values aren't valid
     */
    private List<Object> parsingJSONPrefs() throws InvalidJSONPrefsException{

        InputStream is = null;

        try {
            is = getClass().getClassLoader().getResourceAsStream(sourcePrefsFile);
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + sourcePrefsFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
            JSONObject prefs = (JSONObject) obj;

            //Reading the prefs parameters from JSON

            String portTCP = (String) prefs.get("portNumberTCP");
            String portRMI= (String) prefs.get("portNumberRMI");
            String hostName = (String) prefs.get("hostnameClient");
            String serverAddress = (String) prefs.get("serverAddress");

            if(portTCP == null || portRMI == null || hostName == null || serverAddress == null){
                System.out.println("Loading from JSON file failed");
                throw new InvalidJSONPrefsException();
            }

            if(!checkPort(Integer.parseInt(portTCP.toString())) || !checkPort(Integer.parseInt(portRMI.toString())) || !checkHostName(hostName) || !checkServerAddress(serverAddress)){
                System.out.println("Loading from JSON file failed");
                throw new InvalidJSONPrefsException();
            }

            List<Object> result = new ArrayList<>();
            result.add(Integer.parseInt(portTCP));
            result.add(Integer.parseInt(portRMI));
            result.add(hostName);
            result.add(serverAddress);

            System.out.println("Loading from JSON succeed");

            return result;

        }catch(IOException | ParseException e) {
            System.out.println("Loading from JSON file failed");

            throw new InvalidJSONPrefsException();
        }
    }

    /**
     * Method to parse prefs from main args
     * @return a list of obj (0 - port as Integer, 1 - hostName as String, 2 - server IP address)
     * @throws InvalidArgsPrefsException if one or both the parameters are invalid, or if the number
     *                                     of args is != 3
     */
    private List<Object> parsingArgsPrefs(String[] args) throws InvalidArgsPrefsException{
        if(args.length == 4) {
            //Taking port
            int portPrefTCP = Integer.parseInt(args[0]);
            int portPrefRMI = Integer.parseInt(args[1]);

            //Taking host name
            String hostPref = args[2];
            String serverPref = args[3];

            if(hostPref == null || serverPref == null || !(checkPort(portPrefTCP) && checkPort(portPrefRMI) && checkHostName(hostPref) && checkServerAddress(serverPref))){
                System.out.println("Loading from args failed");
                throw new InvalidArgsPrefsException();
            }

            List<Object> result = new ArrayList<>();
            result.add(portPrefTCP);
            result.add(portPrefRMI);
            result.add(hostPref);
            result.add(serverPref);
            System.out.println("Loading from args succeed");
            return result;

        } else {
            System.out.println("Loading from args failed");
            throw new InvalidArgsPrefsException();
        }
    }


    //AUXILIARY ASKING-PARAMETERS METHODS

    /**
     * Method which asks the user the network protocol preference
     * @param in the scanner used to communicate with the user
     * @return the parameter from the user as string
     * @throws IllegalValueException if the input is wrong
     */
    private String askParamNetwork(Scanner in) throws IllegalValueException{
        // Asking the user which network protocol he wants

        System.out.print("Insert network protocol param (accepted: 'TCP' or 'RMI') : ");
        String networkPref = in.nextLine();

        //Basing on what the user has written, we check if the preference is valid
        switch (networkPref.toLowerCase()){
            case "tcp":
                break;
            case "rmi":
                break;
            default: throw new IllegalValueException();
        }

        return networkPref;
    }

    /**
     * Method which asks the user the view system preference
     * @param in the scanner used to communicate with the user
     * @return the parameter from the user as string
     * @throws IllegalValueException if the input is wrong
     */
    private String askParamView(Scanner in) throws IllegalValueException{
        // Asking the user which view system he wants

        System.out.print("Insert view system param (accepted: 'TUI' or 'GUI') : ");
        String networkPref = in.nextLine();

        //Basing on what the user has written, we check if the preference is valid
        switch (networkPref.toLowerCase()){
            case "tui":
                break;
            case "gui": //fixme link with the gui
            break;
            default: throw new IllegalValueException();
        }

        return networkPref;
    }


}



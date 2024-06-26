package it.polimi.ingsw.am40.server.application;


import it.polimi.ingsw.am40.server.controller.GameManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The class which contains the main method for our application on the server
 */
public class CodexNaturalisServer {

    /**
     * File path for json prefs file
     */
    private static final String sourcePrefsFile = "ServerPrefs.json";

    /**
     * Main application
     * @param args the arguments of the main application
     */
    public static void main(String[] args) {

        //Creating application obj
        CodexNaturalisServer application = new CodexNaturalisServer();

        //Creating the controller
        GameManager gameManager = new GameManager();

        Integer portPrefTCP = null;
        Integer portPrefRMI = null;
        String hostPref = null;
        boolean JSONCheck = true;


        /*   System for parameters parsing.
             If JSON or args fails, the other is taken as prefs source.
             If both fail, a runtime exception occurs.
             If both succeed, args prefs overwrite JSON prefs
        */
        try {
            List<Object> params = application.parsingJSONPrefs();
            portPrefTCP = (Integer) params.get(0);
            portPrefRMI = (Integer) params.get(1);
            hostPref = (String) params.get(2);
        } catch (InvalidJSONPrefsException e) {
            JSONCheck = false;
        }

        try {
            List<Object> params = application.parsingArgsPrefs(args);
            portPrefTCP = (Integer) params.get(0);
            portPrefRMI = (Integer) params.get(1);
            hostPref = (String) params.get(2);
            System.out.println("Args prefs are set as parameters");
        } catch (InvalidArgsPrefsException e) {
            if(JSONCheck == false) {
                System.out.println("ERROR! All given prefs (from both JSON and args) are invalid!");
                throw new RuntimeException(e);
            }else{
                System.out.println("JSON prefs are set as parameters");
            }
        }

        System.out.println("Server initialization on port: " + portPrefTCP + " for TCP, " + portPrefRMI + " for RMI, " + "host name: " + hostPref);


        gameManager.initApplication(portPrefTCP, portPrefRMI, hostPref);

    }

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
     * Method to parse prefs from JSON
     * @return a list of obj (0 - port as Integer, 1 - hostName as String)
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
            String portRMI = (String) prefs.get("portNumberRMI");
            String hostName = (String) prefs.get("hostnameServer");

            if(portTCP == null || portRMI == null || hostName == null){
                System.out.println("Loading from JSON file failed");
                throw new InvalidJSONPrefsException();
            }

            if(!checkPort(Integer.parseInt(portTCP.toString())) || !checkPort(Integer.parseInt(portRMI.toString())) || !checkHostName(hostName)){
                System.out.println("Loading from JSON file failed");
                throw new InvalidJSONPrefsException();
            }

            List<Object> result = new ArrayList<>();
            result.add(Integer.parseInt(portTCP));
            result.add(Integer.parseInt(portRMI));
            result.add(hostName);

            System.out.println("Loading from JSON succeed");

            return result;

        }catch(IOException | ParseException e) {
            System.out.println("Loading from JSON file failed");

            throw new InvalidJSONPrefsException();
        }
    }

    /**
     * Method to parse prefs from main args
     * @return a list of obj (0 - port as Integer, 1 - hostName as String)
     * @throws InvalidArgsPrefsException if one or both the parameters are invalid, or if the number
     *                                     of args is != 2
     */
    private List<Object> parsingArgsPrefs(String[] args) throws InvalidArgsPrefsException{
        if(args.length == 3) {
            //Taking port
            Integer portPrefTCP = Integer.parseInt(args[0]);
            Integer portPrefRMI = Integer.parseInt(args[1]);

            //Taking host name
            String hostPref = args[2];

            if(portPrefTCP == null || portPrefRMI == null || hostPref == null || !(checkPort(portPrefTCP) && checkPort(portPrefRMI) && checkHostName(hostPref))){
                System.out.println("Loading from args failed");
                throw new InvalidArgsPrefsException();
            }

            List<Object> result = new ArrayList<>();
            result.add(portPrefTCP);
            result.add(portPrefRMI);
            result.add(hostPref);
            System.out.println("Loading from args succeed");
            return result;

        } else {
            System.out.println("Loading from args failed");
            throw new InvalidArgsPrefsException();
        }
    }
}

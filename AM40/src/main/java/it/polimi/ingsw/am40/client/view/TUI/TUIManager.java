package it.polimi.ingsw.am40.client.view.TUI;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.view.ViewManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The TUIManager is the class which make possible to display the information
 * and to manage the inputs with a command line interface on the console
 */
public class TUIManager implements ViewManager {

    //ATTRIBUTES

    private Client client;

    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    @Override
    public void initView() {
        this.printPixelArt("AM40/src/main/resources/it/polimi/ingsw/am40/logo.txt");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public Client getClient() {
        return this.client;
    }

    private void printPixelArt(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                    System.out.println(line);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package it.polimi.ingsw.am40.client.view.GUI;

import it.polimi.ingsw.am40.client.network.Client;
import it.polimi.ingsw.am40.client.view.ViewManager;


/**
 * The GUIManager is the class which make possible to display the information
 * and to manage the inputs with JavaFX
 */
public class GUIManager implements ViewManager {

    ///ATTRIBUTES

    private Client client;


    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    @Override
    public void initView() {

    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public Client getClient() {
        return this.client;
    }
}

package it.polimi.ingsw.am40.client.view;

import it.polimi.ingsw.am40.client.ConcreteContext;
import it.polimi.ingsw.am40.client.network.Client;

/**
 * The ViewManager interface forces the implementers to have methods to show something in the
 * user interface and to be able to manage incoming user inputs
 */
public interface ViewManager extends ConcreteContext {

    /**
     * Method to initialize the entire display protocol and to set initial parameters
     */
    public void initView();

    public void setClient(Client client);

    public Client getClient();
}

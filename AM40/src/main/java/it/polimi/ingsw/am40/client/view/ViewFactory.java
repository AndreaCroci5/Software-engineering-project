package it.polimi.ingsw.am40.client.view;

import it.polimi.ingsw.am40.client.view.GUI.GUIManager;
import it.polimi.ingsw.am40.client.view.TUI.TUIManager;

/**
 * The ViewFactory class is used to actuate the factory pattern for the view graphic interface.
 * The user can decide how to show the information on the display
 */
public class ViewFactory {

    /**
     * Method to create the concrete network manager class according to user preferences
     * @param graphicInterface A string which define which protocol is used
     * @return the reference to the new concrete network manager class (null if something
     */
    public ViewManager createConcreteView(String graphicInterface) throws ViewFactoryException {
            if(graphicInterface != null){
                switch(graphicInterface){
                    case "TUI":
                        return new TUIManager();
                    case "GUI":
                        return new GUIManager();
                    default:
                        throw new ViewFactoryException();
                }
            }
            throw new ViewFactoryException();

    }
}

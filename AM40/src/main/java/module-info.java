module it.polimi.ingsw.am40 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires json.simple;
    requires java.rmi;
    requires com.fasterxml.jackson.annotation;

    opens it.polimi.ingsw.am40 to javafx.fxml;
    exports it.polimi.ingsw.am40.server.model;
    exports it.polimi.ingsw.am40.server.controller;
    exports it.polimi.ingsw.am40.client.view.GUI.FXexamples;
    exports it.polimi.ingsw.am40.exceptions.server.model;
    opens it.polimi.ingsw.am40.client.view.GUI.FXexamples to javafx.fxml;
    opens it.polimi.ingsw.am40.server to javafx.fxml;
    exports it.polimi.ingsw.am40.client.view.GUI;
    opens it.polimi.ingsw.am40.client.view.GUI to javafx.fxml;

    exports it.polimi.ingsw.am40.server.network.RMI to java.rmi;
}
module it.polimi.ingsw.am40 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens it.polimi.ingsw.am40 to javafx.fxml;
    exports it.polimi.ingsw.am40;
    exports it.polimi.ingsw.am40.FXexamples;
    opens it.polimi.ingsw.am40.FXexamples to javafx.fxml;
}
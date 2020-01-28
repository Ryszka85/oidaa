module testDbFx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.swt;
    requires mysql.connector.java;
    requires java.sql;
    opens sample;
    opens sample.FxGUI.fxController;
    opens sample.Datamodel;
    opens sample.FxGUI.tableView;
}
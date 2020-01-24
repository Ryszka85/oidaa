package sample.fxController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Datamodel.Person;
import sample.dbUtil.CustomerDbService;

import java.util.List;
import java.util.Objects;

public class Controller {
    @FXML
    private ListView<String> customers;
    private ObservableList<String> customerNames;

    public void initialize() {
        customerNames = FXCollections.observableArrayList();
        customers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Objects.requireNonNull(CustomerDbService.getCustomers())
                .forEach(x -> customerNames.add(x.getFirstName() + " " + x.getLastName()));
        customers.setItems(customerNames);
    }
}

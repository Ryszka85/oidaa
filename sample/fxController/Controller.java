package sample.fxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Datamodel.Address;
import sample.Datamodel.Person;
import sample.dbUtil.AddressDbService;
import sample.dbUtil.CustomerDbService;

import java.util.Objects;

public class Controller {
    @FXML
    private TableView<Address> addressTable;
    @FXML
    private TableColumn<Address, String> postal;
    @FXML
    private TableColumn<Address, String> city;
    @FXML
    private TableColumn<Address, String> streetAddress;
    @FXML
    private TableColumn<Address, String> countryName;
    @FXML
    private ListView<Person> customerTable;

    private ObservableList<Person> customerList;
    private ObservableList<Address> addressList;

    public void initialize() {
        customerList = FXCollections.observableArrayList();
        addressList = FXCollections.observableArrayList();
        initCustomerNames();
        initAddressColumns();
    }

    private void initCustomerNames() {
        customerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        customerList.addAll(Objects.requireNonNull(CustomerDbService.getCustomers()));
        setCustomerCellName();
        setSelectedItemEvent();
    }

    private void setSelectedItemEvent() {
        customerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, person, selectedPerson) -> {
                    addressList.setAll(AddressDbService.getUserAddress(selectedPerson.getId()));
                    addressTable.setItems(addressList);
                });
        customerTable.setItems(customerList);
    }

    private static ListCell<Person> setCellName(ListView<Person> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Person person, boolean b) {
                super.updateItem(person, b);
                if (b || person == null || person.getFirstName() == null || person.getLastName() == null) {
                    setText(null);
                } else {
                    final String name = person.getFirstName() + " " + person.getLastName();
                    setText(name);
                }
            }
        };
    }

    private void setCustomerCellName() { customerTable.setCellFactory(Controller::setCellName); }

    private void initAddressColumns() {
        postal = new TableColumn<>("Postal");
        postal.setCellValueFactory(new PropertyValueFactory<Address, String>("postal"));
        city = new TableColumn<>("City");
        city.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
        streetAddress = new TableColumn<>("StreetAddress");
        streetAddress.setCellValueFactory(new PropertyValueFactory<Address, String>("streetAddress"));
        countryName = new TableColumn<>("CountryName");
        countryName.setCellValueFactory(new PropertyValueFactory<Address, String>("countryName"));
        addressTable.getColumns().setAll(postal, city, streetAddress, countryName);
    }
}

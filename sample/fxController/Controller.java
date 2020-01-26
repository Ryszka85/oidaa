package sample.fxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Datamodel.Address;
import sample.Datamodel.Person;
import sample.dbUtil.CustomerDbService;
import sample.userDetails.AddressDetailsFx;
import sample.userDetails.ShoppingCartDetailsFx;

import java.util.Objects;

public class Controller {
    @FXML
    private CheckBox show_shopping_cart;
    @FXML
    private CheckBox show_address;
    @FXML
    private TableView userDetailTable;
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
    private ObservableList<ShoppingCartDetailsFx> shoppingCartDetailsFx;

    public void initialize() {
        customerList = FXCollections.observableArrayList();
        addressList = FXCollections.observableArrayList();
        shoppingCartDetailsFx = FXCollections.observableArrayList();
        userDetailTable.getColumns().clear();
        initCustomerNames();
    }

    public void showAddressTable() {
        if (!show_address.isSelected() || show_shopping_cart.isSelected()) clearUserDetailTable();
        else if (show_address.isSelected() && !show_shopping_cart.isSelected()) {
            setAddressDetailTable(customerTable.getSelectionModel().getSelectedItem().getId());
        }
    }

    public void clearUserDetailTable() {
        userDetailTable.getColumns().clear();
        userDetailTable.setItems(FXCollections.observableArrayList());
    }

    public void setAddressDetailTable(int id) {
        AddressDetailsFx.setAddressColumnNames(userDetailTable);
        addressList.setAll(AddressDetailsFx.setAddressTableData(id));
        userDetailTable.setItems(addressList);
    }

    public void setShoppingCartDetailTable(int id) {
        ShoppingCartDetailsFx.setAddressColumnNames(userDetailTable);
        shoppingCartDetailsFx.setAll(ShoppingCartDetailsFx.setShoppingCartTableData(id));
        userDetailTable.setItems(shoppingCartDetailsFx);
    }

    public void showShoppingCartTable() {
        if (!show_shopping_cart.isSelected() || show_address.isSelected()) clearUserDetailTable();
        else if (show_shopping_cart.isSelected() && !show_address.isSelected()) {
            setShoppingCartDetailTable(customerTable.getSelectionModel().getSelectedItem().getId());
        }
    }


    public void setShoppingCartTable() {

    }

    private void initCustomerNames() {
        customerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        customerList.addAll(Objects.requireNonNull(CustomerDbService.getCustomers()));
        setCustomerCellName();
        setSelectedItemEvent();
        customerTable.setItems(customerList);
    }

    private void setSelectedItemEvent() {
        customerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, person, selectedPerson) -> {
                    if (show_address.isSelected() && !show_shopping_cart.isSelected()) {
                        setAddressDetailTable(selectedPerson.getId());
                    } else if (show_shopping_cart.isSelected() && !show_address.isSelected()){
                        setShoppingCartDetailTable(selectedPerson.getId());
                    }

                });
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

    private void initAddressColumns() { AddressDetailsFx.setAddressColumnNames(userDetailTable); }
}

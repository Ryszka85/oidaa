package sample.FxGUI.fxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Datamodel.Address;
import sample.Datamodel.Person;
import sample.FxGUI.listView.CustomerListView;
import sample.FxGUI.tableView.ShoppingCartDetailsTable;
import sample.FxGUI.tableView.ShowTableValidator;
import sample.FxGUI.tableView.UserDetailsTable;
import sample.database.dataBaseUtil.DbCustomerDAO;
import sample.database.dataBaseUtil.DbFactoryDAO;
import sample.FxGUI.tableView.AddressDetailsTable;

import java.util.Objects;

public class Controller {
    @FXML
    private CheckBox show_shopping_cart;
    @FXML
    private CheckBox show_address;
    @FXML
    private TableView<UserDetailsTable> userDetailTable;
    @FXML
    private ListView<Person> customerTable;

    private ObservableList<Person> customerList;
    private UserDetailsTable shoppingCartDetails;
    private UserDetailsTable addressDetails;
    private CustomerListView customerListView;
    ShowTableValidator showShoppingCart;
    ShowTableValidator showAddress;

    public void initialize() {
        shoppingCartDetails = UserDetailsTable.tableFactory(UserDetailsTable.SHOPPING_CART_DETAILS, userDetailTable);
        addressDetails = UserDetailsTable.tableFactory(UserDetailsTable.ADDRESS_DETAILS, userDetailTable);
        customerList = FXCollections.observableArrayList();
        userDetailTable.getColumns().clear();
        customerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        customerListView = new CustomerListView(
                customerTable,
                addressDetails,
                shoppingCartDetails,
                show_shopping_cart,
                show_address
        );
        customerListView.setSelectedItemEvent();
        customerListView.initCustomerListTable();
        showShoppingCart = new ShowTableValidator(shoppingCartDetails, userDetailTable, customerTable);
        showAddress = new ShowTableValidator(addressDetails, userDetailTable, customerTable);
    }

    public void showAddressTable() {
        showAddress.showTable(show_address, show_shopping_cart);
    }

    public void clearUserDetailTable() {
        userDetailTable.getColumns().clear();
    }

    public void showShoppingCartTable() {
        showShoppingCart.showTable(show_shopping_cart, show_address);
    }

}

package sample.FxGUI.fxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.Datamodel.Person;
import sample.FxGUI.listView.CustomerListView;
import sample.FxGUI.tableView.ShowTableValidator;
import sample.FxGUI.tableView.UserDetailsTable;
import sample.database.dataBaseUtil.DbCustomerDAO;
import sample.validator.PersonValidator;
import sample.validator.Validator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

public class MainWindowController {
    @FXML
    private CheckBox show_shopping_cart;
    @FXML
    private CheckBox show_address;

    @FXML
    private TableView<UserDetailsTable> userDetailTable;
    @FXML
    private ListView<Person> customerTable;
    @FXML
    private BorderPane mainBorderPane;

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

    public void openNewCustomerDialog() {
        Dialog<ButtonType> newCustomerDialog = initDialogOwner();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogWindow.fxml"));
        setDialogContentWithFxml(newCustomerDialog,fxmlLoader);

        addConfirmButtonToDialog(newCustomerDialog, ButtonType.OK);
        addConfirmButtonToDialog(newCustomerDialog, ButtonType.CANCEL);

        Optional<ButtonType> confirmedResults = newCustomerDialog.showAndWait();

        if (confirmedResults.isPresent() && confirmedResults.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            initialize();
            try {
                PersonValidator.validatePerson(dialogController.getNewCustomer());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addConfirmButtonToDialog(Dialog<ButtonType> newCustomerDialog, ButtonType ok) {
        newCustomerDialog.getDialogPane()
                .getButtonTypes()
                .add(ok);
    }

    private void setDialogContentWithFxml(Dialog<ButtonType> newCustomerDialog, FXMLLoader fxmlLoader) {
        try {
            newCustomerDialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Dialog<ButtonType> initDialogOwner() {
        Dialog<ButtonType> newCustomerDialog = new Dialog<>();
        newCustomerDialog.initOwner(mainBorderPane.getScene().getWindow());
        return newCustomerDialog;
    }

    private FXMLLoader getDialogFxml() {
        FXMLLoader dialogFxml = new FXMLLoader();
        dialogFxml.setLocation(getClass().getResource("dialogWindow.fxml"));
        return dialogFxml;
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

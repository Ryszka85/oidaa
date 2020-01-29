package sample.FxGUI.listView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import sample.Datamodel.Person;
import sample.FxGUI.tableView.UserDetailsTable;
import sample.database.dataBaseUtil.DbCustomerDAO;
import sample.database.dataBaseUtil.DbFactoryDAO;

import java.util.Date;
import java.util.List;

public class CustomerListView {
    @FXML
    private ListView<Person> customerTable;
    public static ObservableList<Person> customerList;
    private UserDetailsTable shoppingCartDetails;
    private UserDetailsTable addressDetails;
    private CheckBox show_shopping_cart;

    private CheckBox show_address;

    public CustomerListView(ListView<Person> customerTable,
                            UserDetailsTable addressDetails,
                            UserDetailsTable shoppingCartDetails,
                            CheckBox show_shopping_cart,
                            CheckBox show_address) {
        this.customerTable = customerTable;
        customerList = FXCollections.observableArrayList();
        this.shoppingCartDetails = shoppingCartDetails;
        this.addressDetails = addressDetails;
        this.show_address = show_address;
        this.show_shopping_cart = show_shopping_cart;
    }

    public void initCustomerListTable() {
        DbCustomerDAO customerDAO = DbFactoryDAO.getCustomerDAO();
        customerList.setAll(customerDAO.selectAll());
        setCustomerCell();
        this.customerTable.setItems(customerList);
    }

    public void setSelectedItemEvent() {
        customerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, person, selectedPerson) -> {
                    if (show_address.isSelected() && !show_shopping_cart.isSelected()) {
                        addressDetails.setData(selectedPerson.getId());
                    } else if (show_shopping_cart.isSelected() && !show_address.isSelected()){
                        shoppingCartDetails.setData(selectedPerson.getId());
                    }
                });
    }

    public void setCustomerCell() {
        this.customerTable.setCellFactory((ListView<Person> p)  -> new ListCell<Person>() {
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
        });
    }
}

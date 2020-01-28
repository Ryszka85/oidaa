package sample.FxGUI.tableView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import sample.Datamodel.Person;

import java.util.EventListener;

public class ShowTableValidator<T extends UserDetailsTable> {
    private TableView<UserDetailsTable> userDetailTableView;
    private ListView<Person> customerTable;
    private UserDetailsTable userDetailsTable;

    public ShowTableValidator(UserDetailsTable userDetailsTable,
                              TableView<UserDetailsTable> userDetailTable,
                              ListView<Person> customerTable) {
        this.userDetailTableView = userDetailTable;
        this.userDetailsTable = userDetailsTable;
        this.customerTable = customerTable;
    }

    public void clearUserDetailTable() {
        userDetailTableView.getColumns().clear();
    }

    public void showTable(CheckBox show_shopping_cart, CheckBox show_address) {
        if (!show_shopping_cart.isSelected() || show_address.isSelected())
            clearUserDetailTable();
        else if (show_shopping_cart.isSelected() && !show_address.isSelected()) {
            final int id = customerTable.getSelectionModel()
                    .getSelectedItem()
                    .getId();
            userDetailsTable.setData(id);
        }
    }
}

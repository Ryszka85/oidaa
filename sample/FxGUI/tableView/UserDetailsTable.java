package sample.FxGUI.tableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public abstract class UserDetailsTable {
    public TableView<UserDetailsTable> userDetailsTable;

    public static final int SHOPPING_CART_DETAILS = 1;
    public static final int ADDRESS_DETAILS = 2;

    public UserDetailsTable(TableView<UserDetailsTable> userDetailsTable) {
        this.userDetailsTable = userDetailsTable;

    }

    public UserDetailsTable() {
    }

    public abstract void setTableViewDetails(int id);
    abstract void setColumnNames();

    public static UserDetailsTable tableFactory(final int choice, TableView tableView) {
        return choice == SHOPPING_CART_DETAILS ? new ShoppingCartDetailsTable(tableView) :
                choice == ADDRESS_DETAILS ? new AddressDetailsTable(tableView) : null;
    }

    public void setData(final int id) {
        setColumnNames();
        setTableViewDetails(id);
    }

}

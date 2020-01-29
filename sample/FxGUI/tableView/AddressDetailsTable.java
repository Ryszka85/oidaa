package sample.FxGUI.tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Datamodel.Address;
import sample.database.dataBaseUtil.DbAddressDAO;
import sample.database.dbUtil.AddressDbService;

public class AddressDetailsTable extends UserDetailsTable{
    private static final String COLUMN_STREET = "street";
    private static final String COLUMN_STREET_VALUE = "streetAddress";
    private static final String COLUMN_CITY = "City";
    private static final String COLUMN_CITY_VALUE = "city";
    private static final String COLUMN_POSTAL = "Postal";
    private static final String COLUMN_POSTAL_VALUE = "postal";
    private static final String COLUMN_COUNTRY = "CountryName";
    private static final String COLUMN_COUNTRY_VALUE = "countryName";

    @FXML
    private TableColumn<Address, String> postal;
    @FXML
    private TableColumn<Address, String> city;
    @FXML
    private TableColumn<Address, String> streetAddress;
    @FXML
    private TableColumn<Address, String> countryName;

    private static ObservableList<Address> addressList;
    private TableView<Address> tableViewDetails;
    private DbAddressDAO addressDAO;

    public AddressDetailsTable(TableView<Address> tableViewDetails) {
        this.tableViewDetails = tableViewDetails;
        addressDAO = new DbAddressDAO();
    }

    static { addressList = FXCollections.observableArrayList(); }

    @Override
    public void setColumnNames() {
        initPostalColumn();
        initCityColumn();
        initStreetAddressColumn();
        initCountryNameColumn();
        tableViewDetails.getColumns()
                .setAll(postal, city, streetAddress, countryName);
    }

    @Override
    public void setTableViewDetails(int id) {
        setColumnNames();
        final Address address = addressDAO.selectSpecificByInt(id);
        addressList.setAll(address);
        tableViewDetails.setItems(addressList);
    }

    private void initCountryNameColumn() {
        this.countryName = new Column<Address, String>(COLUMN_COUNTRY, COLUMN_COUNTRY_VALUE).getColumn();
    }

    private void initStreetAddressColumn() {
        streetAddress = new Column<Address, String>(COLUMN_STREET, COLUMN_STREET_VALUE).getColumn();
    }

    private void initCityColumn() {
        city = new Column<Address, String>(COLUMN_CITY, COLUMN_CITY_VALUE).getColumn();
    }

    private void initPostalColumn() {
        postal = new Column<Address, String>(COLUMN_POSTAL, COLUMN_POSTAL_VALUE).getColumn();
    }


}

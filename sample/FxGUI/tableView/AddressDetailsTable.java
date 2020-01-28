package sample.FxGUI.tableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Datamodel.Address;
import sample.database.dbUtil.AddressDbService;

public class AddressDetailsTable extends UserDetailsTable{
    @FXML
    private static TableColumn<Address, String> postal;
    @FXML
    private static TableColumn<Address, String> city;
    @FXML
    private static TableColumn<Address, String> streetAddress;
    @FXML
    private static TableColumn<Address, String> countryName;

    private static ObservableList<Address> addressList;
    private TableView tableViewDetails;

    public AddressDetailsTable(TableView tableViewDetails) {
        this.tableViewDetails = tableViewDetails;
    }

    static { addressList = FXCollections.observableArrayList(); }
    @Override
    public void setColumnNames() {
        initPostalColumn();
        initCityColumn();
        initStreetAddressColumn();
        initCountryNameColumn();
        tableViewDetails.getColumns().setAll(postal, city, streetAddress, countryName);
    }

    public static ObservableList<Address> getAddressTableData(int id) {
        addressList.setAll(AddressDbService.getUserAddress(id));
        return addressList;
    }
    @Override
    public void setTableViewDetails(int id) {
        setColumnNames();
        tableViewDetails.setItems(FXCollections.observableArrayList(AddressDbService.getUserAddress(id)));
    }

    private static void initCountryNameColumn() {
        countryName = new TableColumn<>("CountryName");
        countryName.setCellValueFactory(new PropertyValueFactory<Address, String>("countryName"));
    }

    private static void initStreetAddressColumn() {
        streetAddress = new TableColumn<>("StreetAddress");
        streetAddress.setCellValueFactory(new PropertyValueFactory<Address, String>("streetAddress"));
    }

    private static void initCityColumn() {
        city = new TableColumn<>("City");
        city.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
    }

    private static void initPostalColumn() {
        postal = new TableColumn<>("Postal");
        postal.setCellValueFactory(new PropertyValueFactory<Address, String>("postal"));
    }


}

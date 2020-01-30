package sample.FxGUI.fxController;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Datamodel.Address;
import sample.Datamodel.Country;
import sample.Datamodel.Person;
import sample.FxGUI.listView.CustomerListView;
import sample.database.dataBaseUtil.DbAddressDAO;
import sample.database.dataBaseUtil.DbCountryDAO;
import sample.database.dataBaseUtil.DbCustomerDAO;
import sample.validator.PersonValidator;
import sample.validator.Validator;


import java.util.Date;

public class DialogController {
    @FXML
    public TextField first_name;
    @FXML
    public TextField last_name;
    @FXML
    public TextField city;
    @FXML
    public TextField address;
    @FXML
    public TextField postal;
    @FXML
    public TextField country = new TextField();
    @FXML
    private DatePicker birth_date;

    private DbCustomerDAO customerDAO;
    private DbCountryDAO countryDAO;
    private DbAddressDAO dbAddressDAO;

    public void initialize() {
        customerDAO = new DbCustomerDAO();
        countryDAO = new DbCountryDAO();
        dbAddressDAO = new DbAddressDAO();
    }

    public Person getNewCustomer() {
        try {
            final String countryName = getTrimmedInput(country);
            final String cityName = getTrimmedInput(city);
            final String postalCode = getTrimmedInput(postal);
            final String address = this.address.getText();

            Address addressTemp = new Address(postalCode, address, cityName, countryName);
            Country country = getCountry(countryDAO, countryName);
            Person person = getPerson(customerDAO);

            dbAddressDAO.insertAll(addressTemp, person, country);
            CustomerListView.customerList.add(person);
            return person;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Person getPerson(DbCustomerDAO customerDAO) {
        Date birthDate = java.sql.Date.valueOf(birth_date.getValue());
        Person person = getPerson(birthDate);
        int customer_id = customerDAO.insert(person);
        person.setId(customer_id);
        return person;
    }

    private Person getPerson(Date birthDate) {
        PersonValidator.validateDate(birth_date);
        PersonValidator.validateName(first_name.getText(), last_name.getText());
        final String firstName = getTrimmedInput(first_name);
        final String lastName = getTrimmedInput(last_name);
        return new Person(firstName, lastName, birthDate);
    }

    private Country getCountry(DbCountryDAO countryDAO, String countryName) {
        final Country country = countryDAO.selectSpecificByString(countryName);
        Validator.objectNotNull(country);
        final int id_country = country.getId_country();
        return new Country(id_country, countryName);
    }

    private String getTrimmedInput(TextField textField) {
        return textField.getText().trim();
    }


}

package sample.dbUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Datamodel.Address;
import sample.Datamodel.Person;

import javax.swing.text.html.ListView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDbService {
    private static Connection conn;
    private ResultSet resultSet;
    private static PreparedStatement getCustomers;
    private static final String GET_CUSTOMERS =
            "SELECT * FROM person";

    static {
        conn = MysqlConnect.getInstance().connect();
        try {
            getCustomers = conn.prepareStatement(GET_CUSTOMERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Person> getCustomers() {
        List<Person> customer = new ArrayList<>();
        try {
            ResultSet customers = getCustomers.executeQuery();
            while (customers.next()) {
                final String first_name = customers.getString("first_name");
                final String last_name = customers.getString("last_name");
                final Date birth_date = customers.getDate("birth_date");
                customer.add(new Person(first_name, last_name, birth_date));
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

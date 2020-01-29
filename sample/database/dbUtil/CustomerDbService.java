package sample.database.dbUtil;

import sample.Datamodel.Person;

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

    /*public static List<Person> getCustomers() {
        List<Person> customer = new ArrayList<>();
        try {
            return getCustomers(getCustomers, customer);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}

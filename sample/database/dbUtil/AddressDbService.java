package sample.database.dbUtil;

import sample.Datamodel.Address;
import sample.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDbService {
    private static Connection conn;
    private ResultSet resultSet;
    private static PreparedStatement getUserAddress;
    private static final String GET_USER_ADDRESS =
            "SELECT postal_code, city_name, street, country_name FROM address " +
            "INNER JOIN country c on address.country_id = c.id_country " +
            "WHERE person_id = ?";

    static {
        conn = MysqlConnect.getInstance().connect();
        try {
            getUserAddress = conn.prepareStatement(GET_USER_ADDRESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Address getUserAddress(final int id) {
        try {
            Validator.validatePositiveNumbers(id);
            getUserAddress.setInt(1, id);
            ResultSet address = getUserAddress.executeQuery();
            if (address.next()) {
                String postal_code = address.getString("postal_code");
                String city = address.getString("city_name");
                String street = address.getString("street");
                String country = address.getString("country_name");
                return new Address(postal_code, street, city, country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}

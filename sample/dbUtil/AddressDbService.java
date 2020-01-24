package sample.dbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDbService {
    private static Connection conn;
    private ResultSet resultSet;
    private static PreparedStatement getUserAddress;
    private static final String GET_USER_ADDRESS = "SELECT * FROM address WHERE person_id = ?";

    static {
        conn = MysqlConnect.getInstance().connect();
        try {

            getUserAddress = conn.prepareStatement(GET_USER_ADDRESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

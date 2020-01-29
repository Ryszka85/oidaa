package sample.database.dataBaseUtil;

import sample.Datamodel.Address;
import sample.Datamodel.Country;
import sample.Datamodel.Person;

import java.sql.*;
import java.util.List;

public class DbAddressDAO implements OnlineShopDAO<Address, String> {
    private Connection conn;
    public static final String INSERT_STRING = "INSERT INTO address(postal_code, city_name, street, person_id, country_id)" +
            " VALUES (?, ?, ?, ?, ?)";
    private PreparedStatement insertAddress;

    public DbAddressDAO() {
        this.conn = DbConnectionPool.connect();
        try {
            insertAddress = conn.prepareStatement(INSERT_STRING, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertAll(Address value, Person customer, Country country) {
        try {
            System.out.println(customer.toString());
            insertAddress.setString(1, value.getPostal());
            insertAddress.setString(2, value.getCity());
            insertAddress.setString(3, value.getStreetAddress());
            insertAddress.setInt(4, customer.getId());
            insertAddress.setInt(5, country.getId_country());
            insertAddress.executeUpdate();
            System.out.println("hallo?");
            final ResultSet generatedKeys = insertAddress.getGeneratedKeys();
            if (generatedKeys.next()) return generatedKeys.getInt(1);
        } catch (SQLException e) {
            System.out.println("Inserting address failed!!");
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int insert(Address value) {
        try {
            insertAddress.setString(1, value.getPostal());
            insertAddress.setString(2, value.getCity());
            insertAddress.setString(3, value.getStreetAddress());
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Address> selectAll() {
        return null;
    }

    @Override
    public Address selectSpecificByInt(final int id) {
        try {
            ResultSet address = getResultSet(id);
            if (address.next())
                return getAddress(address);
        } catch (SQLException e) {
            System.out.println("ERROR!Selecting address failed.");
            e.printStackTrace();
        }
        return null;
    }

    private Address getAddress(final ResultSet address) throws SQLException {
        String postal_code = address.getString("postal_code");
        String city = address.getString("city_name");
        String street = address.getString("street");
        String country = address.getString("country_name");
        return new Address(postal_code, street, city, country);
    }

    private ResultSet getResultSet(final int id) throws SQLException {
        final String sqlString = "SELECT postal_code, city_name, street, country_name " +
                "FROM address INNER JOIN country c on address.country_id = c.id_country " +
                "WHERE person_id = ?";
        PreparedStatement selectAddress = conn.prepareStatement(sqlString);
        selectAddress.setInt(1, id);
        return selectAddress.executeQuery();
    }

    @Override
    public Address selectSpecificByString(String value) {
        return null;
    }
}

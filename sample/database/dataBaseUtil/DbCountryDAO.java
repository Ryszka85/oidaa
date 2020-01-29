package sample.database.dataBaseUtil;

import sample.Datamodel.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbCountryDAO implements OnlineShopDAO<Country, String> {
    public static final String POSTAL_CODE = "postal_code";
    public static final String STREET = "street";
    public static final String COUNTRY_NAME = "country_name";
    public static final String ID_COUNTRY = "id_country";
    public static final String COUNTRY_NAME1 = "country_name";
    private Connection conn;

    public DbCountryDAO() {
        this.conn = DbConnectionPool.connect();
    }

    @Override
    public List<Country> selectAll() {
        return null;
    }

    @Override
    public Country selectSpecificByInt(int value) {
        return null;
    }

    @Override
    public Country selectSpecificByString(final String value) {
        try {
            ResultSet selectedCountry = getSelectedCountryResult(value);
            if (selectedCountry.next()) return getCountry(selectedCountry);
        } catch (SQLException e) {
            System.out.println("ERROR!Selecting selecting country name failed!!");
            e.printStackTrace();
            return null;

        }
        return null;
    }

    private ResultSet getSelectedCountryResult(String value) throws SQLException {
        final String selectCountryString = "SELECT * FROM country WHERE country_name = ?";
        PreparedStatement selectCountry = conn.prepareStatement(selectCountryString);
        selectCountry.setString(1, value);
        return selectCountry.executeQuery();
    }

    private Country getCountry(ResultSet selectedCountry) throws SQLException {
        final int id_country = selectedCountry.getInt(ID_COUNTRY);
        final String country_name = selectedCountry.getString(COUNTRY_NAME1);
        return new Country(id_country, country_name);
    }

    @Override
    public int insert(Country value) {
        return 0;
    }
}

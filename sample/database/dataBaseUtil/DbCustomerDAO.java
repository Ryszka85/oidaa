package sample.database.dataBaseUtil;

import sample.Datamodel.Person;
import sample.database.dbUtil.MysqlConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCustomerDAO implements OnlineShopDAO<Person, String> {
    private Connection conn;
    PreparedStatement test;

    public DbCustomerDAO() {
        this.conn = MysqlConnect.getInstance().connect();
    }

    @Override
    public int insert(Person value) {
        PreparedStatement preparedStatement = null;
        try {
            final String sqlQueryString = "INSERT INTO person(first_name, last_name, birth_date) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(sqlQueryString);
            preparedStatement = getPreparedInsertStatement(value);
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) return generatedKeys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    

    private PreparedStatement getPreparedInsertStatement(Person value) throws SQLException {
        final String sqlQueryString = "INSERT INTO person(first_name, last_name, birth_date) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sqlQueryString, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, value.getFirstName());
        preparedStatement.setString(2, value.getLastName());
        preparedStatement.setDate(3, new Date(value.getBirthDate().getTime()));
        return preparedStatement;
    }

    @Override
    public Person selectSpecificByInt(final int id) {
        final String queryString = "SELECT * FROM person WHERE id_person = ?";
        Person customer;
        PreparedStatement preparedStatement = null;
        try {
            final ResultSet query = initResultSet(id);
            if (query.next()) return setPerson(query);
        } catch (SQLException e) {
            System.out.println("ERROR!!Person query went wrong");
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private ResultSet initResultSet(final int id) throws SQLException {
        final String sql = "SELECT * FROM person WHERE id_person = ?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    private Person setPerson(ResultSet query) throws SQLException {
        final int id_person = query.getInt("id_person");
        final String first_name = query.getString("first_name");
        final String last_name = query.getString("last_name");
        final Date birth_date = query.getDate("birth_date");
        return new Person(id_person,first_name, last_name, birth_date);
    }

    @Override
    public Person selectSpecificByString(String value) {
        final String queryString = "SELECT * FROM person WHERE first_name = ? AND last_name = ?";
        String[] arr = value.split(" ");
        final String first = arr[0].trim();
        final String last = arr[1].trim();
        PreparedStatement preparedStatement = null;
        try {
            final ResultSet query = initNameQuery(first, last);
            if (query.next()) return setPerson(query);
        } catch (SQLException e) {
            System.out.println("Error!!Searching for Person with name " + first + " " + last + " failed.");
            e.printStackTrace();
        }
        return null;

    }

    private ResultSet initNameQuery(String first, String last) throws SQLException {
        final String sql = "SELECT * FROM person WHERE first_name = ? AND last_name = ?";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, last);
        return preparedStatement.executeQuery();
    }

    @Override
    public List<Person> selectAll() {
        final String GET_CUSTOMERS = "SELECT * FROM person";
        PreparedStatement preparedCustomerStatement = null;
        try {
            preparedCustomerStatement = conn.prepareStatement(GET_CUSTOMERS);
            return mapCustomerToList(preparedCustomerStatement);
        } catch (SQLException e) {
            System.out.println("ERROR!! Preparing statement failed");
            e.printStackTrace();
        }
        return null;
    }

    private List<Person> mapCustomerToList(PreparedStatement preparedCustomerStatement)  {
        List<Person> customers = new ArrayList<>();
        try {
            return getCustomers(preparedCustomerStatement, customers);
        } catch (SQLException e) {
            System.out.println("ERROR! Executing query went wrong");
            e.printStackTrace();
            return customers;
        }
    }

    public static List<Person> getCustomers(PreparedStatement preparedCustomerStatement,
                                            List<Person> customers) throws SQLException {
        ResultSet customersResultSet = preparedCustomerStatement.executeQuery();
        while (customersResultSet.next()) 
            addCustomersToList(customers, customersResultSet);
        return customers;
    }

    private static void addCustomersToList(List<Person> customers,
                                           ResultSet customersResultSet) throws SQLException {
        final int id_person = customersResultSet.getInt("id_person");
        final String first_name = customersResultSet.getString("first_name");
        final String last_name = customersResultSet.getString("last_name");
        final Date birth_date = customersResultSet.getDate("birth_date");
        customers.add(new Person(id_person, first_name, last_name, birth_date));
    }
}

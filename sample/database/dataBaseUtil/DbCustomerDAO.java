package sample.database.dataBaseUtil;

import sample.Datamodel.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbCustomerDAO implements CustomerDAO {
    private Connection conn;

    public DbCustomerDAO() {
        this.conn = DbConnectionPool.connect();
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
            ResultSet customersResultSet = preparedCustomerStatement.executeQuery();
            while (customersResultSet.next()) {
                final int id_person = customersResultSet.getInt("id_person");
                final String first_name = customersResultSet.getString("first_name");
                final String last_name = customersResultSet.getString("last_name");
                final Date birth_date = customersResultSet.getDate("birth_date");
                customers.add(new Person(id_person, first_name, last_name, birth_date));
            }
            return customers;
        } catch (SQLException e) {
            System.out.println("ERROR! Executing query went wrong");
            e.printStackTrace();
            return customers;
        }
    }
}

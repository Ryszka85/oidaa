package sample.database.dataBaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionPool {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    private static Connection connection;
    private static Properties properties;
    private static DbConnectionPool instance;

    private DbConnectionPool() { }

    public static DbConnectionPool getInstance() {
        if (instance == null) instance = new DbConnectionPool();
        return instance;
    }

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    public static Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Database connection error");
            }
        }
        return connection;
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                /*connection = null;*/
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Disconnecting database failed!");
            }
        }
    }
}



package sample.database.dataBaseUtil;

import java.sql.Connection;

public class DbFactoryDAO {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    public static Connection createConnection() { return DbConnectionPool.connect(); }

    public static void diconnect() { DbConnectionPool.disconnect(); }

    public static DbCustomerDAO getCustomerDAO() { return new DbCustomerDAO(); }

}

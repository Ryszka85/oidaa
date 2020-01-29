package sample.database.dataBaseUtil;

import java.sql.Connection;

public class DbFactoryDAO {

    public static Connection createConnection() { return DbConnectionPool.connect(); }

    public static void diconnect() { DbConnectionPool.disconnect(); }

    public static DbCustomerDAO getCustomerDAO() { return new DbCustomerDAO(); }

}

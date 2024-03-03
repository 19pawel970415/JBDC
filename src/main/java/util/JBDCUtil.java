package util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCUtil {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/jbdcexsshema";

    private static final String DB_USER_NAME = "root";

    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
    }

    //or

    public static Connection alternativeGetConnection() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(CONNECTION_URL);
        mysqlDataSource.setUser(DB_USER_NAME);
        mysqlDataSource.setURL(DB_PASSWORD);
        return mysqlDataSource.getConnection();
    }



}

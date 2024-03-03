import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdcexsshema", "root", ""); Statement statement = connection.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("author"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("isbn"));
                System.out.println(resultSet.getDate("release_date"));
                System.out.println(resultSet.getString("title"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //or

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/jbdcexsshema");
        dataSource.setUser("root");
        dataSource.setPassword("");

        try (final Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("author"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("isbn"));
                System.out.println(resultSet.getDate("release_date"));
                System.out.println(resultSet.getString("title"));
            }

        } catch (SQLException exp) {
            exp.getErrorCode();
        }
    }
}

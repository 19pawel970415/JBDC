import util.JBDCUtil;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Statement statement = JBDCUtil.getConnection().createStatement()){
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

        try (Statement statement = JBDCUtil.getConnection().createStatement()) {

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

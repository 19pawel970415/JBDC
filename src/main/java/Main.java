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

        String insertIntoBook = "INSERT INTO book (id, author, description, isbn, release_date, title) " +
                "VALUES (5, 'Michał Nowak', 'Description of book 1', '1234567890', '2024-03-03', 'Book Title 1')";

        String insertIntoBook1 = "INSERT INTO book (id, author, description, isbn, release_date, title) " +
                "VALUES (6, 'Adam Nowak', 'Description of book 2', '0987654321', '2024-03-03', 'Book Title 2')";

        String updateBook = "UPDATE book SET isbn = '0987654321' WHERE id = 1";

        String deleteFromBook = "DELETE FROM book WHERE title = 'Book Title 1'";

        String dropTable = "DROP TABLE book";

        try (Statement statement = JBDCUtil.getConnection().createStatement()){
            statement.execute(insertIntoBook);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Statement statement = JBDCUtil.getConnection().createStatement()){
            statement.execute(insertIntoBook1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Statement statement = JBDCUtil.getConnection().createStatement()){
            statement.execute(updateBook);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (Statement statement = JBDCUtil.getConnection().createStatement()){
            statement.execute(deleteFromBook);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sql = "UPDATE book SET isbn = ? WHERE id = ?";

        try (PreparedStatement statement = JBDCUtil.getConnection().prepareStatement(sql)){
            statement.setString(1,"1234567891011");
            statement.setInt(2,1);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (CallableStatement statement = JBDCUtil.getConnection().prepareCall("{call selectAllFromBook}")){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("isbn"));
                System.out.println(resultSet.getString("description"));
                System.out.println(resultSet.getString("author"));
                System.out.println(resultSet.getDate("release_date"));
                System.out.println(resultSet.getString("title"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

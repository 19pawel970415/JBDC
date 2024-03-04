package service;

import model.Book;
import util.JBDCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class BookService {

    //Stworzyć metodę, która zwróci wszystkie książki (listę obiektów) wydane przed zadaną w argumencie datą.
    public static ArrayList<Book> getAllBooksBefore(Date date) {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM book WHERE release_date < ?";
        try (PreparedStatement statement = JBDCUtil.getConnection().prepareStatement(sql)) {
            statement.setDate(1, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                Date release_date = resultSet.getDate("release_date");
                String title = resultSet.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki, których autorem jest osoba wskazana jako argument metody.
    public static ArrayList<Book> getAllBooksBy(String theAuthor) {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM book WHERE author = 'Adam Smith'";
        try (Statement statement = JBDCUtil.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                Date release_date = resultSet.getDate("release_date");
                String title = resultSet.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki, których autorem jest osoba wskazana jako argument metody z preparedStatement
    public static ArrayList<Book> getAllBooksByPS(String theAuthor) {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM book WHERE author = ?";
        try (PreparedStatement statement = JBDCUtil.getConnection().prepareStatement(sql)) {
            statement.setString(1, theAuthor);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                Date release_date = resultSet.getDate("release_date");
                String title = resultSet.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki posortowane od A do Z po tytule.
    public static ArrayList<Book> getAllBooksSortedByTitle() {
        ArrayList<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM book ORDER BY title";
        try (Statement statement = JBDCUtil.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                Date release_date = resultSet.getDate("release_date");
                String title = resultSet.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }

    //Stworzyć metodę, która zwróci książkę lub null, na podstawie isbn przekazanego jako parametr metody.
    public static Book getAllBooksByISBN(String theISBN) {
        Optional<Book> book = null;
        String sql = "SELECT * FROM book WHERE ISBN = ?";
        try (PreparedStatement statement = JBDCUtil.getConnection().prepareStatement(sql)) {
            statement.setString(1, theISBN);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                Date release_date = resultSet.getDate("release_date");
                String title = resultSet.getString("title");
                Book theBook = new Book(id, author, description, isbn, release_date, title);
                book = Optional.of(theBook);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (book.isPresent()) {
                Book returnBook = book.get();
                return returnBook;
            } else {
                return null;
            }
        } catch (NullPointerException npe) {
            return null;
        }
    }
}

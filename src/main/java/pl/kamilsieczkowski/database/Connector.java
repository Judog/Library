package pl.kamilsieczkowski.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamilsieczkowski.DTO.Book;
import pl.kamilsieczkowski.utils.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static pl.kamilsieczkowski.constants.Constants.*;

public class Connector {
    public User connectUsersDatabase(String enteredLogin) throws SQLException {
        String query = "SELECT * FROM library_users.users WHERE username='";
        String enteredQuery = query + enteredLogin + "'"; // select row of entered login, from users database
        User user = new User(enteredLogin, null, null);
        ResultSet resultSet = connectIntoDatabase(enteredQuery);
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String privilege = resultSet.getString("privilege");
            user = new User(username, password, privilege);
        }
        return user;
    }

    public ObservableList<Book> connectBooksDatabase() throws SQLException {
        String enteredQuery = "SELECT * FROM library_users.books;";
        ResultSet resultSet = connectIntoDatabase(enteredQuery);
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            int id_books = resultSet.getInt("id_books");
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");
            String keyWords = resultSet.getString("key_words");
            int tome = resultSet.getInt("tome");
            String edition = resultSet.getString("edition");
            String localization = resultSet.getString("localization");
            bookList.add(new Book(id_books, author, title, keyWords, tome, edition, localization));
        }
        return bookList;
    }

    ResultSet connectIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = DriverManager.getConnection(SERVER_URL, SERVER_USER, SERVER_PASSWORD);
        PreparedStatement pst = con.prepareStatement(enteredQuery);
        return pst.executeQuery();
    }
}

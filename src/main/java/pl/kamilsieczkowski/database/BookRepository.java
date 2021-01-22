package pl.kamilsieczkowski.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamilsieczkowski.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class BookRepository {
    private Connector connector;

    public BookRepository(Connector connector) {
        this.connector = connector;
    }

    public ObservableList<Book> getAllBooks() {
        String queryGetAllBooks = "SELECT * FROM library_users.books;";
        ResultSet resultSet = connector.downloadFromDatabase(queryGetAllBooks);
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                int id_books = resultSet.getInt(COLUMN_ID_BOOK);
                String author = resultSet.getString(COLUMN_AUTHOR);
                String title = resultSet.getString(COLUMN_TITLE);
                String keyWords = resultSet.getString(COLUMN_KEY_WORDS);
                int tome = resultSet.getInt(COLUMN_TOME);
                String edition = resultSet.getString(COLUMN_EDITION);
                String localization = resultSet.getString(COLUMN_LOCALIZATION);
                bookList.add(new Book.BookBuilder()
                        .setId_book(id_books)
                        .setAuthor(author)
                        .setTitle(title)
                        .setKeyWords(keyWords)
                        .setTome(tome)
                        .setEdition(edition)
                        .setLocalization(localization)
                        .createBook());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }
}

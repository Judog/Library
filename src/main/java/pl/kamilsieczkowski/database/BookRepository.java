package pl.kamilsieczkowski.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamilsieczkowski.DTO.Book;

import java.sql.ResultSet;

import static pl.kamilsieczkowski.constants.Constants.*;

public class BookRepository extends Connector {

    public ObservableList<Book> downloadBookList() throws Throwable {
        ResultSet resultSet = downloadFromDatabase(QUERY_ALL_BOOKS);
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            int id_books = resultSet.getInt(COLUMN_ID_BOOK);
            String author = resultSet.getString(COLUMN_AUTHOR);
            String title = resultSet.getString(COLUMN_TITLE);
            String keyWords = resultSet.getString(COLUMN_KEY_WORDS);
            int tome = resultSet.getInt(COLUMN_TOME);
            String edition = resultSet.getString(COLUMN_EDITION);
            String localization = resultSet.getString(COLUMN_LOCALIZATION);
            bookList.add(new Book(id_books, author, title, keyWords, tome, edition, localization));
        }
        return bookList;
    }
}

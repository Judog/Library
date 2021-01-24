package pl.kamilsieczkowski.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static pl.kamilsieczkowski.constants.Constants.*;

public class BookRepository {
    private final Connector connector;
    public static final Logger LOG = LogManager.getLogger(BookRepository.class);
    private final String QUERY_GET_ALL_BOOKS = "SELECT * FROM library_users.books;";
    private final String QUERY_INSERT = "INSERT INTO library_users.books VALUES (";
    private final String QUERY = "SELECT * FROM library_users.books WHERE ";

    public BookRepository(Connector connector) {
        this.connector = connector;
    }

    public List<Book> getAllBooks() {
        ResultSet resultSet = this.connector.downloadFromDatabase(this.QUERY_GET_ALL_BOOKS);
        List<Book> bookList = new ArrayList<>();
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
        } catch (SQLException exception) {
            LOG.error("Can't get bookList from server");
        }
        return bookList;
    }

    public void insertIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = this.connector.getDatabaseConnection();
        Statement st = con.createStatement();
        st.executeUpdate(enteredQuery);
        con.close();
    }

    public void insertBook(String book_id, String author,
                           String title, String keyWords, String edition, String tome) {
        StringBuilder enteredQuery =new StringBuilder().append(this.QUERY_INSERT).append("'").append(book_id).append("', '")
                .append(author).append("', '").append(title).append("', '").append(keyWords).append("', '" )
                .append(tome).append("', '").append(edition).append("', '").append("library'").append(");");
        try {
            insertIntoDatabase(enteredQuery.toString());
        } catch (SQLException e) {
            LOG.error("Can't send query to server");
        }
    }

    public List<Book> getSearchedBooks(String id_number, String placement, String author, String title, String keyWords) throws SQLException {
        String enteredQuery = this.QUERY +
                COLUMN_ID_BOOK + " LIKE '%" + id_number + "%' AND " +
                COLUMN_AUTHOR + " LIKE '%" + author + "%' AND " +
                COLUMN_TITLE + " LIKE '%" + title + "%' AND " +
                COLUMN_KEY_WORDS + " LIKE '%" + keyWords + "%' AND " +
                COLUMN_LOCALIZATION + " LIKE '%" + getPlacement(placement) + "%';";
        ResultSet resultSet = this.connector.downloadFromDatabase(enteredQuery);
        List<Book> searchedBooks = new ArrayList<>();
        while (resultSet.next()) {
            Book book = new Book.BookBuilder()
                    .setId_book(resultSet.getInt(COLUMN_ID_BOOK))
                    .setLocalization(resultSet.getString(COLUMN_LOCALIZATION))
                    .setAuthor(resultSet.getString(COLUMN_AUTHOR))
                    .setTitle(resultSet.getString(COLUMN_TITLE))
                    .setEdition(resultSet.getString(COLUMN_EDITION))
                    .setTome(resultSet.getInt(COLUMN_TOME))
                    .setKeyWords(resultSet.getString(COLUMN_KEY_WORDS))
                    .createBook();
            searchedBooks.add(book);
        }
        return searchedBooks;
    }

    private String getPlacement(String placement) {
        if (placement.equals("All")) {
            placement = "";
            // for searching in query, all meaning in library and borrowed -
            // must be empty for searching result with library and borrowed placement
        }
        return placement;
    }
}

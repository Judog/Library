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
    public static final Logger LOG = LogManager.getLogger(BookRepository.class);
    private final Connector connector;
    private static final String QUERY_GET_ALL_BOOKS = "SELECT * FROM library_users.books;";
    private static final String QUERY_INSERT = "INSERT INTO library_users.books VALUES (";
    private static final String QUERY_EDIT = "UPDATE library_users.books SET";
    private static final String QUERY = "SELECT * FROM library_users.books WHERE ";

    public BookRepository(Connector connector) {
        this.connector = connector;
    }

    public List<Book> getBookList() {
        ResultSet resultSet = this.connector.downloadFromDatabase(QUERY_GET_ALL_BOOKS);
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
            LOG.error("Can't get bookList from server", exception);
        }
        return bookList;
    }

    public void executeQuery(String enteredQuery) throws SQLException {
        Connection con = this.connector.getDatabaseConnection();
        Statement st = con.createStatement();
        st.executeUpdate(enteredQuery);
        con.close();
    }

    public void insertBook(Book book) {
        try {
            executeQuery(getInsertBookQuery(book));
        } catch (SQLException e) {
            LOG.error("Can't send query to server", e);
        }
    }

    public void updateBook(Book book, int idEditedBook) {
        try {
            executeQuery(getEditBookQuery(book, idEditedBook));
        } catch (SQLException e) {
            LOG.error("Can't send query to server", e);
        }
    }

    private String getEditBookQuery(Book book, int idEditedBook) {
        return new StringBuilder(QUERY_EDIT).append("`id_books`='").append(book.getId_book()).append("', ")
                .append("`title` = '").append(book.getTitle()).append("', ")
                .append("`author` = '").append(book.getAuthor()).append("', ")
                .append("`key_words` = '").append(book.getKeyWords()).append("', ")
                .append("`tome` = '").append(book.getTome()).append("', ")
                .append("`edition` = '").append(book.getEdition())
                .append("' WHERE `id_books`='").append(idEditedBook).append("';").toString();
    }

    private String getInsertBookQuery(Book book) {
        return new StringBuilder(QUERY_INSERT).append("'").append(book.getId_book()).append("', '")
                .append(book.getAuthor()).append("', '").append(book.getTitle()).append("', '").append(book.getKeyWords()).append("', '")
                .append(book.getTome()).append("', '").append(book.getEdition()).append("', '").append("library'").append(");").toString();
    }

    public List<Book> searchBooks(Book searchedBook) throws SQLException {
        ResultSet resultSet = this.connector.downloadFromDatabase(getSearchedBooksQuery(searchedBook));
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

    private String getSearchedBooksQuery(Book searchedBook) {
        return new StringBuilder(QUERY)
                .append(COLUMN_ID_BOOK).append(" LIKE '%").append(searchedBook.getId_book()).append("%' AND ")
                .append(COLUMN_AUTHOR).append(" LIKE '%").append(searchedBook.getAuthor()).append("%' AND ")
                .append(COLUMN_TITLE).append(" LIKE '%").append(searchedBook.getTitle()).append("%' AND ")
                .append(COLUMN_KEY_WORDS).append(" LIKE '%").append(searchedBook.getKeyWords()).append("%' AND ")
                .append(COLUMN_LOCALIZATION).append(" LIKE '%").append(getPlacement(searchedBook.getLocalization())).append("%';")
                .toString();
    }

    private String getPlacement(String placement) {
        if (placement.equals("All")) {
            placement = "";
           //placement method created for SQL. All needs to be changed for empty string
        }
        return placement;
    }
}

package pl.kamilsieczkowski.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static pl.kamilsieczkowski.constants.Constants.*;
import static pl.kamilsieczkowski.constants.Texts.SQL_EXCEPTION;

public class BookRepository {
    private final TextField id_numberTextField;
    private final TextField authorTextField;
    private final TextField titleTextField;
    private final TextField keywordsTextField;
    private final TextField editionTextField;
    private final TextField tomeTextField;
    private final Connector connector;
    public static final Logger LOG = LogManager.getLogger(BookRepository.class);

    private BookRepository(TextField id_numberTextField, TextField authorTextField,
                           TextField titleTextField, TextField keywordsTextField,
                           TextField editionTextField, TextField tomeTextField, Connector connector) {
        this.id_numberTextField = id_numberTextField;
        this.authorTextField = authorTextField;
        this.titleTextField = titleTextField;
        this.keywordsTextField = keywordsTextField;
        this.editionTextField = editionTextField;
        this.tomeTextField = tomeTextField;
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
        } catch (SQLException exception) {
            LOG.error(SQL_EXCEPTION + "getAllBooks");
        }
        return bookList;
    }

    public void insertIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = connector.getDatabaseConnection();
        Statement st = con.createStatement();
        st.executeUpdate(enteredQuery);
        con.close();
    }

    public void insertBook() {
        String queryInsert = "INSERT INTO library_users.books VALUES (";
        String book_id = id_numberTextField.getText();
        String author = authorTextField.getText();
        String title = titleTextField.getText();
        String keyWords = keywordsTextField.getText();
        String edition = editionTextField.getText();
        String tome = tomeTextField.getText();
        String enteredQuery = queryInsert + "'" + book_id + "', '" +
                author + "', '" + title + "', '" + keyWords + "', '" +
                tome + "', '" + edition + "', '" + "library'" + ");";
        try {
            insertIntoDatabase(enteredQuery);
        } catch (SQLException e) {
            LOG.error(SQL_EXCEPTION + " BookInserterRepository insertBook");
        }
        connector.closeConnection();
    }

    public static class BookRepositoryBuilder {
        private TextField id_numberTextField;
        private TextField authorTextField;
        private TextField titleTextField;
        private TextField keywordsTextField;
        private TextField editionTextField;
        private TextField tomeTextField;
        private Connector connector;

        public BookRepositoryBuilder setId_numberTextField(TextField id_numberTextField) {
            this.id_numberTextField = id_numberTextField;
            return this;
        }

        public BookRepositoryBuilder setAuthorTextField(TextField authorTextField) {
            this.authorTextField = authorTextField;
            return this;
        }

        public BookRepositoryBuilder setTitleTextField(TextField titleTextField) {
            this.titleTextField = titleTextField;
            return this;
        }

        public BookRepositoryBuilder setKeywordsTextField(TextField keywordsTextField) {
            this.keywordsTextField = keywordsTextField;
            return this;
        }

        public BookRepositoryBuilder setEditionTextField(TextField editionTextField) {
            this.editionTextField = editionTextField;
            return this;
        }

        public BookRepositoryBuilder setTomeTextField(TextField tomeTextField) {
            this.tomeTextField = tomeTextField;
            return this;
        }

        public BookRepositoryBuilder setConnector(Connector connector) {
            this.connector = connector;
            return this;
        }

        public BookRepository createBookRepository() {
            return new BookRepository(id_numberTextField, authorTextField, titleTextField, keywordsTextField, editionTextField, tomeTextField, connector);
        }
    }
}

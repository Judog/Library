package pl.kamilsieczkowski.database;

import javafx.scene.control.TextField;

import java.sql.*;

import static pl.kamilsieczkowski.constants.Constants.LOG;
import static pl.kamilsieczkowski.constants.Texts.SQL_EXCEPTION;

public class BookInserterRepository {
    private TextField id_numberTextField;
    private TextField authorTextField;
    private TextField titleTextField;
    private TextField keywordsTextField;
    private TextField editionTextField;
    private TextField tomeTextField;
    private Connector connector;

    private BookInserterRepository(TextField id_numberTextField, TextField authorTextField,
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

    public void insertIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = connector.connectIntoDatabase();
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
    }

    public static class BookInserterRepositoryBuilder {
        private TextField id_numberTextField;
        private TextField authorTextField;
        private TextField titleTextField;
        private TextField keywordsTextField;
        private TextField editionTextField;
        private TextField tomeTextField;
        private Connector connector;

        public BookInserterRepositoryBuilder setId_numberTextField(TextField id_numberTextField) {
            this.id_numberTextField = id_numberTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setAuthorTextField(TextField authorTextField) {
            this.authorTextField = authorTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setTitleTextField(TextField titleTextField) {
            this.titleTextField = titleTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setKeywordsTextField(TextField keywordsTextField) {
            this.keywordsTextField = keywordsTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setEditionTextField(TextField editionTextField) {
            this.editionTextField = editionTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setTomeTextField(TextField tomeTextField) {
            this.tomeTextField = tomeTextField;
            return this;
        }

        public BookInserterRepositoryBuilder setConnector(Connector connector) {
            this.connector = connector;
            return this;
        }

        public BookInserterRepository createInserter() {
            return new BookInserterRepository(id_numberTextField, authorTextField, titleTextField, keywordsTextField, editionTextField, tomeTextField, connector);
        }
    }
}

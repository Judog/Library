package pl.kamilsieczkowski.database;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.*;

public class Inserter {
    @FXML
    private TextField id_numberTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField keywordsTextField;
    @FXML
    private TextField editionTextField;
    @FXML
    private TextField tomeTextField;
    private Connector connector;

    public Inserter(TextField id_numberTextField, TextField authorTextField,
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
            e.printStackTrace();
        }
    }
}

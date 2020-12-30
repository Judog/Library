package pl.kamilsieczkowski.database;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

import static pl.kamilsieczkowski.constants.Constants.QUERY_INSERT;

public class Inserter extends Connector {
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

    public Inserter(TextField id_numberTextField, TextField authorTextField,
                    TextField titleTextField, TextField keywordsTextField,
                    TextField editionTextField, TextField tomeTextField) {
        this.id_numberTextField = id_numberTextField;
        this.authorTextField = authorTextField;
        this.titleTextField = titleTextField;
        this.keywordsTextField = keywordsTextField;
        this.editionTextField = editionTextField;
        this.tomeTextField = tomeTextField;
    }

    public void insertIntoDatabase(String enteredQuery) throws SQLException {
        Connection con = connectIntoDatabase();
        Statement st = con.createStatement();
        st.executeUpdate(enteredQuery);
        con.close();
    }
    public void insertBook() throws Throwable {
        String book_id = id_numberTextField.getText();
        String author = authorTextField.getText();
        String title = titleTextField.getText();
        String keyWords = keywordsTextField.getText();
        String edition = editionTextField.getText();
        String tome = tomeTextField.getText();
        String enteredQuery = QUERY_INSERT + "'" + book_id + "', '" +
                author + "', '" + title + "', '" + keyWords + "', '" +
                tome + "', '" + edition + "', '" + "library'" + ");";
        try {
            insertIntoDatabase(enteredQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

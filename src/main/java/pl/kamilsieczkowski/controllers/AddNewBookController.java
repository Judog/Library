package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.database.Inserter;
import pl.kamilsieczkowski.utils.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_LIBRARY_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class AddNewBookController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button insertButton;

    @FXML
    private Button endButton;


    @FXML
    private Label id_numberLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label keyWordsLabel;

    @FXML
    private Label editionLabel;

    @FXML
    private Label tomeLabel;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Window window = new Window();
        setWindowText();
        try {
            id_numberTextField.setText(setDefaultBookIdentifier(new BookRepository()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        endButton.setOnAction(event -> {
            window.getWindow(pane).close();
            try {
                window.openLibraryWindow(SOURCE_LIBRARY_WINDOW, LOGGED_IN);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        insertButton.setOnAction(event -> {
            try {
                Inserter inserter = new Inserter(id_numberTextField, authorTextField,
                        titleTextField, keywordsTextField, editionTextField, tomeTextField);
                inserter.insertBook();
                window.openLibraryWindow(SOURCE_LIBRARY_WINDOW, LOGGED_IN);
                window.getWindow(pane).close();

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private String setDefaultBookIdentifier(BookRepository bookRepository) throws Throwable {
        return Integer.toString(bookRepository.downloadBookList().size() + 1);
    }

    private void setWindowText() {
        insertButton.setText(INSERT);
        endButton.setText(END);
        id_numberLabel.setText(ID_NUMBER);
        authorLabel.setText(AUTHOR);
        titleLabel.setText(TITLE);
        keyWordsLabel.setText(KEY_WORDS);
        editionLabel.setText(EDITION);
        tomeLabel.setText(TOME);
    }
}

package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.database.BookInserterRepository;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import static pl.kamilsieczkowski.constants.Constants.LOG;
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
        setWindowText();
        id_numberTextField.setText(setDefaultBookIdentifier(getBookRepository()));
        endButton.setOnAction(event -> {
            clickOnEndButton(getWindow());
        });
        insertButton.setOnAction(event -> {
            clickOnInsertButton(getWindow());
        });
    }

    private Window getWindow() {
        return new Window();
    }

    private void clickOnEndButton(Window window) {
        window.closeWindow(pane);
        window.openNewWindow(SOURCE_LIBRARY_WINDOW, LOGGED_IN);
    }

    private void clickOnInsertButton(Window window) {
        BookInserterRepository bookInserterRepository = new BookInserterRepository.BookInserterRepositoryBuilder()
                .setId_numberTextField(id_numberTextField)
                .setAuthorTextField(authorTextField)
                .setTitleTextField(titleTextField)
                .setKeywordsTextField(keywordsTextField)
                .setEditionTextField(editionTextField)
                .setTomeTextField(tomeTextField)
                .setConnector(new Connector())
                .createInserter();
        insertNewBook(window, bookInserterRepository);
    }

    private BookRepository getBookRepository() {
        return new BookRepository(new Connector());
    }

    private void insertNewBook(Window window, BookInserterRepository bookInserterRepository) {
        bookInserterRepository.insertBook();
        window.openNewWindow(SOURCE_LIBRARY_WINDOW, LOGGED_IN);
        window.closeWindow(pane);
    }

    private String setDefaultBookIdentifier(BookRepository bookRepository) {
        String bookIdentifier = "";
        try {
            bookIdentifier = Integer.toString(bookRepository.getAllBooks().size() + 1);
        } catch (SQLException e) {
            LOG.error(SQL_EXCEPTION + "AddNewBookController setDefaultBookIdentifier");
        }
        return bookIdentifier;
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

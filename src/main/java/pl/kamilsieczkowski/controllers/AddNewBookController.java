package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.model.Book;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_LIBRARY_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class AddNewBookController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Button saveButton;

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
        id_numberTextField.setText(setDefaultBookIdentifier(
                new BookRepository(new Connector())));
        endButton.setOnAction(event -> clickOnEndButton(new Window()));
        saveButton.setOnAction(event -> clickOnSaveButton(new Window()));
    }

    private void clickOnEndButton(Window window) {
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void clickOnSaveButton(Window window) {
        BookRepository bookRepository = new BookRepository(new Connector());
        insertNewBook(window, bookRepository);
    }

    private void insertNewBook(Window window, BookRepository bookRepository) {
        bookRepository.insertBook(new Book.BookBuilder()
                .setId_book(Integer.parseInt(id_numberTextField.getText()))
                .setAuthor(authorTextField.getText())
                .setTitle(titleTextField.getText())
                .setKeyWords(keywordsTextField.getText())
                .setEdition(editionTextField.getText())
                .setTome(Integer.parseInt(tomeTextField.getText()))
                .createBook());
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private String setDefaultBookIdentifier(BookRepository bookRepository) {
        return Integer.toString(bookRepository.getAllBooks().size() + 1);
    }

    private void setWindowText() {
        saveButton.setText(SAVE);
        endButton.setText(END);
        id_numberLabel.setText(ID_NUMBER);
        authorLabel.setText(AUTHOR);
        titleLabel.setText(TITLE);
        keyWordsLabel.setText(KEY_WORDS);
        editionLabel.setText(EDITION);
        tomeLabel.setText(TOME);
    }
}

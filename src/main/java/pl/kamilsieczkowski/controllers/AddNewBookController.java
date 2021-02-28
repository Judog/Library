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
    private Label idNumberLabel;

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
    private TextField idNumberTextField;

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
    private Window window;
    private BookRepository bookRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInstances();
        setWindowText();
        idNumberTextField.setText(iterateBookId(
                new BookRepository(connector)));
        endButton.setOnAction(event -> executeEndButton(window));
        saveButton.setOnAction(event -> executeSaveButton(window, bookRepository));
    }

    private void executeEndButton(Window window) {
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void executeSaveButton(Window window, BookRepository bookRepository) {
        insertNewBook(bookRepository);
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void setInstances() {
        this.connector = new Connector();
        this.window = new Window();
        this.bookRepository = new BookRepository(connector);
    }

    private void insertNewBook(BookRepository bookRepository) {
        bookRepository.insertBook(new Book.BookBuilder()
                .setId_book(Integer.parseInt(idNumberTextField.getText()))
                .setAuthor(authorTextField.getText())
                .setTitle(titleTextField.getText())
                .setKeyWords(keywordsTextField.getText())
                .setEdition(editionTextField.getText())
                .setTome(Integer.parseInt(tomeTextField.getText()))
                .createBook());
    }

    private String iterateBookId(BookRepository bookRepository) {
        return Integer.toString(bookRepository.getBookList().size() + 1);
    }

    private void setWindowText() {
        saveButton.setText(SAVE);
        endButton.setText(END);
        idNumberLabel.setText(ID_NUMBER);
        authorLabel.setText(AUTHOR);
        titleLabel.setText(TITLE);
        keyWordsLabel.setText(KEY_WORDS);
        editionLabel.setText(EDITION);
        tomeLabel.setText(TOME);
    }
}

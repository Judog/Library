package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.model.Book;
import pl.kamilsieczkowski.dto.BookDTO;
import pl.kamilsieczkowski.utils.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_LIBRARY_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class EditBookController implements Initializable {
    public static final Logger LOG = LogManager.getLogger(EditBookController.class);
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
    private Book bookToEdit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getLabelsToEdit(getBookToEdit());
        } catch (IOException e) {
            LOG.error("Can't get book to edition", e);
        }
        setWindowText();
        endButton.setOnAction(event -> executeEndButton(new Window()));
        saveButton.setOnAction(event -> executeSaveButton(new Window()));
    }

    private Book getBookToEdit() throws IOException {
        bookToEdit = null;
        if (BookDTO.isIsBookAvailable()) {
            bookToEdit = BookDTO.getBook();
        } else {
            throw new IOException();
        }
        return bookToEdit;
    }

    private void getLabelsToEdit(Book bookToEdit) {
        idNumberTextField.setText(Integer.toString(bookToEdit.getId_book()));
        authorTextField.setText(bookToEdit.getAuthor());
        titleTextField.setText(bookToEdit.getTitle());
        keywordsTextField.setText(bookToEdit.getKeyWords());
        editionTextField.setText(bookToEdit.getEdition());
        tomeTextField.setText(Integer.toString(bookToEdit.getTome()));
    }

    private void executeEndButton(Window window) {
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void executeSaveButton(Window window) {
        editBook(window, new BookRepository(new Connector()));
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void editBook(Window window, BookRepository bookRepository) {
        bookRepository.updateBook(mapBook(),
                bookToEdit.getId_book());
    }

    private Book mapBook() {
        return new Book.BookBuilder()
                .setId_book(Integer.parseInt(idNumberTextField.getText()))
                .setAuthor(authorTextField.getText())
                .setTitle(titleTextField.getText())
                .setKeyWords(keywordsTextField.getText())
                .setEdition(editionTextField.getText())
                .setTome(Integer.parseInt(tomeTextField.getText()))
                .createBook();
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

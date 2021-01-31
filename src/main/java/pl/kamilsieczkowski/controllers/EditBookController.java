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
import pl.kamilsieczkowski.transporters.BookTransporter;
import pl.kamilsieczkowski.utils.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_LIBRARY_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class EditBookController implements Initializable {
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
    private Book bookToEdit;
    public static final Logger LOG = LogManager.getLogger(EditBookController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getLabelsToEdit(getBookToEdit());
        setWindowText();
        endButton.setOnAction(event -> clickOnEndButton(new Window()));
        saveButton.setOnAction(event -> clickOnSaveButton(new Window()));
    }

    private Book getBookToEdit() {
        bookToEdit = new Book.BookBuilder().createBook();
        if (BookTransporter.isIsBookAvailable()) {
            bookToEdit = BookTransporter.getBook();
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                LOG.error("Can't get book to edition", e);
            }
        }
        return bookToEdit;
    }

    private void getLabelsToEdit(Book bookToEdit) {
        id_numberTextField.setText(Integer.toString(bookToEdit.getId_book()));
        authorTextField.setText(bookToEdit.getAuthor());
        titleTextField.setText(bookToEdit.getTitle());
        keywordsTextField.setText(bookToEdit.getKeyWords());
        editionTextField.setText(bookToEdit.getEdition());
        tomeTextField.setText(Integer.toString(bookToEdit.getTome()));
    }

    private void clickOnEndButton(Window window) {
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
    }

    private void clickOnSaveButton(Window window) {
        editBook(window, new BookRepository(new Connector()));
    }

    private void editBook(Window window, BookRepository bookRepository) {
        bookRepository.editBook(new Book.BookBuilder()
                        .setId_book(Integer.parseInt(id_numberTextField.getText()))
                        .setAuthor(authorTextField.getText())
                        .setTitle(titleTextField.getText())
                        .setKeyWords(keywordsTextField.getText())
                        .setEdition(editionTextField.getText())
                        .setTome(Integer.parseInt(tomeTextField.getText()))
                        .createBook(),
                bookToEdit.getId_book());
        window.changeWindow(pane, SOURCE_LIBRARY_WINDOW);
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

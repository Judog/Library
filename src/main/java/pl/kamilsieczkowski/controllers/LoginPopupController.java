package pl.kamilsieczkowski.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.kamilsieczkowski.DTO.Book;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.utils.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_ADD_NEW_BOOK_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class LoginPopupController implements Initializable {
    @FXML
    private Tab publicationsTab;
    @FXML
    private Label id_numberLabel;
    @FXML
    private Label signatureLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label keyWordsLabel;
    @FXML
    private Button searchButton;
    @FXML
    private TableColumn<Book, Integer> idNumberColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, Integer> tomeColumn;
    @FXML
    private TableColumn<Book, String> editionColumn;
    @FXML
    private TableColumn<Book, String> localizationColumn;
    @FXML
    private TableColumn<Book, String> keyWordsColumn;
    @FXML
    private Button borrowButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button prolongButton;
    @FXML
    private Button editDescriptionButton;
    @FXML
    private Button addNewButton;
    @FXML
    private Label foundLabel;
    @FXML
    private Label avabilityLabel;
    @FXML
    private TableView<Book> table;
    @FXML
    private Pane popupPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Window window = new Window();
        setWindowText();
        BookRepository bookRepository = new BookRepository();
        addNewButton.setOnAction(event -> {
            try {
                window.openLibraryWindow(SOURCE_ADD_NEW_BOOK_WINDOW, ADD_NEW_BOOK);
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.getWindow(popupPane).close();
        });
        try {
            ViewTable(bookRepository);
            displayHowManyBooksFound(bookRepository);

        } catch (Throwable exception) {
            exception.printStackTrace();
        }
    }

    void displayHowManyBooksFound(BookRepository bookRepository) throws Throwable {
        foundLabel.setText(FOUND + SPACE + bookRepository.downloadBookList().size());
    }

    void ViewTable(BookRepository bookRepository) throws Throwable {
        table.setItems(bookRepository.downloadBookList());
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<>("id_book"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        keyWordsColumn.setCellValueFactory(new PropertyValueFactory<>("keyWords"));
        tomeColumn.setCellValueFactory(new PropertyValueFactory<>("tome"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        localizationColumn.setCellValueFactory(new PropertyValueFactory<>("localization"));
    }

    private void setWindowText() {
        publicationsTab.setText(PUBLICATIONS);
        id_numberLabel.setText(ID_NUMBER);
        idNumberColumn.setText(ID_NUMBER);
        signatureLabel.setText(SIGNATURE);
        keyWordsColumn.setText(KEY_WORDS);
        authorLabel.setText(AUTHOR);
        authorColumn.setText(AUTHOR);
        titleLabel.setText(TITLE);
        titleColumn.setText(TITLE);
        keyWordsLabel.setText(KEY_WORDS);
        searchButton.setText(SEARCH);
        tomeColumn.setText(TOME);
        editionColumn.setText(EDITION);
        localizationColumn.setText(LOCALIZATION);
        borrowButton.setText(BORROW);
        returnButton.setText(RETURN);
        prolongButton.setText(PROLONG);
        editDescriptionButton.setText(EDIT_DESCRIPTION);
        addNewButton.setText(ADD_NEW);
        foundLabel.setText(FOUND);
        avabilityLabel.setText(AVABILITY);
    }
}



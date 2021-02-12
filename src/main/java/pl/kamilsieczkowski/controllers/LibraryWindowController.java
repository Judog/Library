package pl.kamilsieczkowski.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kamilsieczkowski.model.Book;
import pl.kamilsieczkowski.constants.Texts;
import pl.kamilsieczkowski.database.BookRepository;
import pl.kamilsieczkowski.database.Connector;
import pl.kamilsieczkowski.DTO.BookDTO;
import pl.kamilsieczkowski.utils.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static pl.kamilsieczkowski.constants.Constants.SOURCE_ADD_NEW_BOOK_WINDOW;
import static pl.kamilsieczkowski.constants.Constants.SOURCE_EDIT_BOOK_WINDOW;
import static pl.kamilsieczkowski.constants.Texts.*;

public class LibraryWindowController implements Initializable {
    @FXML
    private Tab publicationsTab;
    @FXML
    private Label id_numberLabel;
    @FXML
    private Label placementLabel;
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
    private Label availabilityLabel;
    @FXML
    private TableView<Book> table;
    @FXML
    private Pane pane;
    @FXML
    private TextField id_numberTextField;
    @FXML
    private SplitMenuButton placementMenuButton;
    @FXML
    private MenuItem library;
    @FXML
    private MenuItem borrowed;
    @FXML
    private MenuItem all;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField keyWordsTextField;
    private final Window window;
    private final BookRepository bookRepository;
    public static final Logger LOG = LogManager.getLogger(LibraryWindowController.class);

    public LibraryWindowController() {
        this.window = new Window();
        this.bookRepository = new BookRepository(new Connector());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //displayed things
        setWindowText();
        ObservableList<Book> listOfBooks = FXCollections.observableArrayList(bookRepository.getAllBooks());
        displayHowManyBooksFound(listOfBooks);
        displayAvailableBooksFound(listOfBooks);
        viewTable(listOfBooks);
        //buttons
        searchButton.setOnAction(event -> searchBooks());
        addNewButton.setOnAction(event -> window.changeWindow(pane, SOURCE_ADD_NEW_BOOK_WINDOW));
        editDescriptionButton.setOnAction(event -> editBook());
        //Split Menu Button (placement Menu Button) functionality.
        library.setOnAction(event -> placementMenuButton.setText(IN_LIBRARY));
        borrowed.setOnAction(event -> placementMenuButton.setText(BORROWED));
        all.setOnAction(event -> placementMenuButton.setText(ALL));
    }

    private void editBook() {
        Book bookToEdit = table.getSelectionModel().getSelectedItem();
        BookDTO.setBook(bookToEdit);
        window.changeWindow(pane, SOURCE_EDIT_BOOK_WINDOW);
    }

    private void searchBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            //book list
            bookList = bookRepository.findBooksBy
                    (new Book.BookBuilder()
                            .setId_book(Integer.parseInt(id_numberTextField.getText()))
                            .setAuthor(authorTextField.getText())
                            .setTitle(titleTextField.getText())
                            .setKeyWords(keyWordsTextField.getText())
                            .setLocalization(placementMenuButton.getText())
                            .createBook());
        } catch (SQLException sqlException) {
            LOG.error("Can't get book list, ");
        }
        ObservableList<Book> searchedBookList = FXCollections.observableArrayList(bookList);
        //viewers
        viewTable(searchedBookList);
        displayHowManyBooksFound(searchedBookList);
        displayAvailableBooksFound(searchedBookList);
    }

    void displayHowManyBooksFound(ObservableList<Book> observableList) {
        foundLabel.setText(FOUND + SPACE + observableList.size());
    }

    void displayAvailableBooksFound(ObservableList<Book> observableList) {
        int numberOfBooksInLibrary = 0;
        Book bookInLibrary = new Book.BookBuilder().setLocalization(IN_LIBRARY).createBook();
        for (Book book : observableList) {
            numberOfBooksInLibrary = getPlusOneWhenBookIsInLibrary(numberOfBooksInLibrary, bookInLibrary, book);
        }
        availabilityLabel.setText(AVAILABILITY + SPACE + numberOfBooksInLibrary);
    }

    private int getPlusOneWhenBookIsInLibrary(int numberOfBooksInLibrary, Book bookInLibrary, Book book) {
        if (book.equals(bookInLibrary)) {
            numberOfBooksInLibrary = numberOfBooksInLibrary + 1;
        }
        return numberOfBooksInLibrary;
    }

    void viewTable(ObservableList<Book> observableList) {
        table.setItems(observableList);
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
        placementLabel.setText(Texts.LOCALIZATION);
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
        availabilityLabel.setText(AVAILABILITY);
        placementMenuButton.setText(ALL);
        library.setText(IN_LIBRARY);
        borrowed.setText(BORROWED);
        all.setText(ALL);
    }
}



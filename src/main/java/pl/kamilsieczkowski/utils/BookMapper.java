package pl.kamilsieczkowski.utils;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.kamilsieczkowski.model.Book;

public abstract class BookMapper {
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

    protected Book mapBook() {
        return new Book.BookBuilder()
                .setId_book(Integer.parseInt(idNumberTextField.getText()))
                .setAuthor(authorTextField.getText())
                .setTitle(titleTextField.getText())
                .setKeyWords(keywordsTextField.getText())
                .setEdition(editionTextField.getText())
                .setTome(Integer.parseInt(tomeTextField.getText()))
                .createBook();
    }
}

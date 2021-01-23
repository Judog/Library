package pl.kamilsieczkowski.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kamilsieczkowski.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pl.kamilsieczkowski.constants.Constants.*;

public class SearchRepository {
    private final String id_number;
    private final String placement;
    private final String author;
    private final String title;
    private final String keyWords;
    private final String QUERY = "SELECT * FROM library_users.books WHERE ";

    private SearchRepository(String id_number, String placement, String author, String title, String keyWords) {
        this.id_number = id_number;
        this.placement = placement;
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
    }

    public ObservableList<Book> getSearchedBooks(Connector connector) throws SQLException {
        String enteredQuery = QUERY +
                COLUMN_ID_BOOK + " LIKE '%" + this.id_number + "%' AND " +
                COLUMN_AUTHOR + " LIKE '%" + this.author + "%' AND " +
                COLUMN_TITLE + " LIKE '%" + this.title + "%' AND " +
                COLUMN_KEY_WORDS + " LIKE '%" + this.keyWords + "%' AND " +
                COLUMN_LOCALIZATION + " LIKE '%" + getPlacement(this.placement) + "%';";
        ResultSet resultSet = connector.downloadFromDatabase(enteredQuery);
        ObservableList<Book> searchedBooks = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Book book = new Book.BookBuilder()
                    .setId_book(resultSet.getInt(COLUMN_ID_BOOK))
                    .setLocalization(resultSet.getString(COLUMN_LOCALIZATION))
                    .setAuthor(resultSet.getString(COLUMN_AUTHOR))
                    .setTitle(resultSet.getString(COLUMN_TITLE))
                    .setEdition(resultSet.getString(COLUMN_EDITION))
                    .setTome(resultSet.getInt(COLUMN_TOME))
                    .setKeyWords(resultSet.getString(COLUMN_KEY_WORDS))
                    .createBook();
            searchedBooks.add(book);
        }
        return searchedBooks;
    }

    private String getPlacement(String placement) {
        if (placement.equals("All")) {
            placement = "";
            // for searching in query, all meaning in library and borrowed -
            // must be empty for searching result with library and borrowed placement
        }
        return placement;
    }

    public static class SearchRepositoryBuilder {
        private String id_number;
        private String placement;
        private String author;
        private String title;
        private String keyWords;

        public SearchRepositoryBuilder setId_number(String id_number) {
            this.id_number = id_number;
            return this;
        }

        public SearchRepositoryBuilder setPlacement(String placement) {
            this.placement = placement;
            return this;
        }

        public SearchRepositoryBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public SearchRepositoryBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public SearchRepositoryBuilder setKeyWords(String keyWords) {
            this.keyWords = keyWords;
            return this;
        }

        public SearchRepository createSearchRepository() {
            return new SearchRepository(id_number, placement, author, title, keyWords);
        }
    }
}

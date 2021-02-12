package pl.kamilsieczkowski.DTO;

import pl.kamilsieczkowski.model.Book;

public class BookDTO {
    private static Book book;
    private static boolean isBookAvailable;

    public static Book getBook() {
        return book;
    }

    public static boolean isIsBookAvailable() {
        return isBookAvailable;
    }

    public static void setBook(Book book) {
        isBookAvailable = true;
        BookDTO.book = book;
    }
}

package pl.kamilsieczkowski.transporters;

import pl.kamilsieczkowski.model.Book;

public class BookTransporter {
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
        BookTransporter.book = book;
    }
}

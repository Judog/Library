package pl.kamilsieczkowski.DTO;

public class Book {
    private int id_book;
    private String author;
    private String title;
    private String keyWords;
    private int tome;
    private String edition;
    private String localization;

    public int getId_book() {
        return id_book;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public int getTome() {
        return tome;
    }

    public String getEdition() {
        return edition;
    }

    public String getLocalization() {
        return localization;
    }

    public Book(int id_book, String author, String title, String keyWords, int tome, String edition, String localization) {
        this.id_book = id_book;
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
        this.tome = tome;
        this.edition = edition;
        this.localization = localization;
    }
}

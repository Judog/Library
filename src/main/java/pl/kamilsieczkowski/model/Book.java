package pl.kamilsieczkowski.model;

public class Book {
    private final int id_book;
    private final String author;
    private final String title;
    private final String keyWords;
    private final int tome;
    private final String edition;
    private final String localization;

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

    private Book(int id_book, String author, String title, String keyWords, int tome, String edition, String localization) {
        this.id_book = id_book;
        this.author = author;
        this.title = title;
        this.keyWords = keyWords;
        this.tome = tome;
        this.edition = edition;
        this.localization = localization;
    }

    public static class BookBuilder {
        private int id_book;
        private String author;
        private String title;
        private String keyWords;
        private int tome;
        private String edition;
        private String localization;

        public BookBuilder setId_book(int id_book) {
            this.id_book = id_book;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder setKeyWords(String keyWords) {
            this.keyWords = keyWords;
            return this;
        }

        public BookBuilder setTome(int tome) {
            this.tome = tome;
            return this;
        }

        public BookBuilder setEdition(String edition) {
            this.edition = edition;
            return this;
        }

        public BookBuilder setLocalization(String localization) {
            this.localization = localization;
            return this;
        }

        public Book createBook() {
            return new Book(id_book, author, title, keyWords, tome, edition, localization);
        }
    }
}

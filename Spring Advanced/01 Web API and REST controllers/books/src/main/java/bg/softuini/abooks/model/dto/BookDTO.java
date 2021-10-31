package bg.softuini.abooks.model.dto;

public class BookDTO {
    private AuthorDTO author;
    private Long Id;
    private String title;
    private String isbn;

    public BookDTO() {
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public BookDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }

    public Long getId() {
        return Id;
    }

    public BookDTO setId(Long id) {
        Id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }
}

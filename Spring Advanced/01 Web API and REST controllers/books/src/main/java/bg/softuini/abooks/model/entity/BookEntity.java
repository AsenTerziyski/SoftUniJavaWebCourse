package bg.softuini.abooks.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {
    @Column(unique = true)
    private String title;
    private String isbn;

    // all и персист не дават да е персистне ентити, трябва да се сложи @трансакшънъл!!!!!
//    @ManyToOne(cascade = CascadeType.ALL)
//    @ManyToOne(cascade = CascadeType.PERSIST)
    //merge raboti
//    @ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne()
    private AuthorEntity author;

    public BookEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                '}';
    }
}

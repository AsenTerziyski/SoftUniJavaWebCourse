package bg.softuini.abooks.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="authors")
public class AuthorEntity extends BaseEntity{

  private String name;

  @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
  private List<BookEntity> books = new LinkedList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<BookEntity> getBooks() {
    return books;
  }

  public void setBooks(List<BookEntity> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "AuthorEntity{" +
        "name='" + name + '\'' +
        '}';
  }
}

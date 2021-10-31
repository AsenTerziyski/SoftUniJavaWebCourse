package bg.softuini.abooks.service;

import bg.softuini.abooks.model.dto.BookDTO;
import bg.softuini.abooks.model.entity.BookEntity;

import java.util.List;

public interface BooksService {
    List<BookDTO> getAllBooks();

    void saveBook(BookEntity book);

    void saveAllBooksInDB(List<BookEntity> allBooks);
    BookEntity findBookByTitle (String title);

    BookDTO findBookById(Long id);

    void deleteBookById(Long id);

    long createBook(BookDTO bookDTO);


    Long updateBookById(Long bookId, BookDTO bookDTO);
}

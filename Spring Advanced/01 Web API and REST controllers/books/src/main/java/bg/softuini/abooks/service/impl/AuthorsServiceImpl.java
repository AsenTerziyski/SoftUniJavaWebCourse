package bg.softuini.abooks.service.impl;

import bg.softuini.abooks.model.entity.AuthorEntity;
import bg.softuini.abooks.model.entity.BookEntity;
import bg.softuini.abooks.repository.AuthorRepository;
import bg.softuini.abooks.service.AuthorsService;
import bg.softuini.abooks.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorsServiceImpl implements AuthorsService {
    private final AuthorRepository authorRepository;
    private final BooksService booksService;

    public AuthorsServiceImpl(AuthorRepository authorRepository, BooksService booksService) {
        this.authorRepository = authorRepository;
        this.booksService = booksService;
    }

    @Override
    public void save(AuthorEntity author) {
        this.authorRepository.save(author);
    }


    //    @Transactional
    @Override
    public boolean saveAuthorInDB(AuthorEntity author) {
        if (author != null) {
            this.authorRepository.save(author);
            return true;
        }
        return false;

    }

    @Override
    public void initialiseAuthor(String authorName, String[] books) {
        AuthorEntity author = new AuthorEntity();
        List<BookEntity> authorsBooks = new LinkedList<>();
        AuthorEntity byName = this.authorRepository.findByName(authorName).orElse(null);
        if (byName == null) {
            author.setName(authorName);
            for (String aBookTitle : books) {
                BookEntity aBook = new BookEntity();
                if (this.booksService.findBookByTitle(aBookTitle) == null) {
                    aBook.setTitle(aBookTitle);
                    aBook.setIsbn(UUID.randomUUID().toString());
                    aBook.setAuthor(author);
                    authorsBooks.add(aBook);
                }
            }
            author.setBooks(authorsBooks);
            this.save(author);
            this.booksService.saveAllBooksInDB(authorsBooks);
        }



    }
}

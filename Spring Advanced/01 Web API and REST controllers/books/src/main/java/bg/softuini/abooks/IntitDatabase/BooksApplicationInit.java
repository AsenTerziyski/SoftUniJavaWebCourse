package bg.softuini.abooks.IntitDatabase;

import bg.softuini.abooks.model.entity.AuthorEntity;
import bg.softuini.abooks.model.entity.BookEntity;
import bg.softuini.abooks.repository.AuthorRepository;
import bg.softuini.abooks.repository.BookRepository;
import bg.softuini.abooks.service.AuthorsService;
import bg.softuini.abooks.service.BooksService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BooksApplicationInit implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorsService authorsService;
    private final BooksService booksService;

    public BooksApplicationInit(AuthorRepository authorRepository, BookRepository bookRepository, AuthorsService authorsService, BooksService booksService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorsService = authorsService;
        this.booksService = booksService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0 && authorRepository.count() == 0) {
            initNikolaiHaitov();
            initDimitarTalev();
            initTestAuthor();
            initTestAuthor2();
            initTestAuthor3();
        }
    }

    private void initTestAuthor() {
        String[] books = new String[]{"TestBook1", "TestBook2"};
        this.authorsService.initialiseAuthor("Test Author", books);
    }

    private void initTestAuthor2() {
        String[] books = new String[]{"TestBook3", "TestBook4"};
        this.authorsService.initialiseAuthor("Test Author 2", books);
    }

    private void initTestAuthor3() {
        String[] books = new String[]{"TestBook5", "TestBook6"};
        this.authorsService.initialiseAuthor("Test Author 3", books);

    }

    private void initNikolaiHaitov() {
        String[] books = new String[]{"Диви Разкази"};
        this.authorsService.initialiseAuthor("Николай Хайтов", books);
    }

    private void initDimitarTalev() {
        String[] books = new String[]{"Тютюн"};
        this.authorsService.initialiseAuthor("Димитър Димов", books);
    }

}

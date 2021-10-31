package bg.softuini.abooks.service.impl;

import bg.softuini.abooks.model.dto.AuthorDTO;
import bg.softuini.abooks.model.dto.BookDTO;
import bg.softuini.abooks.model.entity.AuthorEntity;
import bg.softuini.abooks.model.entity.BookEntity;
import bg.softuini.abooks.repository.AuthorRepository;
import bg.softuini.abooks.repository.BookRepository;
import bg.softuini.abooks.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BooksServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long createBook(BookDTO bookDTO) {
        BookDTO bookDTO1 = bookDTO;
//        AuthorEntity authorEntity = this.authorRepository.findByName(bookDTO.getAuthor().getName())
//                .orElseGet(() -> {
//                    AuthorEntity author = new AuthorEntity();
//                    author.setName(bookDTO1.getAuthor().getName());
//                    return author;
//                });
        AuthorEntity authorEntity = this.authorRepository.findByName(bookDTO.getAuthor().getName()).orElse(null);

        if (authorEntity == null) {
            AuthorEntity author = new AuthorEntity();
            author.setName(bookDTO.getAuthor().getName());
            this.authorRepository.save(author);
            BookEntity newBook = new BookEntity();
            newBook.setAuthor(author);
            newBook.setIsbn(bookDTO.getIsbn());
            newBook.setTitle(bookDTO.getTitle());
            Long id = this.bookRepository.save(newBook).getId();
            return id;
        }

        BookEntity newBook = new BookEntity();
        newBook.setAuthor(authorEntity);
        newBook.setIsbn(bookDTO.getIsbn());
        newBook.setTitle(bookDTO.getTitle());
        Long id = this.bookRepository.save(newBook).getId();
        return id;
    }

    @Override
    public Long updateBookById(Long bookId, BookDTO bookDTO) {
        BookEntity bookEntity = this.bookRepository.findById(bookId).orElse(null);
        if (bookEntity !=null) {
            bookEntity.setTitle(bookDTO.getTitle());
            String name = bookDTO.getAuthor().getName();
            AuthorEntity author = this.authorRepository.findByName(name).orElse(null);
            if (author == null) {
                AuthorEntity newAuthor = new AuthorEntity();
                newAuthor.setName(name);
                AuthorEntity savedNewAuthor = this.authorRepository.save(newAuthor);
                bookEntity.setAuthor(savedNewAuthor);
                bookEntity.setIsbn(bookDTO.getIsbn());
                BookEntity savedBook = this.bookRepository.save(bookEntity);
                return savedBook.getId();
            }
            bookEntity.setAuthor(author);
            bookEntity.setIsbn(bookDTO.getIsbn());
            BookEntity savedBook = this.bookRepository.save(bookEntity);
            return savedBook.getId();
        }
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        BookEntity bookEntity = this.bookRepository.findById(id).orElse(null);
        if (bookEntity !=null) {
            this.bookRepository.delete(bookEntity);
        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookEntity> allBooks = this.bookRepository.findAll();
        List<BookDTO> allBooksDto = new LinkedList<>();
        for (BookEntity aBook : allBooks) {
            BookDTO bookDTO = this.modelMapper.map(aBook, BookDTO.class);
            AuthorDTO authorDTO = this.modelMapper.map(aBook.getAuthor(), AuthorDTO.class);
            bookDTO.setAuthor(authorDTO);
            allBooksDto.add(bookDTO);
        }

        return allBooksDto;
    }

    @Override
    public void saveBook(BookEntity book) {
        BookEntity bookEntity = this.bookRepository.findByTitle(book.getTitle()).orElse(null);
        if (bookEntity != null) {
            this.bookRepository.save(book);
        }

    }

    //    @Transactional
    @Override
    public void saveAllBooksInDB(List<BookEntity> allBooks) {
        this.bookRepository.saveAll(allBooks);
    }

    @Override
    public BookEntity findBookByTitle(String title) {
        BookEntity bookEntity = this.bookRepository.findByTitle(title).orElse(null);
        return bookEntity;
    }

    @Override
    public BookDTO findBookById(Long id) {
        BookEntity bookById = this.bookRepository.findById(id).orElse(null);
        if (bookById != null) {
            BookDTO bookDtoById = this.modelMapper.map(bookById, BookDTO.class);
            return bookDtoById;
        }
        return null;
    }


}

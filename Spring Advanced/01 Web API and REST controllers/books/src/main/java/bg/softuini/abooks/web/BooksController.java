package bg.softuini.abooks.web;

import bg.softuini.abooks.model.dto.BookDTO;
import bg.softuini.abooks.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.booksService.getAllBooks();
        ResponseEntity<List<BookDTO>> ok = ResponseEntity.ok(allBooks);
        return ok;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookById = this.booksService.findBookById(id);
        if (bookById != null) {
            //Ще върне статус 404
            return ResponseEntity.ok(bookById);
        }
        //Ще върне статус 200
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        this.booksService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(
            @PathVariable Long id,
            @RequestBody BookDTO bookDTO,
            UriComponentsBuilder builder) {

        System.out.println();
        Long updatedId = this.booksService.updateBookById(id, bookDTO);
        if (updatedId != null) {
            URI locationUri = builder
                    .path("/books/{id}")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity
                    .created(locationUri).build();
        }
        //todo
        throw new UnsupportedOperationException("Still not implemented");
    }

    @PostMapping()
    public ResponseEntity<BookDTO> create(
            @RequestBody BookDTO bookDTO,
            UriComponentsBuilder builder) {

        long bookId = this.booksService.createBook(bookDTO);

        URI locationUri = builder
                .path("/books/{id}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity
                .created(locationUri).build();
    }
}

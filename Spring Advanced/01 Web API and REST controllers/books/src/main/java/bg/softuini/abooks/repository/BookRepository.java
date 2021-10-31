package bg.softuini.abooks.repository;

import bg.softuini.abooks.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAll();
    Optional<BookEntity> findByTitle(String title);
}

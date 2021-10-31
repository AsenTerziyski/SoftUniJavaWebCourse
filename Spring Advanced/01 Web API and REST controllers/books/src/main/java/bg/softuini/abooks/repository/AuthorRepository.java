package bg.softuini.abooks.repository;

import bg.softuini.abooks.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
//    AuthorEntity findByName(String authorName);
    Optional<AuthorEntity> findByName(String name);
}

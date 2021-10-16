package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByUsernameIgnoreCase(String username);
}

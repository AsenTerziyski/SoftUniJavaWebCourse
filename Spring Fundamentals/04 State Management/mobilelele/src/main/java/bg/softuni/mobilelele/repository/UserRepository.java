package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

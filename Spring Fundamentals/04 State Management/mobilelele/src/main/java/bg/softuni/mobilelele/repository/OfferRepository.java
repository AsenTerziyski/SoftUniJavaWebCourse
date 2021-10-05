package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository  extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAllBySeller(UserEntity userEntity);
}

package bg.softuni.mobilelele.service.interfaces;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.view.OfferSummeryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();
    List<OfferSummeryView> getAllOffers();
//    List<OfferEntity> findUserOffers(UserEntity userEntity);
}

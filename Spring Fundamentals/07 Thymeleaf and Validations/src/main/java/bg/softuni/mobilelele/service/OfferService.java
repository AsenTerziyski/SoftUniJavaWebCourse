package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.OfferAddServiceModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import java.util.List;

public interface OfferService {
  void initializeOffers();

  List<OfferSummaryView> getAllOffers();

  OfferDetailsView findById(Long id);

  void deleteOffer(Long id);

  void updateOffer(OfferUpdateServiceModel offerModel);

    boolean addNewOffer(OfferAddServiceModel mappedOffer);
}

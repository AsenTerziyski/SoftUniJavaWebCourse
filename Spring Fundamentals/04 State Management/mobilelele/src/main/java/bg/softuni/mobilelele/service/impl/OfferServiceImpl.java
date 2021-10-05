package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.EnumEngine;
import bg.softuni.mobilelele.model.entity.enums.EnumTransmission;
import bg.softuni.mobilelele.model.view.OfferSummeryView;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.service.interfaces.OfferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void initializeOffers() {
            //todo

    }

    @Override
    public List<OfferSummeryView> getAllOffers() {
        //TODO
        List<OfferSummeryView> collect = offerRepository
                .findAll()
                .stream()
                .map(this::mapOffersToView)
                .collect(Collectors.toList());
        return collect;
    }

//    @Override
//    public List<OfferEntity> findUserOffers(UserEntity userEntity) {
//
//        return null;
//    }

    private OfferSummeryView mapOffersToView(OfferEntity offerEntity) {
        //TODO
        String description = offerEntity.getDescription();
        EnumEngine engine = offerEntity.getEngine();
        String imageUrl = offerEntity.getImageUrl();
        Integer mileage = offerEntity.getMileage();
        BigDecimal price = offerEntity.getPrice();
        EnumTransmission transmission = offerEntity.getTransmission();
        Integer year = offerEntity.getYear();
        String model = offerEntity.getModel().getName();
        String brand = offerEntity.getModel().getBrand().getName();

        OfferSummeryView offerSummeryView = new OfferSummeryView();
        offerSummeryView.setDescription(description);
        offerSummeryView.setEngine(engine);
        offerSummeryView.setImageUrl(imageUrl);
        offerSummeryView.setMileage(mileage);
        offerSummeryView.setPrice(price);
        offerSummeryView.setTransmission(transmission);
        offerSummeryView.setYear(year);
        offerSummeryView.setModel(model);
        offerSummeryView.setBrand(brand);

        return offerSummeryView;
    }
}

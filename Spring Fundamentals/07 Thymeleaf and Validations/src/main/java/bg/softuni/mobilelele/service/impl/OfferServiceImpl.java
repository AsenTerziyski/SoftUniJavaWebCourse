package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.CarModelsEnum;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.service.OfferAddServiceModel;
import bg.softuni.mobilelele.model.service.OfferUpdateServiceModel;
import bg.softuni.mobilelele.model.view.OfferDetailsView;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.OfferService;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.web.exception.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, ModelRepository modelRepository, UserRepository userRepository, BrandRepository brandRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    public void initializeOffers() {

        if (offerRepository.count() == 0) {
            OfferEntity offer1 = new OfferEntity();
            offer1
                .setModel(modelRepository.findById(1L).orElse(null))
                .setEngine(EngineEnum.GASOLINE)
                .setTransmission(TransmissionEnum.MANUAL)
                .setMileage(22500)
                .setPrice(14300)
                .setYear(2019)
                .setDescription("Used, but well services and in good condition.")
                .setSeller(userRepository.findByUsername("pesho")
                    .orElse(null)) // or currentUser.getUserName()
                .setImageUrl(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcXp1KBpDKgYs6VqndkBpX8twjPOZbHV86yg&usqp=CAU");

            OfferEntity offer2 = new OfferEntity();
            offer2
                .setModel(modelRepository.findById(1L).orElse(null))
                .setEngine(EngineEnum.DIESEL)
                .setTransmission(TransmissionEnum.AUTOMATIC)
                .setMileage(209000)
                .setPrice(5500)
                .setYear(2000)
                .setDescription("After full maintenance, insurance, new tires...")
                .setSeller(userRepository.findByUsername("admin")
                    .orElse(null)) // or currentUser.getUserName()
                .setImageUrl(
                    "https://www.picclickimg.com/d/l400/pict/283362908243_/FORD-ESCORT-MK5-16L-DOHC-16v-ZETEC.jpg");

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return offerRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());
    }
    @Override
    public OfferDetailsView findById(Long id) {
        OfferDetailsView offerDetailsView = this.offerRepository.findById(id).map(this::mapDetailsView).get();
        return offerDetailsView;
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel offerModel) {

        OfferEntity offerEntity =
            offerRepository.findById(offerModel.getId()).orElseThrow(() ->
            new ObjectNotFoundException("Offer with id " + offerModel.getId() + " not found!"));


        offerEntity.setPrice(offerModel.getPrice())
            .setDescription(offerModel.getDescription())
            .setEngine(offerModel.getEngine())
            .setImageUrl(offerModel.getImageUrl())
            .setMileage(offerModel.getMileage())
            .setTransmission(offerModel.getTransmission())
            .setYear(offerModel.getYear());

        offerRepository.save(offerEntity);
    }

    @Override
    public boolean addNewOffer(OfferAddServiceModel mappedOffer) {

        String carBrand = mappedOffer.getBrand().name();
        String modelName = mappedOffer.getModel().name();
        OfferEntity newOffer = this.modelMapper.map(mappedOffer, OfferEntity.class);
        UserEntity seller = this.userService.getCurrentlyLoggedUser();
        System.out.println();
        if (seller !=null) {
            newOffer.setSeller(seller);
        }

        BrandEntity brand = this.brandRepository.findByName(carBrand).orElse(null);
        if(brand != null) {
            ModelEntity model = new ModelEntity();
            model.setName(modelName);
            model.setCategory(mappedOffer.getCategory());
            model.setBrand(brand);
            model.setImageUrl(mappedOffer.getImageUrl());
            model.setStartYear(mappedOffer.getYear());
            ModelEntity save = this.modelRepository.save(model);
            newOffer.setModel(save);

            this.offerRepository.save(newOffer);
        }

        return false;
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        OfferSummaryView summaryView = this.modelMapper
                .map(offerEntity, OfferSummaryView.class);

        summaryView.setModel(offerEntity.getModel().getName());
        summaryView.setBrand(offerEntity.getModel().getBrand().getName());

        return summaryView;
    }

    private OfferDetailsView mapDetailsView(OfferEntity offer) {
        OfferDetailsView offerDetailsView = this.modelMapper.map(offer, OfferDetailsView.class);
        offerDetailsView.setModel(offer.getModel().getName());
        offerDetailsView.setBrand(offer.getModel().getBrand().getName());
        offerDetailsView.setSellerFullName(offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName());
        return offerDetailsView;
    }
}

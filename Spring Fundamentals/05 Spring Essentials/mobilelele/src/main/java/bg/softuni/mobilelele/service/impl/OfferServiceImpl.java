package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.OfferEntity;
import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import bg.softuni.mobilelele.model.view.OfferSummaryView;
import bg.softuni.mobilelele.repository.ModelRepository;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.OfferService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ModelRepository modelRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
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
                map(offerEntity -> map(offerEntity)).collect(Collectors.toList());
    }

    private OfferSummaryView map(OfferEntity offerEntity) {
        OfferSummaryView summaryView = this.modelMapper
                .map(offerEntity, OfferSummaryView.class);

        summaryView.setModel(offerEntity.getModel().getName());

        return summaryView;
    }
}

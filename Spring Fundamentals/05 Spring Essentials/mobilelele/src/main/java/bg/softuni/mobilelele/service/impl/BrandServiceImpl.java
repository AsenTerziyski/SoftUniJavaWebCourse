package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.repository.BrandRepository;
import bg.softuni.mobilelele.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeBrand() {
        if (brandRepository.count() == 0) {
            BrandEntity ford = new BrandEntity();
            ford.setName("Ford");
            brandRepository.save(ford);
        }
    }

}

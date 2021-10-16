package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.enums.CarBrandsEnum;
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

        CarBrandsEnum[] values = CarBrandsEnum.values();
        for (CarBrandsEnum value : values) {
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setName(value.toString());
            if (this.brandRepository.findBrandEntityByNameIgnoreCase(value.toString()).isEmpty()) {
                brandRepository.save(brandEntity);
            }

        }
    }

}

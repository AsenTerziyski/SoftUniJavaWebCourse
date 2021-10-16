package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.entity.ModelEntity;

public interface ModelService {

    void initializeModels();

    ModelEntity findById(Long id);
}

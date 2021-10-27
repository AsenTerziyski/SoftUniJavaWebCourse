package com.example.examsf.service.impl;

import com.example.examsf.model.entity.CategoryEntity;
import com.example.examsf.model.entity.ShipEntity;
import com.example.examsf.model.entity.UserEntity;
import com.example.examsf.model.entity.enums.CategoryEnum;
import com.example.examsf.model.service.ShipServiceModel;
import com.example.examsf.repository.ShipRepository;
import com.example.examsf.service.CategoryService;
import com.example.examsf.service.ShipService;
import com.example.examsf.service.UserService;
import com.example.examsf.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public ShipServiceImpl(ShipRepository shipRepository, CategoryService categoryService, UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.shipRepository = shipRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean saveShipInDatabase(ShipServiceModel shipServiceModel) {

        CategoryEnum category = shipServiceModel.getCategory();
        CategoryEntity categoryByCategoryEnum = this.categoryService.findCategoryByCategoryEnum(category);
        ShipEntity shipEntity = this.modelMapper.map(shipServiceModel, ShipEntity.class);
        shipEntity.setCategory(categoryByCategoryEnum);
        if (this.currentUser.getId() != null) {
            UserEntity userById = this.userService.findUserById(this.currentUser.getId());
            shipEntity.setUser(userById);
            this.shipRepository.save(shipEntity);
            return true;
        }
        return false;
    }
}

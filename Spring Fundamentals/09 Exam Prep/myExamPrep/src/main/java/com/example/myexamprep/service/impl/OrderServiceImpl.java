package com.example.myexamprep.service.impl;

import com.example.myexamprep.model.entities.CategoryEntity;
import com.example.myexamprep.model.entities.OrderEntity;
import com.example.myexamprep.model.entities.UserEntity;
import com.example.myexamprep.model.entities.enums.CategoryEnum;
import com.example.myexamprep.model.serviceModel.OrderAddServiceModel;
import com.example.myexamprep.model.view.OrderViewModel;
import com.example.myexamprep.repository.OrderRepository;
import com.example.myexamprep.service.CategoryService;
import com.example.myexamprep.service.OrderService;
import com.example.myexamprep.service.UserService;
import com.example.myexamprep.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;

    public OrderServiceImpl(OrderRepository orderRepository, CategoryService categoryService, ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @Override
    public boolean addOrderInDataBase(OrderAddServiceModel orderAddServiceModel) {

        OrderEntity orderEntity = this.modelMapper.map(orderAddServiceModel, OrderEntity.class);
        CategoryEnum category = orderAddServiceModel.getCategory();
        CategoryEntity categoryByCategoryEnum = this.categoryService.findCategoryByCategoryEnum(category);
        System.out.println();
        if(categoryByCategoryEnum !=null) {
            orderEntity.setCategory(categoryByCategoryEnum);
        }

        Long id = this.currentUser.getId();
        UserEntity userById = this.userService.findUserById(id);
        if (userById != null) {
            orderEntity.setEmployee(userById);
            this.orderRepository.save(orderEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderViewModel> findAllOrdersByPriceDesc() {
        List<OrderEntity> allOrderByPriceDesc = this.orderRepository
                .findAllOrderByPriceDesc();
       return allOrderByPriceDesc
               .stream()
               .map(ordersEntity -> this.modelMapper.map(ordersEntity, OrderViewModel.class))
               .collect(Collectors.toList());
    }

    @Override
    public Integer getAllNeededTime() {
        List<OrderEntity> all = this.orderRepository.findAll();
        Integer totalOrdersTime = 0;
        for (OrderEntity ordersEntity : all) {
            Integer neededTime = ordersEntity.getCategory().getNeededTime();
            totalOrdersTime +=neededTime;
        }
        return totalOrdersTime;
    }

    @Override
    public boolean deleteOrder(Long id) {
        if (this.orderRepository.findById(id).isPresent()) {
            this.orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

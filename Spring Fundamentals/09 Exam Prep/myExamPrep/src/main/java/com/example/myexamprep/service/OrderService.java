package com.example.myexamprep.service;

import com.example.myexamprep.model.serviceModel.OrderAddServiceModel;
import com.example.myexamprep.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    boolean addOrderInDataBase(OrderAddServiceModel orderAddServiceModel);

    List<OrderViewModel> findAllOrdersByPriceDesc();

    Integer getAllNeededTime();

    boolean deleteOrder(Long id);
}

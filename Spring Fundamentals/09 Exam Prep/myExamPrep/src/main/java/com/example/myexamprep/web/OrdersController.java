package com.example.myexamprep.web;

import com.example.myexamprep.model.binding.OrderAddBindingModel;
import com.example.myexamprep.model.serviceModel.OrderAddServiceModel;
import com.example.myexamprep.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrdersController(OrderService orderService, ModelMapper modelMapper) {

        this.orderService = orderService;
        this.modelMapper = modelMapper;

    }

    @GetMapping("/add")
    public String addOrder() {
        return "order-add";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }

    @PostMapping("/add")
    public String addOrderPost(@Valid OrderAddBindingModel orderAddBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);
            return "redirect:add";
        }

        OrderAddServiceModel orderAddServiceModel = this.modelMapper.map(orderAddBindingModel, OrderAddServiceModel.class);
        boolean orderAdded = this.orderService.addOrderInDataBase(orderAddServiceModel);
        if (orderAdded) {
            return "redirect:/";
        }
        return "redirect:add";
    }

    @GetMapping("/ready{id}")
    public String readyOrder(@PathVariable Long id) {
        boolean orderDeleted = this.orderService.deleteOrder(id);
        if(orderDeleted) {
            System.out.println("ORDER DELETED");
        }
        return "redirect:/";
    }

}

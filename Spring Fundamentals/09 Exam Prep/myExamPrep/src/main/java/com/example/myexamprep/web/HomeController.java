package com.example.myexamprep.web;

import com.example.myexamprep.model.view.OrderViewModel;
import com.example.myexamprep.model.view.UserViewModel;
import com.example.myexamprep.service.OrderService;
import com.example.myexamprep.service.UserService;
import com.example.myexamprep.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser,
                          OrderService orderService,
                          UserService userService) {

        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    private String getIndex(Model model) {

        if (this.currentUser.getId() == null) {
            System.out.println("RETURN INDEX");
            return "index";
        }

        List<OrderViewModel> allOrdersByPriceDesc = this.orderService.findAllOrdersByPriceDesc();
        model.addAttribute("allOrders", allOrdersByPriceDesc);

        Integer timeToPrepareAllOrders = this.orderService.getAllNeededTime();
        model.addAttribute("timeToPrepareAllOrders", timeToPrepareAllOrders);

        List<UserViewModel> employeesAndTheirOrdersCount = this.userService.findEmployeesAndTheirOrdersCount();
        model.addAttribute("employeesAndTheirOrdersCount", employeesAndTheirOrdersCount);

        System.out.println("RETURN HOME");
        return "home";
    }

}

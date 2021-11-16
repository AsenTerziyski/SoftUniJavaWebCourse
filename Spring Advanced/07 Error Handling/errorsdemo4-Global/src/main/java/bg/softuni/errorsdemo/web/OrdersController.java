package bg.softuni.errorsdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersController {

    @GetMapping("/orders/{orderId}")
    private String getProductDetails(@PathVariable Long orderId) {
        throw new ObjectNotFoundException(orderId);
    }
}

package bg.softuni.errorsdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {

    @GetMapping("/products/{id}/details")
    private String getProductDetails(@PathVariable Long id) {
        throw new ProductNotFoundException();
    }

    @GetMapping("/products/{id}/error")
    private String crashAndBoom(@PathVariable Long id) {
        throw new NullPointerException();
    }
}

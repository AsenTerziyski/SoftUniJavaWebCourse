package bg.softuni.errorsdemo.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {

    @GetMapping("/products/{productId}")
    private String getProductDetails(@PathVariable Long productId) {
        System.out.println();
        throw new ObjectNotFoundException(productId);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleExceptions(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("productNotFound");
        modelAndView.addObject("productId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }


//    @ExceptionHandler(ProductNotFoundException.class)
//    public ModelAndView handleDbExceptions(ProductNotFoundException e) {
//        ModelAndView modelAndView = new ModelAndView("productNotFound");
//        modelAndView.addObject("productId", e.getProductId());
//        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//        return modelAndView;
//    }

//    @GetMapping("products/{id}/error")
//    private String crashAndBoom(@PathVariable Long id) {
//        System.out.println();
//        throw new NullPointerException();
//    }
}

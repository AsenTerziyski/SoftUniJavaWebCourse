//package bg.softuni.errorsdemo.web;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The product not found!!")
//public class ProductNotFoundException extends RuntimeException{
//    private final Long objectId;
//    public ProductNotFoundException(Long objectId) {
//        super("Can not find product with ID: " + objectId + "!");
//        this.objectId = objectId;
//    }
//
//    public Long getProductId() {
//        return objectId;
//    }
//}

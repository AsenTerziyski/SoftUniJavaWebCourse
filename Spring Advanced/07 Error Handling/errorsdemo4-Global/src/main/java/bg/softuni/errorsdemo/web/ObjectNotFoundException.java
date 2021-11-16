package bg.softuni.errorsdemo.web;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The product not found!!")
public class ObjectNotFoundException extends RuntimeException{
    private final Long objectId;
    public ObjectNotFoundException(Long objectId) {
        super("Can not find object with ID: " + objectId + "!");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}

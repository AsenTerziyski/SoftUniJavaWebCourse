package bg.softuni.errorsdemo.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleExceptions(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("objectNotFound");
        modelAndView.addObject("objectId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//        modelAndView.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        return modelAndView;
    }
}

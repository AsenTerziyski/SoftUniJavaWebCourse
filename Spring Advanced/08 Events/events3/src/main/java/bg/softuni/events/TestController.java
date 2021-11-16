package bg.softuni.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    private final ApplicationEventPublisher applicationEventPublisher;

    public TestController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/order-create")
    public String orderCreate() {

        //няма да сработи с ApplicationEvent orderEvent
//        ApplicationEvent orderEvent = new OrderCreateEvent(this, UUID.randomUUID().toString());

        // трябва да е OrderCreateEvent orderEvent
        OrderCreateEvent orderEvent = new OrderCreateEvent(this, UUID.randomUUID().toString());
        applicationEventPublisher.publishEvent(orderEvent);
        return "order created";
    }
}

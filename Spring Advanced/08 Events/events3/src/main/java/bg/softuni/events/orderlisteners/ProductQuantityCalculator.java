package bg.softuni.events.orderlisteners;

import bg.softuni.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductQuantityCalculator {
    private Logger LOGGER = LoggerFactory.getLogger(ProductQuantityCalculator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent orderCreateEvent){
        LOGGER.info("Order No {} has been creaded. Will calculate the current product quantities.",
                orderCreateEvent.getOrderId());

        //TODO -> see what products had been created and calculate product quantities!
    }

}

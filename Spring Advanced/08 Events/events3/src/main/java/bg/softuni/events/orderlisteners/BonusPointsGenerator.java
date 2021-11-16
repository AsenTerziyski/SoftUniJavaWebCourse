package bg.softuni.events.orderlisteners;

import bg.softuni.events.OrderCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonusPointsGenerator {
    private Logger LOGGER = LoggerFactory.getLogger(BonusPointsGenerator.class);

    @EventListener(OrderCreateEvent.class)
    public void onOrderCreated(OrderCreateEvent orderCreateEvent){
        LOGGER.info("Order No {} has been created. Bonus points to the" + "client.", orderCreateEvent.getOrderId());
        //todo -> bonus points to the client!

    }
}

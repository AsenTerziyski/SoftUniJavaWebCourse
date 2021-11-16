package bg.softuni.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
//public class ApplicationListenerTest implements ApplicationListener {
public class ApplicationListenerTest implements ApplicationListener<ServletRequestHandledEvent> {
    private Logger LOGGER = LoggerFactory.getLogger(ApplicationListenerTest.class);


    @Override
//    public void onApplicationEvent(ApplicationEvent event) {
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        LOGGER.info("I have received an EVENT {}", event);
    }
}

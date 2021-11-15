package bg.softuni.scheduler_m.lmsuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FixedRateScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateScheduler.class);

    @Scheduled(fixedRate = 5000)
    public void ShowTimeWithFixesDelay() {
        LOGGER.info("Hello, from fixed RATE scheduler! :) at {}", LocalDateTime.now());
    }
}

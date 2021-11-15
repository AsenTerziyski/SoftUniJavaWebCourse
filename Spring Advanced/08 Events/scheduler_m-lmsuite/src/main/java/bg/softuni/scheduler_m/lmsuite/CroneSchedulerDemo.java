package bg.softuni.scheduler_m.lmsuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CroneSchedulerDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(CroneSchedulerDemo.class);

    //    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "${schedulers.cron}")
    public void ShowTimeWithCrone() {
//        System.out.println(LocalDateTime.now())
        LOGGER.info("Hello, Malmsuite! :) at {}", LocalDateTime.now());
    }
}

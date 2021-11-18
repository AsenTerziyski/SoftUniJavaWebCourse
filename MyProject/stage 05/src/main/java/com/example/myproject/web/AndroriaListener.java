package com.example.myproject.web;

import com.example.myproject.model.entities.UserBrowser;
import com.example.myproject.service.UserBrowserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.time.LocalDate;

@Component
public class AndroriaListener {
    private final UserBrowserService userBrowserService;
    private Logger LOGGER = LoggerFactory.getLogger(AndroriaListener.class);

    public AndroriaListener(UserBrowserService userBrowserService) {
        this.userBrowserService = userBrowserService;
    }


    @EventListener(ServletRequestHandledEvent.class)
    public void onApplicationEvent(ServletRequestHandledEvent event) {

        String userName = event.getUserName();
        if (userName != null) {
            LocalDate now = LocalDate.now();
            System.out.println(userName + "browsed on " + now);
            LOGGER.info("Event: {}", event);
            UserBrowser userBrowser = new UserBrowser();
            userBrowser.setUsername(userName);
            userBrowser.setLocalDate(now);
            this.userBrowserService.saveUserBrowser(userBrowser);
        }

    }
}

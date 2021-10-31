package com.example.testprojecttwo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilityController {

    @GetMapping("/rooms")
    public String getRoomsPage() {
        return "rooms";
    }

    @GetMapping("/rooms/double")
    public String getDoubleRoomPage() {
        return "roomsDouble";
    }

    @GetMapping("/restaurant")
    public String getRestaurantPage() {
        return "restaurant";
    }

    @GetMapping("/parking")
    public String getParkingPage() {
        return "parking";
    }

}

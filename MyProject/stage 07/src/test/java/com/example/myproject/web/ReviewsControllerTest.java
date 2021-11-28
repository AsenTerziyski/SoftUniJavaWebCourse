package com.example.myproject.web;

import com.example.myproject.service.GuestService;
import com.example.myproject.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestService guestService;
    @Autowired
    private ReviewService reviewService;


    @Test
    void getReviewsPage() throws Exception {
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allReviews"))
                .andExpect(view().name("reviews"));
    }

    @Test
    void reviewSendBindingModel() {
    }

    @Test
    void sendEmail() {
    }

    @Test
    void getReviewsEdit() {
    }

    @Test
    void removeReview() {
    }

    @Test
    void handlePictureFileExceptions() {
    }
}
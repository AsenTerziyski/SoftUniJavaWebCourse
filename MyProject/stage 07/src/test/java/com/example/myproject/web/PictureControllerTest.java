package com.example.myproject.web;

import com.example.myproject.repository.PictureRepository;
import com.example.myproject.service.CloudinaryService;
import com.example.myproject.service.PictureService;
import com.example.myproject.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
class PictureControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;

    @Test
    void testGetAddPicture() throws Exception {
        mockMvc.perform(get("/pictures/add")).andExpect(model().attribute("pictureIsEmpty", false))
                .andExpect(status().isOk()).andExpect(view().name("picture-add"));
    }

    @Test
    void pictureBindingModel() {
    }

    @Test
    void testAddPicture() {
    }

    @Test
    void handlePictureFileExceptions() {
    }

    @Test
    void testHandlePictureFileExceptions() {
    }

    @Test
    void delete() {
    }

    @Test
    void all() {
    }
}
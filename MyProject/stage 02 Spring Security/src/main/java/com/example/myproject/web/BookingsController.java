package com.example.myproject.web;

import com.example.myproject.model.binding.BookingBindingModel;
import com.example.myproject.model.binding.MessageSendBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
//@RequestMapping("/book")
public class BookingsController {

    @GetMapping("/book")
    public String getBooking() {
        return "booking_create";
    }

    @ModelAttribute
    public BookingBindingModel bookingBindingModel() {
        return new BookingBindingModel();
    }

    @PostMapping("/book/create")
    private String booking(@Valid BookingBindingModel bookingBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        System.out.println();
        String email = bookingBindingModel.getEmail();

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("bookingBindingModel", bookingBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookingBindingModel", bookingBindingModel);
            return "redirect:/";
        }
        return "redirect:/";
    }


}

package com.example.myproject.web;

import com.example.myproject.model.binding.ReviewSendBindingModel;
import com.example.myproject.model.view.ReviewSummeryView;
import com.example.myproject.service.GuestService;
import com.example.myproject.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReviewsController {
    private final GuestService guestService;
    private final ReviewService reviewService;

    public ReviewsController(GuestService guestService, ReviewService reviewService) {
        this.guestService = guestService;
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String getReviewsPage(Model model) {
        System.out.println();
        List<ReviewSummeryView> allReviews = this.reviewService.getAllReviews();
        model.addAttribute("allReviews", allReviews);
        return "reviews";
    }

    @ModelAttribute
    public ReviewSendBindingModel reviewSendBindingModel() {
        return new ReviewSendBindingModel();
    }

    @PostMapping("/reviews/send")
    public String sendEmail(@Valid ReviewSendBindingModel reviewSendBindingModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("reviewSendBindingModel", reviewSendBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewSendBindingModel", reviewSendBindingModel);
            return "reviews";
        }

        this.guestService.addReview(reviewSendBindingModel);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/remove")
    public String getReviewsEdit(Model model) {
        List<ReviewSummeryView> allReviews = this.reviewService.getAllReviews();
        model.addAttribute("allReviews", allReviews);
        return "reviews-remove";
    }

    @DeleteMapping("/reviews/remove/{id}")
    public String removeReview(@PathVariable Long id) {
        this.reviewService.deleteReviewById(id);
        System.out.println();
        return "redirect:/reviews/remove";
    }

//    @PostMapping("/bookings/remove/{id}")
//    public String removeBooking(@PathVariable Long id) {
//        this.bookingService.removeBooking(id);
//        return "redirect:/remove-booking";
//    }
}

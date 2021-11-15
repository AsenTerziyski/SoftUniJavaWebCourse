package com.example.myproject.service.impl;

import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.ReviewEntity;
import com.example.myproject.model.view.ReviewSummeryView;
import com.example.myproject.repository.ReviewRepository;
import com.example.myproject.service.GuestService;
import com.example.myproject.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final GuestService guestService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper, GuestService guestService) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.guestService = guestService;
    }

    @Override
    public void initReviews() {
        if (this.reviewRepository.count() == 0) {
            String name = "Axles Roses";
            String email = "axles@gunsNroses.com";
            saveReview(name, email);

            name = "Slashes";
            email = "slashes@gunsNroses.com";
            saveReview(name, email);

            name = "Duffesss";
            email = "duffesss@gunsNroses.com";
            saveReview(name, email);
        }

    }

    private void saveReview(String name, String email) {
        ReviewEntity sampleReview = new ReviewEntity();
        sampleReview.setReviewerName(name);
        sampleReview.setReviewText("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui" +
                "officia deserunt mollit anim id est laborum.");

        GuestEntity newGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail = this.guestService.createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail(email);
        sampleReview.setGuest(newGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail);
        this.reviewRepository.save(sampleReview);
    }

    @Override
    public List<ReviewSummeryView> getAllReviews() {
        List<ReviewEntity> allReviews = this.reviewRepository.findAllReviews();
        List<ReviewSummeryView> allReviewsExtraction = allReviews.stream().map(reviewEntity ->
                {
                    System.out.println();
                    ReviewSummeryView map = this.modelMapper.map(reviewEntity, ReviewSummeryView.class);
                    if (reviewEntity.getReviewerName().trim().equals("")) {
                        map.setReviewerName("Anonymous");
                    }
                    return map;
                }
        )
                .collect(Collectors.toList());

        return allReviewsExtraction;
    }

    @Override
    public ReviewSummeryView getReviewById(Long id) {
        ReviewEntity reviewEntity = this.reviewRepository.findById(id).orElse(null);
        if (reviewEntity != null) {
            return this.modelMapper.map(reviewEntity, ReviewSummeryView.class);
        }
        return null;
    }

    @Override
    public boolean deleteReviewById(Long id) {
        ReviewEntity reviewEntity = this.reviewRepository.findById(id).orElse(null);
        if (reviewEntity !=null) {
            this.reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Long updateReview(ReviewSummeryView setId) {

        return null;
    }


}
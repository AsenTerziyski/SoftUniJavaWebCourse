package com.example.myproject.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity {

    private String reviewerName;
    private String reviewText;
    private GuestEntity guest;


    public ReviewEntity() {
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public ReviewEntity setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
        return this;
    }

    @Column(columnDefinition = "LONGTEXT")
    public String getReviewText() {
        return reviewText;
    }

    public ReviewEntity setReviewText(String messageText) {
        this.reviewText = messageText;
        return this;
    }

    @ManyToOne()
    public GuestEntity getGuest() {
        return guest;
    }

    public ReviewEntity setGuest(GuestEntity guest) {
        this.guest = guest;
        return this;
    }
}

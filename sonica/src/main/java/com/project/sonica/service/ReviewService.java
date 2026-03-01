package com.project.sonica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sonica.entity.Booking;
import com.project.sonica.entity.Customer;
import com.project.sonica.entity.Review;
import com.project.sonica.repos.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBooking(Booking booking) {
        return reviewRepository.findByBooking(booking);
    }

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }
}


package dw.gameshop.service;

import dw.gameshop.model.Review;
import dw.gameshop.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReview(){
        return reviewRepository.findAll();
    }

    public Review saveReview(Review review){
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }
}

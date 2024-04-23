package dw.gameshop.service;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Review;
import dw.gameshop.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    // Dto를 사용하는 이유
    // → 보안성 해결도 있지만, 원하는 데이터만을 뽑아내기 위한 것도 있음
    public List<ReviewDto> getReviewAllByDto(){
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        for(int i = 0; i < reviewList.size(); i++){
            ReviewDto reviewDto = new ReviewDto();
//            reviewDto.setReviewPoint(reviewList.get(i).getPoint());
//            reviewDto.setReviewText(reviewList.get(i).getReviewText());
//            reviewDto.setGameId(reviewList.get(i).getGame().getId());
//            reviewDto.setGameName(reviewList.get(i).getGame().getTitle());
//            reviewDto.setUserId(reviewList.get(i).getUser().getUserId());
//            reviewDtoList.add(reviewDto);
            reviewDtoList.add(reviewDto.toReviewDtoFromReview(reviewList.get(i)));
        }
        return reviewDtoList;
    }
}

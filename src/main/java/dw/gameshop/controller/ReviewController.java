package dw.gameshop.controller;

import dw.gameshop.model.Review;
import dw.gameshop.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/products/reviews")
    public ResponseEntity<List<Review>> getAllReview(){
        return new ResponseEntity<>(reviewService.getAllReview(),
                HttpStatus.OK);
    }

    @PostMapping("/products/reviews")
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
        return new ResponseEntity<>(reviewService.saveReview(review),
                HttpStatus.OK);
    }

}

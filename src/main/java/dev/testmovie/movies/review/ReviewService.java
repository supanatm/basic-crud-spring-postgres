package dev.testmovie.movies.review;

import dev.testmovie.movies.movie.Movie;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepo, MongoTemplate mongoTemplate) {
        this.reviewRepo = reviewRepo;
        this.mongoTemplate = mongoTemplate;
    }


    public Review createReview(String reviewBody, String imdbId) {

        Review review = new Review();
        review.setBody(reviewBody);
        this.reviewRepo.insert(review);

        this.mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

    }

}

package dev.testmovie.movies.movie;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepo;

    public MovieService(MovieRepository movieRepo, MongoTemplate mongoTemplate) {
        this.movieRepo = movieRepo;
    }


    public List<Movie> allMovies() {
        return this.movieRepo.findAll();
    }


    public Movie movieById(ObjectId id) {

        Optional<Movie> optionalMovie = this.movieRepo.findById(id);

        if(optionalMovie.isEmpty()) {
            return null;
        }

        return optionalMovie.get();
    }


    public Movie movieByImdbId(String imdb) {

        Optional<Movie> optionalMovie = this.movieRepo.findByImdbId(imdb);

        if(optionalMovie.isEmpty()) {
            return null;
        }

        return optionalMovie.get();
    }

    public Movie createMovie(String imdbId,
                             String title,
                             String releaseDate,
                             String trailerLink,
                             String poster) {
        Movie movie = new Movie();
        movie.setImdbId(imdbId);
        movie.setTitle(title);
        movie.setReleaseDate(releaseDate);
        movie.setTrailerLink(trailerLink);
        movie.setPoster(poster);
        this.movieRepo.insert(movie);

        return movie;
    }

    public Movie deleteMovieById(ObjectId id) {
        Optional<Movie> optionalMovie = this.movieRepo.findById(id);

        if(optionalMovie.isEmpty()) {
            return null;
        }

        this.movieRepo.deleteById(id);
        return optionalMovie.get();
    }
}

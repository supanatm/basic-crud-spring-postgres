package dev.testmovie.movies.movie;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(this.movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") ObjectId id) {
        return new ResponseEntity<Movie>(this.movieService.movieById(id), HttpStatus.OK);
    }

    @GetMapping("/imdb/{imdbId}")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable("imdbId") String imdbId) {
        return new ResponseEntity<Movie>(this.movieService.movieByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Movie>(this.movieService.createMovie(
                payload.get("imdbId"),
                payload.get("title"),
                payload.get("releaseDate"),
                payload.get("trailerLink"),
                payload.get("poster")),
                HttpStatus.CREATED);
    }
}

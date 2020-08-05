package com.springlearning.movieinfoservice.resources;

import com.springlearning.movieinfoservice.models.Movie;
import com.springlearning.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }

//    @RequestMapping("/{movieId}")
//    public String getMovieInfo(@PathVariable("movieId") String movieId) {
//        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
//        //return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
//
//    }
}

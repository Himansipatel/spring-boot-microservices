package com.springlearnig.moviecatalogservice.resources;

import com.springlearnig.moviecatalogservice.models.CatalogItem;
import com.springlearnig.moviecatalogservice.models.Movie;
import com.springlearnig.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    WebClient.Builder webClientBuilder;

    //get all rated movie IDs
    //For each movie ID,call movie info service and get details
    //put them all together



    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);

        return ratings.getUserRating().stream().map(rating ->{
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);  //making call to API and unmarshalling into the Obj
            return new CatalogItem(movie.getName(),"description",rating.getRating());
        } )
        .collect(Collectors.toList());
       // return Collections.singletonList(new CatalogItem("Transformers","Test",4));
    }

}

        /*    Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();      */
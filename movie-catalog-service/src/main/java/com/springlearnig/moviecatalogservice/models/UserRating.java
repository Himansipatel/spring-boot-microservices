package com.springlearnig.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    List<Rating> userRating;

    public UserRating() {
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}

package by.artsem.druzhbahub.controller;

import by.artsem.druzhbahub.model.*;

import java.util.List;

public class ProfileResponseDto {//TODO add images
    private Long id;
    private String username;
    private String selfSummary;
    private Integer rate;
    private List<Profile> friends;
    private List<Likes> likes;
    private List<Post> posts;
    private List<Review> reviewsFrom;
    private List<Review> reviewsTo;
}

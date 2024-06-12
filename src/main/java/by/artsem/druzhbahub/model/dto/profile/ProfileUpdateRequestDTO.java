package by.artsem.druzhbahub.model.dto.profile;

import by.artsem.druzhbahub.model.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ProfileUpdateRequestDTO {//TODO add images
    @NotBlank
    private String username;
    private String selfSummary;
    private Integer rate;
    private List<Profile> friends;
    private List<Likes> likes;
    private List<Post> posts;
    private List<Review> reviewsFrom;
    private List<Review> reviewsTo;
}

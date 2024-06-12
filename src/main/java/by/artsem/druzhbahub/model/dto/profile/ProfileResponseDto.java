package by.artsem.druzhbahub.model.dto.profile;

import by.artsem.druzhbahub.model.*;
import lombok.Data;

import java.util.List;

@Data
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

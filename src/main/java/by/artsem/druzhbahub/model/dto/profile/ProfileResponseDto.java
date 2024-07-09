package by.artsem.druzhbahub.model.dto.profile;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProfileResponseDto {//TODO add images
    private Long id;
    private String username;
    private String selfSummary;
    private Double rate;
    private Integer age;
    private String place;
    private Integer subscribers;
    private Integer subscribedTo;
    private List<Long> posts;
    private List<Long> reviewsFromThis;
    private List<Long> reviewsOnThis;
}

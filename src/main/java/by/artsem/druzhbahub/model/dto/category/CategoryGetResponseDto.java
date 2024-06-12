package by.artsem.druzhbahub.model.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGetResponseDto {
    private Long id;
    private String name;
}

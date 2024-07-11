package by.artsem.druzhbahub.service.impl;

import by.artsem.druzhbahub.model.dto.util.DistanceMatrixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DistanceMatrixService {

    private static final String MAPS_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&units=metric&key=%s";

    private final RestTemplate restTemplate;

    @Value("${gcp.api.maps.key}")
    private String apiKey;

    public int getDistanceValue(String origin, String destination) {
        String url = String.format(MAPS_API_URL, origin, destination, apiKey);

        DistanceMatrixResponse response = restTemplate.getForObject(url, DistanceMatrixResponse.class);
        checkResult(response);
        DistanceMatrixResponse.Element element = response.getRows().get(0).getElements().get(0);
        System.out.println(element.getDistance().getValue());
        return element.getDistance().getValue();
    }

    private void checkResult(DistanceMatrixResponse response) {
        if (!(response != null && response.getRows() != null && !response.getRows().isEmpty())) {
            throw new RuntimeException("Unable to retrieve distance value from response");
        }
    }
}

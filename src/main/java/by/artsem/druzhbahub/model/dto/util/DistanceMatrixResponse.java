package by.artsem.druzhbahub.model.dto.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceMatrixResponse {

    @JsonProperty("rows")
    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Row {
        @JsonProperty("elements")
        private List<Element> elements;

        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Element {
        @JsonProperty("distance")
        private Distance distance;

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Distance {
        @JsonProperty("value")
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
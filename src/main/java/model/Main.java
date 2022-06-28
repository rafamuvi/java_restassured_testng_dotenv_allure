package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Main(
        float temp,
        @JsonProperty("feels_like") float feelsLike,
        @JsonProperty("temp_min") float tempMin,
        @JsonProperty("temp_max") float tempMax,
        int pressure,
        int humidity,
        @JsonProperty("sea_level") int seaLvl,
        @JsonProperty("grnd_level") int grndLvl
) {
}

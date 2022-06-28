package model;

import java.util.List;

public record OpenWeatherResponse(
        Coord coord,
        List<Weather> weather,
        String base,
        Main main,
        int visibility,
        Wind wind,
        Clouds clouds,
        int dt,
        Sys sys,
        int timezone,
        int id,
        String name,
        int cod) {
}


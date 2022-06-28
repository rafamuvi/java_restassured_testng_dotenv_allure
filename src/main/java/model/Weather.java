package model;

public record Weather(
        int id,
        String main,
        String description,
        String icon
) {
}

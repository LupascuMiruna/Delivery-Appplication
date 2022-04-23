package com.company.entities;

public class Courier {
    private String name;
    private String transport;
    private Double rating = 0.0;
    private Integer numberRatings = 0;
    private Double score = 0.0;

    public Courier(String name, String transport, Double rating, Integer numberRatings, Double score) {
        this.name = name;
        this.transport = transport;
        this.rating = rating;
        this.numberRatings = numberRatings;
        this.score = score;
    }

    public Courier(String name, String transport) {
        this.name = name;
        this.transport = transport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNumberRatings() {
        return numberRatings;
    }

    public void setNumberRatings(Integer numberRatings) {
        this.numberRatings = numberRatings;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "name='" + name + '\'' +
                ", transport='" + transport + '\'' +
                ", rating=" + rating +
                ", numberRatings=" + numberRatings +
                ", score=" + score +
                '}';
    }
}

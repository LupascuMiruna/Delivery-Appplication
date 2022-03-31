package com.company.entities;

public class Courier {
    private String name;
    private String transport;
    private Double raiting = 0.0;
    private Integer numberRaitings = 0;
    private Double score = 0.0;

    public Courier(String name, String transport, Double raiting, Integer numberRaitings, Double score) {
        this.name = name;
        this.transport = transport;
        this.raiting = raiting;
        this.numberRaitings = numberRaitings;
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

    public Double getRaiting() {
        return raiting;
    }

    public void setRaiting(Double raiting) {
        this.raiting = raiting;
    }

    public Integer getNumberRaitings() {
        return numberRaitings;
    }

    public void setNumberRaitings(Integer numberRaitings) {
        this.numberRaitings = numberRaitings;
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
                ", raiting=" + raiting +
                ", numberRaitings=" + numberRaitings +
                ", score=" + score +
                '}';
    }
}

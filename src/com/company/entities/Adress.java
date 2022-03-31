package com.company.entities;

import java.util.Scanner;

public class Adress {
    protected County county;
    protected String city;
    protected String street;
    protected Integer number;

    public Adress(County country, String city, String street, Integer number) {
        this.county = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    static public Adress createNewAddress(Scanner scanner) {
        System.out.println("County:");
        String countyStr = scanner.next();
        County county = County.valueOf(countyStr.toUpperCase());
        System.out.println("City:");
        String city = scanner.next();
        System.out.println("Street:");
        String street = scanner.next();
        System.out.println("Number:");
        Integer number = scanner.nextInt();
        return new Adress(county, city, street, number);
    }

    public County getCountry() {
        return county;
    }

    public void setCountry(County country) {
        this.county = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "county=" + county +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}

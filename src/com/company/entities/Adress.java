package com.company.entities;

import java.util.Arrays;
import java.util.Scanner;
import com.company.exceptions.CountyException;



public class Adress {
    protected County county;
    protected String city;
    protected String street;
    protected Integer number;

    public Adress(County county, String city, String street, Integer number) {
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    static public Adress createNewAddress(Scanner scanner) {
        County county = null;
        while(county == null) {
            System.out.println("County:");
            String countyStr = scanner.next();

            try {
                county = Arrays.stream(County.values())
                        .filter(c -> c.getValue().equalsIgnoreCase(countyStr))
                        .findFirst()
                        .orElseThrow(() -> new CountyException("Wrong county")); ///it needs a lambda expression that returns an exception instance
            }
            catch (CountyException exception) {
                System.out.println(exception.getMessage());
            }
        }

        System.out.println("City:");
        String city = scanner.next();
        System.out.println("Street:");
        String street = scanner.next();
        System.out.println("Number:");
        Integer number = scanner.nextInt();
        return new Adress(county, city, street, number);
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
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

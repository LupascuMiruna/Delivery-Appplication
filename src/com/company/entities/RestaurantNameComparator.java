package com.company.entities;

import com.company.entities.Restaurant;

import java.util.Comparator;

public class RestaurantNameComparator implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        int result = r1.getNumberRaitings() - r2.getNumberRaitings();//first of all after number of raitings
        if (result != 0) {
            return result;
        }
        else {
            return r1.getName().compareTo(r2.getName()); //after that after name
        }
    }
}

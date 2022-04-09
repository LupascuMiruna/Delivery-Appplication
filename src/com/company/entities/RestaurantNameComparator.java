package com.company.entities;

import java.util.Comparator;

public class RestaurantNameComparator implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        int result = r1.getNumberRatings() - r2.getNumberRatings();//first of all after number of ratings
        if (result != 0) {
            return result;
        }
        else {
            return r1.getName().compareTo(r2.getName()); //after that after name
        }
    }
}

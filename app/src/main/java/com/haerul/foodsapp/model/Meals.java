package com.haerul.foodsapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Meals {

    @SerializedName("meals")
    private List<Meal> meals;

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
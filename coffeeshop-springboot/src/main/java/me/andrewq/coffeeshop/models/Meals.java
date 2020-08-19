package me.andrewq.coffeeshop.models;


public class Meals {
    

    private Integer mealId;

    private String mealName;

    private Double price;

    public Integer getMealId() {
        return this.mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
}
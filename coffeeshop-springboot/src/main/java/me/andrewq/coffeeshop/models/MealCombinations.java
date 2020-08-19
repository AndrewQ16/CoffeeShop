package me.andrewq.coffeeshop.models;


public class MealCombinations {


    private Integer comboId;

    
    private Integer mealId;

    private Integer productId;

    public Integer getMealId() {
        return this.mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getComboId() {
        return this.comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }
}
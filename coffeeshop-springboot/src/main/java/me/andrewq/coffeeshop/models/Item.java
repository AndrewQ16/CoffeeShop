package me.andrewq.coffeeshop.models;


/**
 * Represents an item in a order
 */
public class Item {
    
    private Integer productId;

    private String name;

    private Double cost;

    private String type;

    private String[][] chosenOptions;

    public Item() {

    }


    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return this.cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[][] getChosenOptions() {
        return this.chosenOptions;
    }

    public void setChosenOptions(String[][] chosenOptions) {
        this.chosenOptions = chosenOptions;
    }

}
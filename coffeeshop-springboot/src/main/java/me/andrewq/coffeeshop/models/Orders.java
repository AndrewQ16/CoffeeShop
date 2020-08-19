package me.andrewq.coffeeshop.models;

import java.util.List;

public class Orders {
    
    private List<Item> items;

    private String fname;

    private String lName;

    private String email;

    private Boolean isMember;

    public Orders() {

    }


    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isIsMember() {
        return this.isMember;
    }

    public Boolean getIsMember() {
        return this.isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

}
package me.andrewq.coffeeshop.models;

import java.util.Date;
import java.util.List;

public class Orders {
    
    private List<Item> items;

    private String fname;

    private String lName;

    private String email;

    private Date date;

    private Boolean isPayed;

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


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isIsPayed() {
        return this.isPayed;
    }

    public Boolean getIsPayed() {
        return this.isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    @Override
    public String toString(){
        return "Fname:" + this.fname
            + "\nLname:" + this.lName
            + "\nEmail:" + this.email;
    }

}
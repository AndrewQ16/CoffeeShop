package me.andrewq.coffeeshop.models;

public class Confirmation {
    
    private String fname;

    private String lname;

    private String email;

    private String orderNumber;

    private Boolean isOrderSaved;

    public Confirmation(){

    }


    public Confirmation(String fname, String lname, String email, String orderNumber, Boolean isOrderSaved) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.orderNumber = orderNumber;
        this.isOrderSaved = isOrderSaved;
    }


    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public Boolean isIsOrderSaved() {
        return this.isOrderSaved;
    }

    public Boolean getIsOrderSaved() {
        return this.isOrderSaved;
    }

    public void setIsOrderSaved(Boolean isOrderSaved) {
        this.isOrderSaved = isOrderSaved;
    }


}
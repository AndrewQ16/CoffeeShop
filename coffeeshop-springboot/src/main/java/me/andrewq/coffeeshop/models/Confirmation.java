package me.andrewq.coffeeshop.models;

public class Confirmation {
    
    private String fname;

    private String lname;

    private String email;

    private String orderNumber;

    private Boolean isOrderSaved;

    private Boolean isProcessed;

    public Confirmation(){

    }


    public Confirmation(String fname, String lname, String email, String orderNumber, Boolean isOrderSaved, Boolean isProcessed) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.orderNumber = orderNumber;
        this.isOrderSaved = isOrderSaved;
        this.isProcessed = isProcessed;
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


    public Boolean isIsProcessed() {
        return this.isProcessed;
    }

    public Boolean getIsProcessed() {
        return this.isProcessed;
    }

    public void setIsProcessed(Boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

}
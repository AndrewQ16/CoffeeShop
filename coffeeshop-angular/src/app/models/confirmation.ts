export interface Confirmation {
    //first name
    fname: string;

    //last name
    lname: string;

    //email
    email: string;

    // Number to designate order
    orderNumber: string;

    //if the user is signed in we confirm if the order was saved in the user's history
    isOrderSaved: boolean;    

}
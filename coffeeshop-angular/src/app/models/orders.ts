import { Item } from './item';

export interface Orders {
    
    items: Item[];

    fname: String;

    lname: String;

    email: String;

    isMember: Boolean;

}
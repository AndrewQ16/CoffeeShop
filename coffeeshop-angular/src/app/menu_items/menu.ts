export interface Menu {
    productId: number;

    name: string;
    
    price: number;
    
    productOptions: string[][];
    
    type: string;

    // additional field for drinks and coffees
    currentSize: string;

    creams: number;

    sugars: number;

}
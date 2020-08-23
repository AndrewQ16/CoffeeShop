export interface Item {
    productId: number;

    name: string;

    cost: number;

    type: string;

    quantity: number;

    totalCost: number;

    /**
     * Options formatting:
     * (Mainly for drinks but if I can think of anything for Foods, I can post it here.)
     * 
     * Drink format:
     * [
     *  ["size", "N,S,M,L"],
     *  ["creams", #],
     *  ["sugars", #]
     * ]
     * 
     * Food format (no need):
     * [
     *  []
     * ]
     */

    chosenOptions: string[][];

    
}
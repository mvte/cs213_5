package com.example.cs213_5;

import java.util.ArrayList;

/**
 * Represents a Meatzza Pizza. A Meatzza Pizza's toppings include sausage,
 * pepperoni, beef, and ham.
 * @author Jan Marzan, Brian Zhang
 */
public class Meatzza extends Pizza {
    /**
     * Constructs a Deluxe Pizza given the crust and pizza type
     * @param crust the crust of this pizza.
     * @param pref "NY" or "Chicago", depending on which factory called this pizza.
     */
    public Meatzza(Crust crust, String pref) {
        this.toppings = new ArrayList<Topping>() {
            {
                add(Topping.SAUSAGE);
                add(Topping.PEPPERONI);
                add(Topping.BEEF);
                add(Topping.HAM);
            }
        };
        this.crust = crust;
        this.name = pref + " Meatzza";
    }

    /**
     * Customers cannot add/remove their own toppings for a Meatzza.
     * @param obj topping to be added
     * @return false
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * Customers cannot add/remove their own toppings for a Meatzza.
     * @param obj topping to be removed
     * @return false
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * Calculates the price of this pizza.
     * @return the price of this pizza
     */
    @Override
    public double price() {
        int size_n = size.getIntSize();
        switch (size_n) {
            case 0:
                return 15.99;
            case 1:
                return 17.99;
            case 2:
                return 19.99;
            default:
                return -1;
        }
    }
}

package com.example.cs213_5;

import javafx.collections.FXCollections;

import java.util.ArrayList;

/**
 * Represents a Deluxe Pizza. A Deluxe Pizza's toppings include sausage,
 * pepperoni, green pepper, onion, and mushroom.
 * @author Jan Marzan, Brian Zhang
 */
public class Deluxe extends Pizza {
    /**
     * Constructs a Deluxe Pizza given the crust and pizza type
     * @param crust the crust of this pizza.
     * @param pref "NY" or "Chicago", depending on which factory called this pizza.
     */
    public Deluxe(Crust crust, String pref) {
        ArrayList<Topping> temp = new ArrayList<>() {
            {
                add(Topping.SAUSAGE);
                add(Topping.PEPPERONI);
                add(Topping.GREEN_PEPPER);
                add(Topping.ONION);
                add(Topping.MUSHROOM);
            }
        };
        this.toppings = FXCollections.observableArrayList(temp);
        this.crust = crust;
        this.name = pref + " Deluxe";
    }

    /**
     * A pre-made pizza cannot add toppings.
     * @param obj topping to be added
     * @return false
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * A pre-made pizza cannot remove toppings.
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
        return switch (size_n) {
            case 0 -> 14.99;
            case 1 -> 16.99;
            case 2 -> 18.99;
            default -> -1;
        };
    }

}

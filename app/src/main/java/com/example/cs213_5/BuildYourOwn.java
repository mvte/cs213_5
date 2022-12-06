package com.example.cs213_5;

import java.util.ArrayList;

/**
 * Represents a Build-Your-Own Pizza. A Build Your Own Pizza can add toppings at the
 * discretion of the user. Each topping added increases the price of the pizza, to
 * a maximum of 7 added toppings.
 * @author Jan Marzan, Brian Zhang
 */
public class BuildYourOwn extends Pizza {

    /**
     * Constructs a Build Your Own Pizza given the crust and pizza type
     * @param crust the crust of this pizza.
     * @param pref "NY" or "Chicago", depending on which factory called this pizza.
     */
    public BuildYourOwn(Crust crust, String pref) {
        this.toppings = new ArrayList<Topping>();
        this.crust = crust;
        this.name = pref + " Build Your Own Pizza";
    }

    /**
     * The add method adds a specific topping to the toppings of a Pizza, if possible.
     * @param obj the topping to be added to the list of toppings
     * @return true if the topping was successfully added,
     *         false if the object was not at topping, or
     *         false if the current number of toppings is already the maximum number (7).
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Topping)) {
            return false;
        }
        if (toppings.size() < 7) {
            toppings.add((Topping) obj);
            return true;
        }
        return false;
    }

    /**
     * The remove method removes a specific topping to the toppings of a Pizza, if possible.
     * @param obj The topping to be removed to the list of toppings
     * @return true if the topping was successfully removed,
     *         false if the object was not a topping,
     *         false if the current number of toppings is already zero, or
     *         false if the topping was not found in the list.
     */
    @Override
    public boolean remove(Object obj) {
        if (!(obj instanceof Topping)) {
            return false;
        }
        if (toppings.size() > 0) {
            return toppings.remove((Topping) obj);
        }
        return false;
    }

    /**
     * Calculates the price of this pizza. Each topping added to the pizza
     * is an additional $1.59.
     * @return the price of this pizza
     */
    @Override
    public double price() {
        double price = 0;
        int size_n = size.getIntSize();
        if (size_n == 0) {
            price = 8.99;
        }
        if (size_n == 1) {
            price = 10.99;
        }
        if (size_n == 2) {
            price = 12.99;
        }
        price += toppings.size() * 1.59;
        return price;
    }

}

package com.example.cs213_5;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The Pizza abstract class represents a Pizza in the pizza creation system. A Pizza
 * is specified by the type of crust, its size, and the toppings.
 */
public abstract class Pizza implements Customizable {
    /** The list of toppings of a Pizza */
    protected ArrayList<Topping> toppings;
    /** The type of crust of a Pizza */
    protected Crust crust;
    /** The size of a Pizza */
    protected Size size;
    /** The name of the pizza */
    protected String name;

    /**
     * The price method calculates the price of a given Pizza.
     * @return the price of a Pizza.
     */
    public abstract double price();

    /**
     * The setSize method sets the size of a given Pizza.
     * @param size the size to change the Pizza to.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * The getToppings method gives the toppings of a pizza.
     * @return the toppings of a Pizza.
     */
    public  ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * The getCrust method gives the crust of a pizza.
     * @return the crust of a Pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Creates a string containing the pizza name, its size, and the toppings of the pizza.
     * @return String of format "[Pizza size] [NY/Chicago] [Pizza flavor] - [Toppings, ]"
     */
    @Override
    public String toString() {
        String result = size + " " + name;
        for(int i = 0; i < toppings.size(); i++) {
            String begin = i == 0 ? " - " : "";
            String end = i == toppings.size()-1? "" : ", ";
            result += begin + toppings.get(i) + end;
        }

        return result;
    }
}

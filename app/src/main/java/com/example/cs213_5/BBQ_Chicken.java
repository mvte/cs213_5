package com.example.cs213_5;
import java.util.ArrayList;

/**
 * Represents a BBQ Chicken Pizza. A BBQ Chicken Pizza's toppings include BBQ
 * chicken, green pepper, provolone, and cheddar.
 * @author Jan Marzan, Brian Zhang
 */
public class BBQ_Chicken extends Pizza {

    /**
     * Constructs a BBQ Chicken Pizza given the crust and pizza type
     * @param crust the crust of this pizza.
     * @param pref "NY" or "Chicago", depending on which factory called this pizza.
     */
    public BBQ_Chicken(Crust crust, String pref) {
        this.toppings = new ArrayList<Topping>() {
            {
                add(Topping.BBQ_CHICKEN);
                add(Topping.GREEN_PEPPER);
                add(Topping.PROVOLONE);
                add(Topping.CHEDDAR);
            }
        };
        this.crust = crust;
        this.name = pref + " BBQ Chicken Pizza";
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
        switch (size_n) {
            case 0:
                return 13.99;
            case 1:
                return 15.99;
            case 2:
                return 17.99;
            default:
                return -1;
        }
    }
}

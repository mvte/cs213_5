package com.example.cs213_5;

/**
 * The Topping enum is a class representing the 3 different pizza sizes.
 * Each Topping instance consists of a String of the topping name.
 * @author Jan Marzan, Brian Zhang
 */
public enum Topping {
    /** The anchovies topping */
    ANCHOVIES       ("Anchovies"),
    /** The BBQ chicken topping */
    BBQ_CHICKEN     ("BBQ Chicken"),
    /** The beef topping */
    BEEF            ("Beef"),
    /** The cheddar topping */
    CHEDDAR         ("Cheddar"),
    /** The green pepper topping */
    GREEN_PEPPER    ("Green Pepper"),
    /** The ham topping */
    HAM             ("Ham"),
    /** The mushroom topping*/
    MUSHROOM        ("Mushroom"),
    /** The olives topping */
    OLIVES          ("Olives"),
    /** The onion topping */
    ONION           ("Onion"),
    /** The pepperoni topping */
    PEPPERONI       ("Pepperoni"),
    /** The pineapple topping */
    PINEAPPLE       ("Pineapple"),
    /** The provolone topping */
    PROVOLONE       ("Provolone"),
    /** The sausage topping */
    SAUSAGE         ("Sausage");

    /** the name of the pizza topping */
    private final String topping;

    /**
     * Create a topping object.
     * @param topping the name of the topping to be created.
     */
    Topping(String topping) {
        this.topping = topping;
    }

    /**
     * Converts the topping enum into a readable format, consisting
     * of the topping name as a String.
     * @return the topping information as a String.
     */
    @Override
    public String toString() {
        return topping;
    }
}

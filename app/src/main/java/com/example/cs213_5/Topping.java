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
     * Searches for a Topping enum given a Topping string.
     * @param str the String to search for
     * @return the corresponding Topping in the enum class,
     *         null if the enum object is not found
     */
    public static Topping getTopping(String str) {
        Topping[] toppings = Topping.values();
        for (Topping t : toppings) {
            if (str.equalsIgnoreCase(t.topping)) {
                return t;
            }
        }
        return null;
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

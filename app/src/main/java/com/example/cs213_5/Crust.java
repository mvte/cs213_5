package com.example.cs213_5;

/**
 * The Crust enum is a class representing the 6 different crusts, depending on the Pizza style.
 * Each Crust instance is a String of the crust name.
 * @author Jan Marzan, Brian Zhang
 */
public enum Crust {
    /** The Chicago Pizza's Deep Dish crust */
    DEEP_DISH       ("Deep Dish"),
    /** The Chicago Pizza's Pan crust */
    PAN             ("Pan"),
    /** The Chicago Pizza's Stuffed crust*/
    STUFFED         ("Stuffed"),

    /** The NY Pizza's Brooklyn crust */
    BROOKLYN        ("Brooklyn"),
    /** The NY Pizza's Thin crust */
    THIN            ("Thin"),
    /** The NY Pizza's Hand-Tossed crust */
    HAND_TOSSED     ("Hand Tossed");

    /** The name of the crust */
    private final String crust;

    /**
     * Create a Crust object.
     * @param crust the name of the crust
     */
    Crust(String crust) {
        this.crust = crust;
    }

    /**
     * Converts the crust enum into a readable format, consisting
     * of the crust name as a String.
     * @return the crust information as a String.
     */
    @Override
    public String toString() {
        return crust;
    }
}

package com.example.cs213_5;

/**
 * The Size enum is a class representing the 3 different pizza sizes.
 * Each Size instance consists of a String of the size name, and a corresponding
 * integer value for each size.
 * @author Jan Marzan, Brian Zhang
 */
public enum Size {
    /** The small size pizza */
    SMALL   ("Small", 0),
    /** The medium size pizza */
    MEDIUM  ("Medium", 1),
    /** The large size pizza */
    LARGE   ("Large", 2);

    /** the name of the pizza size */
    private final String size_s;

    /** the integer value of the pizza size */
    private final int size_n;

    /**
     * Create a Size object.
     * @param size_s the size as a String
     * @param size_n the size as an int
     */
    Size(String size_s, int size_n) {
        this.size_s = size_s;
        this.size_n = size_n;
    }

    /**
     * Converts the size enum into a readable format, consisting
     * of the size name as a String.
     * @return the size information as a String.
     */
    @Override
    public String toString() {
        return size_s;
    }

    /**
     * Returns the size enum as an integer value
     * @return the size information as an int. Will return 0 for small, 1 for medium, and 2 for large.
     */
    public int getIntSize() {
        return size_n;
    }

    /**
     * Searches for a Size enum given a String.
     * @param size_s the String to search for
     * @return the corresponding Size in the enum class,
     *         null if the enum object is not found
     */
    public static Size getSize(String size_s) {
        Size[] sizes = Size.values();
        for (Size s : sizes) {
            if (size_s.equalsIgnoreCase(s.size_s)) {
                return s;
            }
        }
        return null;
    }
}

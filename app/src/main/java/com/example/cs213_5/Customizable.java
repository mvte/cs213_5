package com.example.cs213_5;

/**
 * The Customizable Interface contains the add and remove abstract methods that are
 * implemented in the StoreOrder and Pizza classes.
 * @author Jan Marzan, Brian Zhang
 */
public interface Customizable {
    /** adds an object */
    boolean add(Object obj);
    /** removes an object */
    boolean remove(Object obj);
}

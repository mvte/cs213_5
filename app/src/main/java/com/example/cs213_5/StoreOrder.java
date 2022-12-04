package com.example.cs213_5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class stores all the orders that a store has. The orders are held in an
 * observable list, which can be used to update the GUI in real time. This class
 * contains methods to add and remove orders from the list.
 * @author Jan Marzan, Brian Zhang
 */
public class StoreOrder implements Customizable{
    /** Observable list of orders that has been placed at the Store. */
    private ObservableList<Order> orders;

    /** Initializes an empty list of orders. */
    public StoreOrder() {
        orders = FXCollections.observableArrayList();
    }

    /**
     * Gets the list of orders
     * @return The list of orders
     */
    public ObservableList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order to the list of orders.
     * @param obj The order to be added
     * @return True if the order was successfully added, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(!(obj instanceof Order)) {
            return false;
        }

        return orders.add((Order)obj);
    }

    /**
     * Removes an order from the list of orders.
     * @param obj The order to be removed.
     * @return True if the order was able to removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(!(obj instanceof Order)) {
            return false;
        }

        return orders.remove((Order)obj);
    }
}

package com.example.cs213_5;

import java.util.ArrayList;

/**
 * This class represents an order made by the employees of RU Pizzeria. The pizzas
 * are held in an observable list which can be used to update the GUI in real time,
 * which also has a listener that will update the the subtotal whenever the array
 * changes. An order will automatically assign itself an ID on construction.
 * This class contains methods to add and remove pizzas, and methods to inquire
 * about the details of the order.
 * @author Jan Marzan, Brian Zhang
 */
public class Order implements Customizable{
    /** Sales tax rate in NJ */
    private final double SALES_TAX = 0.06625;
    /** Used to calculate an order's ID */
    private static int id_ref = 10000;
    /** The list of pizzas contained in the order. */
    private ArrayList<Pizza> pizzas;
    /** The order's unique id. */
    private int id;
    /** The order's subtotal (before tax) */
    private double subtotal;

    /**
     * Creates an empty list of pizzas, assigns this order an ID, and registers a
     * listener to the list of pizzas.
     */
    public Order() {
        pizzas = new ArrayList<>();
        id = id_ref++;
    }

    /**
     * Adds a pizza to the list of orders.
     * @param obj The pizza to be added.
     * @return True if the pizza was successfully added to the list, false otherwise.
     */
    @Override
    public boolean add(Object obj) {
        if(!(obj instanceof Pizza)) {
            return false;
        }
        Pizza pizza =  (Pizza)obj;

        return pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the list of orders.
     * @param obj The pizza to be removed.
     * @return True if the pizza was removed, false otherwise.
     */
    @Override
    public boolean remove(Object obj) {
        if(!(obj instanceof Pizza)) {
            return false;
        }
        Pizza pizza = (Pizza)obj;

        return pizzas.remove(pizza);
    }

    /**
     * Gets the list of pizzas in the order.
     * @return The list of Pizzas as an observable list.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Calculates the order's subtotal.
     * @return The subtotal of the order.
     */
    public double calculateSubtotal() {
        double subtotal = 0;
        for(Pizza p : pizzas) {
            subtotal += p.price();
        }
        return subtotal;
    }

    /**
     * Gets the order's subtotal.
     * @return The subtotal of the order.
     */
    public double getSubtotal() {
        subtotal = calculateSubtotal();
        return subtotal;
    }

    /**
     * Computes the prices of the order's sales tax.
     * @return The sales tax due.
     */
    public double getSalesTax() {
        return subtotal * SALES_TAX;
    }

    /**
     * Computes the total cost of the order.
     * @return The total cost of the order.
     */
    public double getTotal() {
        return subtotal + getSalesTax();
    }

    /**
     * Formats the cost of the order as a currency amount (e.g $12.34).
     * @return The formatted string of the order cost.
     */
    public String getTotalFormatted() {
        return String.format("$%.2f", getTotal());
    }

    /**
     * Gets the order's ID.
     * @return The id of this order.
     */
    public int getId() {
        return id;
    }

    /**
     * Formats the order into a string. The first line consists of the order ID.
     * The subsequent lines will consist of each pizza. The last line will consist
     * of the order total.
     * @return The formatted string of this order.
     */
    @Override
    public String toString() {
        String orderStr = "Order #" + id + "\n" + getOrderDetailsAsString() +
                "Total: " + getTotalFormatted();
        return orderStr;
    }

    /**
     * Formats the details of the order as a string. Essentially, a string
     * containing all the pizzas in the order.
     * @return The formatted string of this order's details.
     */
    public String getOrderDetailsAsString() {
        StringBuilder result = new StringBuilder();
        for(Pizza p : pizzas) {
            result.append(p).append("\n");
        }
        return result.toString();
    }
}

package com.example.cs213_5;

/**
 * The PizzaFactory interface allows for the customization of Pizza objects
 * depending on the type of Pizza that a user wants to create.
 * @author Jan Marzan, Brian Zhang
 */
public interface PizzaFactory {
    /** Creates a Deluxe Pizza */
    Pizza createDeluxe();

    /** Creates a Meatzza Pizza */
    Pizza createMeatzza();

    /** Creates a BBQ Chicken Pizza */
    Pizza createBBQChicken();

    /** Creates a Build Your Own Pizza */
    Pizza createBuildYourOwn();
}

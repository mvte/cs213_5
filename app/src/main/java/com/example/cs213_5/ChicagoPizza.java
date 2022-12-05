package com.example.cs213_5;

/**
 * The Chicago Pizza Factory defers instantiation of Chicago Pizzas to the
 * 4 subclasses representing each pizza flavor.
 * @author Jan Marzan, Brian Zhang
 */
public class ChicagoPizza implements PizzaFactory {

    /** Creates a Chicago Pizza with the Deluxe flavor. */
    @Override
    public Pizza createDeluxe() {
        Pizza deluxe = new Deluxe(Crust.DEEP_DISH, "Chicago");
        return deluxe;
    }

    /** Creates a Chicago Pizza with the Meatzza flavor */
    @Override
    public Pizza createMeatzza() {
        Pizza meatzza = new Meatzza(Crust.STUFFED, "Chicago");
        return meatzza;
    }

    /** Creates a Chicago Pizza with the BBQ Chicken flavor */
    @Override
    public Pizza createBBQChicken() {
        Pizza bbqChicken = new BBQ_Chicken(Crust.PAN, "Chicago");
        return bbqChicken;
    }

    /** Creates a Chicago Pizza with the Build Your Own flavor */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza byo = new BuildYourOwn(Crust.PAN, "Chicago");
        return byo;
    }
}

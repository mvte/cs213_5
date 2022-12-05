package com.example.cs213_5;

/**
 * The NY Pizza Factory defers instantiation of NY Pizzas to the
 * 4 subclasses representing each pizza flavor.
 * @author Jan Marzan, Brian Zhang
 */
public class NYPizza implements PizzaFactory {

    /** Creates a NY Pizza with the Deluxe flavor and Brooklyn crust. */
    @Override
    public Pizza createDeluxe() {
        Pizza deluxe = new Deluxe(Crust.BROOKLYN, "NY");
        return deluxe;
    }

    /** Creates a NY Pizza with the Meatzza flavor and Thin crust. */
    @Override
    public Pizza createMeatzza() {
        Pizza meatzza = new Meatzza(Crust.THIN, "NY");
        return meatzza;
    }

    /** Creates a NY Pizza with the BBQ Chicken flavor and Hand-tossed crust. */
    @Override
    public Pizza createBBQChicken() {
        Pizza bbqchicken = new BBQ_Chicken(Crust.HAND_TOSSED, "NY");
        return bbqchicken;
    }

    /** Creates a NY Pizza with the Build Your Own flavor and Hand-tossed crust. */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza byo = new BuildYourOwn(Crust.HAND_TOSSED, "NY");
        return byo;
    }
}

package com.example.cs213_5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The NewYorkFragment class displays all information regarding the New York Pizza being created and allows the user
 * to modify the pizza. The information regarding the New York Pizza includes the flavor, size, toppings (if
 * applicable), crust, and the price of the pizza.
 * @author Jan Marzan, Brian Zhang
 */
public class NewYorkFragment extends Fragment {

    /** The Image View that holds the pizza photo */
    private ImageView pizzaImg;

    /** Spinners that contain preset user options for pizza flavor and pizza size */
    private Spinner flavors, size;

    /** A list of preset user options for pizza toppings */
    private ListView availableToppings;

    /** Button to add current pizza to current order */
    private Button orderButton;

    /** Non-user-editable textboxes that display current price of pizza and the current crust type depending on flavor selection */
    private TextView currPrice, crustType;

    /** The factory that makes New York Pizzas */
    private PizzaFactory nyFactory = new NYPizza();
    /** The pizza to be created */
    private Pizza pizza;

    /**
     * A required empty public constructor to build the fragment.
     */
    public NewYorkFragment() {
        // Required empty public constructor
    }

    /**
     * Loads the previous instance state of the fragment, if saved from last use.
     * @param savedInstanceState a bundle storing the previous state of the fragment activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates the ChicagoFragment view and displays the Fragment
     * @param inflater the inflater that instantiates the contents of a specified XML file
     * @param container the container holding the view items
     * @param savedInstanceState a bundle storing the previous state of the fragment activity
     * @return the created ChicagoFragment view built from XML
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_york, container, false);
        orderButton = view.findViewById(R.id.orderButton);
        currPrice = view.findViewById(R.id.ny_price);
        crustType = view.findViewById(R.id.crustType);
        availableToppings = view.findViewById(R.id.availableToppings);
        ArrayAdapter<CharSequence> toppingsAdapter = ArrayAdapter.createFromResource(getContext(), R.array.toppings_array, android.R.layout.simple_list_item_multiple_choice);
        availableToppings.setAdapter(toppingsAdapter);
        availableToppings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        setupToppingsSelectListener();
        size = view.findViewById(R.id.size);
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.size_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sizeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        size.setAdapter(sizeAdapter);
        flavors = view.findViewById(R.id.flavors);
        ArrayAdapter<CharSequence> flavorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.flavor_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        flavorAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        flavors.setAdapter(flavorAdapter);
        pizzaImg = view.findViewById(R.id.pizzaImage);
        orderButton.setOnClickListener(onOrderClick);
        size.setOnItemSelectedListener(onSizeSelect);
        flavors.setOnItemSelectedListener(onFlavorSelect);
        pizza = createPizza("Build Your Own", Size.SMALL.getIntSize());
        currPrice.setText(String.valueOf(pizza.price()));
        crustType.setText(R.string.hand_tossed_crust);
        // Inflate the layout for this fragment
        return view;
    }

    /**
     * Creates a listener for Toppings Selection. Prevents the user from selecting more than 7
     * toppings if "Build Your Own" Pizza is selected.
     */
    private void setupToppingsSelectListener() {
        availableToppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Topping topp = Topping.getTopping(availableToppings.getAdapter().getItem(position).toString());
                if(availableToppings.isItemChecked(position)) {
                    if(!pizza.add(topp)) {
                        Toast.makeText(getActivity(), "Too many toppings selected, maximum 7", Toast.LENGTH_SHORT).show();
                        availableToppings.setItemChecked(position, false);
                    }
                } else {
                    if(!pizza.remove(topp)) {
                        Log.e("ChicagoFragment", "unable to deselect item!");
                    }
                }
                currPrice.setText(String.format("$%.2f", pizza.price()));
            }
        });
    }

    /**
     * A listener that adds the current pizza, in its current build state, to the current order.
     */
    private View.OnClickListener onOrderClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Pizza added to order", Toast.LENGTH_SHORT).show();
            addToOrder();
        }
    };

    /**
     * A listener that changes the current pizza size, depending on the selection in the Size spinner.
     * Updates the price to reflect change in size.
     */
    private AdapterView.OnItemSelectedListener onSizeSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            String selected = (String) adapterView.getItemAtPosition(position);
            String flavorSelected = (String) flavors.getSelectedItem();
            if (selected.equalsIgnoreCase("Small")) {
                pizza.setSize(Size.SMALL);
            }
            else if (selected.equalsIgnoreCase("Medium")) {
                pizza.setSize(Size.MEDIUM);
            }
            else if (selected.equalsIgnoreCase("Large")) {
                pizza.setSize(Size.LARGE);
            }

            currPrice.setText(String.format("$%.2f", pizza.price()));
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    /**
     * A listener that changes the current pizza flavor, depending on the selection in the Flavor spinner.
     * Updates the price to reflect change in flavor.
     */
    private AdapterView.OnItemSelectedListener onFlavorSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selected = (String) adapterView.getItemAtPosition(i);
            int selectedSize = size.getSelectedItemPosition();
            if (selected.equalsIgnoreCase("Build Your Own")) {
                pizzaImg.setImageResource(R.drawable.ny_byo);
                pizza = createPizza("Build Your Own", selectedSize);
                crustType.setText(R.string.hand_tossed_crust);
                availableToppings.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.toppings_array, android.R.layout.simple_list_item_multiple_choice));
            }
            else if (selected.equalsIgnoreCase("Deluxe")) {
                pizzaImg.setImageResource(R.drawable.ny_deluxe);
                pizza = createPizza("Deluxe", selectedSize);
                crustType.setText(R.string.brooklyn_crust);
                availableToppings.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.deluxe_toppings, android.R.layout.simple_list_item_1));
            }
            else if (selected.equalsIgnoreCase("BBQ Chicken")) {
                pizzaImg.setImageResource(R.drawable.ny_bbqchicken);
                pizza = createPizza("BBQ Chicken", selectedSize);
                crustType.setText(R.string.thin_crust);
                availableToppings.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.BBQ_chicken_toppings, android.R.layout.simple_list_item_1));
            }
            else {
                pizzaImg.setImageResource(R.drawable.ny_meatzza);
                pizza = createPizza("Meatzza", selectedSize);
                crustType.setText(R.string.hand_tossed_crust);
                availableToppings.setAdapter(ArrayAdapter.createFromResource(getContext(), R.array.meatzza_toppings, android.R.layout.simple_list_item_1));
            }
            currPrice.setText(String.format("$%.2f", pizza.price()));
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    /**
     * Creates a pizza based on the selected flavor and size.
     * @param pizzaType the flavor of pizza to be created
     * @param size_n the size of the pizza to be created
     * @return create, the Pizza that is created
     */
    public Pizza createPizza(String pizzaType, int size_n) {
        Pizza create = null;
        Size size = Size.getSize(size_n);
        if (pizzaType.equalsIgnoreCase("Build Your Own")) {
            create = nyFactory.createBuildYourOwn();
            create.setSize(size);
        }
        else if (pizzaType.equalsIgnoreCase("Deluxe")) {
            create = nyFactory.createDeluxe();
            create.setSize(size);
        }
        else if (pizzaType.equalsIgnoreCase("BBQ Chicken")) {
            create = nyFactory.createBBQChicken();
            create.setSize(size);
        }
        else if (pizzaType.equalsIgnoreCase("Meatzza")){
            create = nyFactory.createMeatzza();
            create.setSize(size);
        }
        return create;
    }

    /**
     * Adds a pizza to the current order.
     */
    private void addToOrder() {
        MainActivity.currentOrder.add(pizza);

        pizza = createPizza("Build Your Own", Size.SMALL.getIntSize());
        resetFields();
    }

    /**
     * Resets all fields in the Fragment to their default values.
     */
    private void resetFields() {
        size.setSelection(0);
        flavors.setSelection(0);
        crustType.setText(R.string.hand_tossed_crust);
        for(int i = 0; i < Topping.values().length; i++) {
            availableToppings.setItemChecked(i, false);
        }
    }
}
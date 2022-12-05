package com.example.cs213_5;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import android.widget.*;
import android.R.layout;

import java.sql.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChicagoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChicagoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView pizzaImg;
    private Spinner flavors, size;
    private ListView availableToppings, selectedToppings;
    private Button orderButton;
    private TextView currPrice;

    /** the factory that makes Chicago Pizzas */
    private PizzaFactory chicagoFactory = new ChicagoPizza();
    /** the pizza to be created */
    private Pizza pizza;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChicagoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChicagoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChicagoFragment newInstance(String param1, String param2) {
        ChicagoFragment fragment = new ChicagoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chicago, container, false);
        orderButton = view.findViewById(R.id.orderButton);
        currPrice = view.findViewById(R.id.chicago_price);
        availableToppings = view.findViewById(R.id.availableToppings);
        ArrayAdapter<CharSequence> toppingsAdapter = ArrayAdapter.createFromResource(getContext(), R.array.toppings_array, layout.simple_list_item_1);
        availableToppings.setAdapter(toppingsAdapter);

        selectedToppings = view.findViewById(R.id.selectedToppings);

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

        pizza = createPizza("Build Your Own");
        pizza.setSize(Size.SMALL);
        currPrice.setEnabled(false);
        currPrice.setText(String.valueOf(pizza.price()));

        ArrayAdapter<CharSequence> selectedToppingsAdapter = new ArrayAdapter(getContext(), layout.simple_list_item_1, pizza.getToppings());
        selectedToppings.setAdapter(selectedToppingsAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    private View.OnClickListener onOrderClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Pizza added to order", Toast.LENGTH_SHORT).show();
            addToOrder();
        }
    };

    private AdapterView.OnItemSelectedListener onSizeSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            String selected = (String) adapterView.getItemAtPosition(position);
            String flavorSelected = (String) flavors.getSelectedItem();
            pizza = createPizza(flavorSelected);
            if (selected.equalsIgnoreCase("Small")) {
                pizza.setSize(Size.SMALL);
                currPrice.setText(String.valueOf(pizza.price()));
            }
            else if (selected.equalsIgnoreCase("Medium")) {
                pizza.setSize(Size.MEDIUM);
                currPrice.setText(String.valueOf(pizza.price()));
            }
            else if (selected.equalsIgnoreCase("Large")) {
                pizza.setSize(Size.LARGE);
                currPrice.setText(String.valueOf(pizza.price()));
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    private AdapterView.OnItemSelectedListener onFlavorSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selected = (String) adapterView.getItemAtPosition(i);
            String selectedSize = (String) size.getSelectedItem();
            Size s = Size.getSize(selectedSize);
            if (selected.equalsIgnoreCase("Build Your Own")) {
                pizzaImg.setImageResource(R.drawable.chicago_byo);
                pizza = createPizza("Build Your Own");
                availableToppings.setEnabled(true);
            }
            else if (selected.equalsIgnoreCase("Deluxe")) {
                pizzaImg.setImageResource(R.drawable.chicago_deluxe);
                pizza = createPizza("Deluxe");
                availableToppings.setEnabled(false);
            }
            else if (selected.equalsIgnoreCase("BBQ Chicken")) {
                pizzaImg.setImageResource(R.drawable.chicago_bbqchicken);
                pizza = createPizza("BBQ Chicken");
                availableToppings.setEnabled(false);
            }
            else {
                pizzaImg.setImageResource(R.drawable.chicago_meatzza);
                pizza = createPizza("Meatzza");
                availableToppings.setEnabled(false);
            }
            pizza.setSize(s);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    public Pizza createPizza(String pizzaType) {
        Pizza create;
        if (pizzaType.equalsIgnoreCase("Build Your Own")) {
            return chicagoFactory.createBuildYourOwn();
        }
        else if (pizzaType.equalsIgnoreCase("Deluxe")) {
            return chicagoFactory.createDeluxe();

        }
        else if (pizzaType.equalsIgnoreCase("BBQ Chicken")) {
            return chicagoFactory.createBBQChicken();
        }
        else if (pizzaType.equalsIgnoreCase("Meatzza")){
            return chicagoFactory.createMeatzza();
        }
        return null;
    }

    //TODO: implement this (currently a placeholder to test order recyclerview)
    public void addToOrder() {
        int rand = (int)(Math.random()*4+1);
        Pizza pizza = createPizza("Build Your Own");
        if(rand==2) pizza = createPizza("Deluxe");
        if(rand==3) pizza = createPizza("BBQ Chicken");
        if(rand==4) pizza = createPizza("Meatzza");

        pizza.setSize(Size.MEDIUM);
        MainActivity.currentOrder.add(pizza);
    }
}
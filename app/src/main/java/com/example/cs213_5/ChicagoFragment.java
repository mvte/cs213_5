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
        availableToppings = view.findViewById(R.id.availableToppings);
        selectedToppings = view.findViewById(R.id.selectedToppings);

        size = view.findViewById(R.id.size);
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.size_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sizeAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        size.setAdapter(sizeAdapter);

        flavors = view.findViewById(R.id.flavors);
        ArrayAdapter<CharSequence> flavorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.flavor_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        flavorAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        size.setAdapter(flavorAdapter);

        pizzaImg = view.findViewById(R.id.pizzaImage);

        orderButton.setOnClickListener(onOrderClick);
        size.setOnItemSelectedListener(onSizeSelect);
        // Inflate the layout for this fragment
        return view;
    }

    private View.OnClickListener onOrderClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Pizza added to order", Toast.LENGTH_SHORT).show();
        }
    };

    private AdapterView.OnItemSelectedListener onSizeSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    Toast.makeText(getActivity(), "Small size selected", Toast.LENGTH_SHORT).show();
                case 1:
                    Toast.makeText(getActivity(), "Medium size selected", Toast.LENGTH_SHORT).show();
                case 2:
                    Toast.makeText(getActivity(), "Large size selected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };

    private AdapterView.OnItemSelectedListener onFlavorSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    Toast.makeText(getActivity(), "Build Your Own selected", Toast.LENGTH_SHORT).show();
                case 1:
                    Toast.makeText(getActivity(), "Deluxe selected", Toast.LENGTH_SHORT).show();
                case 2:
                    Toast.makeText(getActivity(), "BBQ Chicken selected", Toast.LENGTH_SHORT).show();
                case 3:
                    Toast.makeText(getActivity(), "Meatzza selected", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
    };
}
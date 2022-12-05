package com.example.cs213_5;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class OrderFragment extends Fragment {
    //TODO: add functionality to buttons, fix prices not updating

    private Order order;

    private RecyclerView recyclerView;
    private TextView tvEmpty, tvSubtotal, tvTax, tvTotal;
    private EditText etId;


    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        order = MainActivity.currentOrder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        tvEmpty = view.findViewById(R.id.tv_empty_order);
        if(order.getPizzas().isEmpty()) {
            tvEmpty.setText(R.string.empty_order_text);
        }

        //set up order id
        etId = view.findViewById(R.id.order_id);
        etId.setText(String.valueOf(order.getId()));

        //set up recyclerview
        recyclerView = view.findViewById(R.id.orderRecyclerView);
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(requireActivity(), order);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        //set up order amounts
        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvTax = view.findViewById(R.id.tvTax);
        tvTotal = view.findViewById(R.id.tvTotal);
        tvSubtotal.setText(R.string.subtotal);
        tvSubtotal.append(": " + String.format("$%.2f", order.getSubtotal()));
        tvTax.setText(R.string.taxes);
        tvTax.append(": " + String.format("$%.2f", order.getSalesTax()));
        tvTotal.setText(R.string.total);
        tvTotal.append(": " + String.format("$%.2f", order.getTotal()));

        return view;
    }

    public void onPlaceOrder() {

    }

    public void onClearOrder() {

    }
}
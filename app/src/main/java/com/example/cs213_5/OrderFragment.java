package com.example.cs213_5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderFragment extends Fragment implements RecyclerViewInterface{

    private Order order;
    private ArrayList<Pizza> pizzas;

    private RecyclerView recyclerView;
    private TextView tvEmpty, tvSubtotal, tvTax, tvTotal;
    private EditText etId;
    private Button place, clear;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        order = MainActivity.currentOrder;
        pizzas = order.getPizzas();
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

        setupOrderId(view);
        setupRecyclerView(view);
        setupOrderAmounts(view);
        setupButtonListeners(view);

        return view;
    }

    private void setupOrderId(View view) {
        etId = view.findViewById(R.id.order_id);
        etId.setText(String.valueOf(order.getId()));
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.orderRecyclerView);
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(requireActivity(), order, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private void setupOrderAmounts(View view) {
        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvTax = view.findViewById(R.id.tvTax);
        tvTotal = view.findViewById(R.id.tvTotal);
        updatePrice();
    }

    private void setupButtonListeners(View view) {
        clear = view.findViewById(R.id.clearOrderButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOrder(true);
            }
        });

        place = view.findViewById(R.id.placeOrderButton);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
    }

    private void updatePrice() {
        tvSubtotal.setText(R.string.subtotal);
        tvSubtotal.append(": " + String.format("$%.2f", order.getSubtotal()));
        tvTax.setText(R.string.taxes);
        tvTax.append(": " + String.format("$%.2f", order.getSalesTax()));
        tvTotal.setText(R.string.total);
        tvTotal.append(": " + String.format("$%.2f", order.getTotal()));
    }

    public void placeOrder() {
        if(pizzas.isEmpty()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.empty_order_text), Toast.LENGTH_SHORT).show();
            return;
        }

        MainActivity.storeOrder.add(order);
        MainActivity.currentOrder = new Order();
        order = MainActivity.currentOrder;
        pizzas = order.getPizzas();

        etId.setText(String.valueOf(order.getId()));

        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(requireActivity(), order, this);
        recyclerView.setAdapter(adapter);
        recyclerView.forceLayout();
        updatePrice();
        clearOrder(false);

        Toast.makeText(getActivity(), getResources().getString(R.string.order_success), Toast.LENGTH_SHORT).show();
    }

    public void clearOrder(boolean showToast) {
        pizzas.clear();
        recyclerView.requestLayout();
        updatePrice();
        if(showToast) {
            Toast.makeText(getActivity(), getResources().getString(R.string.order_clear), Toast.LENGTH_SHORT).show();
        }
        tvEmpty.setText(R.string.empty_order_text);
    }

    /**
     * When a pizza from the Order RecyclerView is selected, this method is called to allow the user
     * to confirm or deny the removal of the selected pizza through an AlertDialog.
     * @param position the position of the pizza in the Recycler View.
     */
    @Override
    public void onItemClick(int position) {
        Pizza pizza = pizzas.get(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setMessage("Do you want to remove this " + pizza.size + " " + pizza.name + "?").setTitle("Remove Pizza");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                pizzas.remove(position);
                recyclerView.getAdapter().notifyItemRemoved(position);
                updatePrice();

                if (pizzas.isEmpty()) {
                    tvEmpty.setText(R.string.empty_order_text);
                }
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
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

/**
 * The OrderFragment class displays all information regarding the current order and allows the user
 * to edit the order. The information regarding the order includes the Order ID, all the pizzas
 * in the order, and the prices of the order (subtotal, tax, total). The user can remove a pizza
 * or clear all the pizzas in the order.
 * @author Jan Marzan, Brian Zhang
 */
public class OrderFragment extends Fragment implements RecyclerViewInterface{
    /** The order whose information is to be displayed */
    private Order order;
    /** The ArrayList of pizzas from the order */
    private ArrayList<Pizza> pizzas;
    /** The RecyclerView that displays information about all the pizzas in an order */
    private RecyclerView recyclerView;
    /** TextViews related to the prices */
    private TextView tvEmpty, tvSubtotal, tvTax, tvTotal;
    /** An non-editable EditText that displays the Order ID*/
    private EditText etId;
    /** Buttons that allow the user to place or clear the order */
    private Button place, clear;

    /**
     * Required empty constructor for a fragment
     */
    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Starts the Order Fragment
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        order = MainActivity.currentOrder;
        pizzas = order.getPizzas();
    }

    /**
     * Instantiates the fragment's user interface view
     * @param inflater the layout inflater
     * @param container the view group container
     * @param savedInstanceState the saved instanced state
     * @return the created view
     */
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

    /**
     * Displays the order's current ID in the order id in the given view.
     * @param view the associated parent view
     */
    private void setupOrderId(View view) {
        etId = view.findViewById(R.id.order_id);
        etId.setText(String.valueOf(order.getId()));
    }

    /**
     * Sets up the order RecyclerView and binds the order to the view.
     * @param view the associated parent view
     */
    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.orderRecyclerView);
        OrderRecyclerViewAdapter adapter = new OrderRecyclerViewAdapter(requireActivity(), order, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    /**
     * Sets up the order's price views.
     * @param view the associated parent view.
     */
    private void setupOrderAmounts(View view) {
        tvSubtotal = view.findViewById(R.id.tvSubtotal);
        tvTax = view.findViewById(R.id.tvTax);
        tvTotal = view.findViewById(R.id.tvTotal);
        updatePrice();
    }

    /**
     * Defines functionality for the clear order and place order button.
     * @param view the associated parent view
     */
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

    /**
     * Updates the price views with the order's current prices.
     */
    private void updatePrice() {
        tvSubtotal.setText(R.string.subtotal);
        tvSubtotal.append(": " + String.format("$%.2f", order.getSubtotal()));
        tvTax.setText(R.string.taxes);
        tvTax.append(": " + String.format("$%.2f", order.getSalesTax()));
        tvTotal.setText(R.string.total);
        tvTotal.append(": " + String.format("$%.2f", order.getTotal()));
    }

    /**
     * Places the current order by adding it to the store order. If there are no pizzas in the
     * current order, the order will not be placed.
     */
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

    /**
     * Clears the order of all pizzas.
     * @param showToast whether or not a confirmation toast should be shown
     */
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
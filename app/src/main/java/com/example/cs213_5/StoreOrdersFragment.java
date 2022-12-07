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
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 */
public class StoreOrdersFragment extends Fragment implements RecyclerViewInterface {
    /** The associated Store Order object*/
    private StoreOrder storeOrder;
    /** The ArrayList containing all of the Store's Orders*/
    private ArrayList<Order> orders;
    /** The RecyclerView containing all the store's orders*/
    RecyclerView recyclerView;
    TextView tvEmpty;

    /**
     * Required empty constructor
     */
    public StoreOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Starts the StoreOrders Fragment
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.storeOrder = MainActivity.storeOrder;
        this.orders = storeOrder.getOrders();
    }

    /**
     * Instantiates the fragment's user interface view
     * @param inflater the layout inflater
     * @param container the view group container
     * @param savedInstanceState the saved instanced state
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_orders, container, false);

        tvEmpty = view.findViewById(R.id.tvEmptyOrders);
        if(!orders.isEmpty()) {
            tvEmpty.setText("");
        }

        recyclerView = view.findViewById(R.id.storeOrdersRecycler);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireActivity(), storeOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return view;
    }

    /**
     * Prompts the user to confirm cancellation of the clicked order item
     * @param position the position of the item being clicked
     */
    @Override
    public void onItemClick(int position) {
        Order order = orders.get(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setMessage("Do you want to Cancel Order #" + order.getId() + "?").setTitle("Cancel Order");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                orders.remove(position);
                recyclerView.getAdapter().notifyItemRemoved(position);

                if (orders.isEmpty()) {
                    tvEmpty.setText(R.string.empty_store_orders_text);
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
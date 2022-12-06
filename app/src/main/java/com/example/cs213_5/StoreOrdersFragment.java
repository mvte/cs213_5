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

import java.util.ArrayList;

/**
 *
 */
public class StoreOrdersFragment extends Fragment implements RecyclerViewInterface {
    private StoreOrder storeOrder;
    private ArrayList<Order> orders;

    RecyclerView recyclerView;

    public StoreOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.storeOrder = MainActivity.storeOrder;
        this.orders = storeOrder.getOrders();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_orders, container, false);

        recyclerView = view.findViewById(R.id.storeOrdersRecycler);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireActivity(), storeOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Order order = orders.get(position);

        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setMessage("Do you want to remove Order #" + order.getId() + "?").setTitle("Remove Order");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                orders.remove(position);
                recyclerView.getAdapter().notifyItemRemoved(position);

                if (orders.isEmpty()) {
                    //TODO: implement empty text
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
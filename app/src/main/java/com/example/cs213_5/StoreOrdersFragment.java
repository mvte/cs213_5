package com.example.cs213_5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class StoreOrdersFragment extends Fragment implements RecyclerViewInterface {
    private StoreOrder storeOrder;

    public StoreOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.storeOrder = MainActivity.storeOrder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_orders, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.storeOrdersRecycler);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireActivity(), storeOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return view;
    }

    //TODO: should create a new OrderFragment in flFragment with param PREVIEW_ONLY
    @Override
    public void onItemClick(int position) {
        Log.e("StoreOrdersFragment", storeOrder.getOrders().get(position).toString());
    }
}
package com.example.cs213_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.MyViewHolder> {
    /** Needed for inflating layout **/
    private Context context;
    private ArrayList<Order> orders;
    private RecyclerViewInterface rvInterface;

    public StoreRecyclerViewAdapter(Context context, StoreOrder storeOrders, RecyclerViewInterface rvInterface) {
        this.context = context;
        this.orders = storeOrders.getOrders();
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_card, parent, false);

        return new StoreRecyclerViewAdapter.MyViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText("#" + orders.get(position).getId());
        holder.tvContents.setText(orders.get(position).getPizzas().size() + " pizzas");
        holder.tvPrice.setText(String.format("$%.2f", orders.get(position).getTotal()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContents, tvPrice;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface rvInterface) {
            super(itemView);

            tvName = itemView.findViewById(R.id.orderIdLabel);
            tvContents = itemView.findViewById(R.id.orderPizzaAmount);
            tvPrice = itemView.findViewById(R.id.orderPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rvInterface != null) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION) {
                            rvInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

package com.example.cs213_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.MyViewHolder> {
    /** Needed for inflating layout **/
    private Context context;
    private ArrayList<Pizza> pizzas;
    private RecyclerViewInterface rvInterface;

    public OrderRecyclerViewAdapter(Context context, Order order, RecyclerViewInterface rvInterface) {
        this.context = context;
        this.pizzas = order.getPizzas();
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pizza_card, parent, false);

        return new OrderRecyclerViewAdapter.MyViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(pizzas.get(position).name);
        holder.tvSize.setText(pizzas.get(position).size.toString());
        holder.tvPrice.setText(String.format("$%.2f", pizzas.get(position).price()));

        String toppingsString = "";
        ArrayList<Topping> toppings = pizzas.get(position).getToppings();
        for(int i = 0; i < toppings.size(); i++) {
            String end = i == toppings.size()-1? "" : ", ";
            end += (i+1)%3 == 0 && i != toppings.size()-1 ? "\n" : "";
            toppingsString += toppings.get(i) + end;
        }

        holder.tvContents.setText(toppingsString);
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSize, tvContents, tvPrice;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface rvInterface) {
            super(itemView);

            tvName = itemView.findViewById(R.id.pizza_name);
            tvSize = itemView.findViewById(R.id.pizza_size);
            tvContents = itemView.findViewById(R.id.pizza_toppings);
            tvPrice = itemView.findViewById(R.id.pizza_price);

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

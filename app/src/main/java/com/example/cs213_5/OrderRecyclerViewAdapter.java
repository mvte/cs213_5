package com.example.cs213_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * The OrderRecyclerViewAdapter provides the means of binding individual pizza data of an order
 * to views that will be displayed in the Order RecyclerView.
 * @author Jan Marzan, Brian Zhang
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.MyViewHolder> {
    /** Needed for inflating layout **/
    private Context context;
    /** ArrayList of Pizzas */
    private ArrayList<Pizza> pizzas;
    /** The RecyclerViewInterface to be implemented */
    private RecyclerViewInterface rvInterface;

    /**
     * Constructor for OrderRecyclerViewAdapter
     * @param context the context of the RecyclerView
     * @param order the order whose pizzas' data will be displayed
     * @param rvInterface the RecyclerView interface to be implemented
     */
    public OrderRecyclerViewAdapter(Context context, Order order, RecyclerViewInterface rvInterface) {
        this.context = context;
        this.pizzas = order.getPizzas();
        this.rvInterface = rvInterface;
    }

    /**
     * Creates a new ViewHolder.
     * @param parent The parent ViewGroup
     * @param viewType the view type constant
     * @return the created ViewHolder object
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pizza_card, parent, false);

        return new OrderRecyclerViewAdapter.MyViewHolder(view, rvInterface);
    }

    /**
     * Binds the data of a pizza to the the View Holder.
     * @param holder the view holder of the data
     * @param position the position of the pizza in the array to be displayed
     */
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

    /**
     * Gets the amount of items in the pizzas array.
     * @return the amount of items in the pizzas array.
     */
    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    /**
     * Represents the View Holder which will display all the information related to an individual pizza.
     * @author Jan Marzan, Brian Zhang
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        /** TextViews to display information about a pizza's name, size, toppings, and price, respectively. */
        TextView tvName, tvSize, tvContents, tvPrice;

        /**
         * Constructor that binds view objects to their respective views and the parent view to an
         * onClick listener.
         * @param itemView the item view
         * @param rvInterface the RecyclerViewInterface to be implemented
         */
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

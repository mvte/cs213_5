package com.example.cs213_5;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
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
    /** ArrayList of orders */
    private ArrayList<Order> orders;
    /** The interface to be implemented */
    private RecyclerViewInterface rvInterface;

    /**
     * Constructor for StoreRecyclerViewAdapter
     * @param context the context of the RecyclerView
     * @param storeOrders the StoreOrder instance to be represented
     * @param rvInterface the RecyclerViewInterface to be implemented
     */
    public StoreRecyclerViewAdapter(Context context, StoreOrder storeOrders, RecyclerViewInterface rvInterface) {
        this.context = context;
        this.orders = storeOrders.getOrders();
        this.rvInterface = rvInterface;
    }

    /**
     * Creates a new View Holder.
     * @param parent The parent ViewGroup
     * @param viewType the view type constant
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_card, parent, false);

        return new StoreRecyclerViewAdapter.MyViewHolder(view, rvInterface);
    }

    /**
     * Binds the data of an order to the the View Holder.
     * @param holder the view holder of the data
     * @param position the position of the order in the array to be displayed
     */
    @Override
    public void onBindViewHolder(@NonNull StoreRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText("Order #" + orders.get(position).getId());
        holder.tvContents.setText(generatePizzaString(orders.get(position)), TextView.BufferType.SPANNABLE);
        holder.tvPrice.setText(String.format("$%.2f", orders.get(position).getTotal()));
    }

    /**
     * Creates a SpannableStringBuilder that contains stylized information of all the pizzas in an
     * order. Each pizza's title (size and name) will be bolded, while the toppings will be normal.
     * @param order
     * @return
     */
    private SpannableStringBuilder generatePizzaString(Order order) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();

        for(int i = 0; i < order.getPizzas().size(); i++) {
            Pizza p = order.getPizzas().get(i);
            StyleSpan boldSpan = new StyleSpan(android.graphics.Typeface.BOLD);
            ssb.append(p.size.toString() +  " " + p.name + "\n", boldSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            String toAppend;
            if(i == order.getPizzas().size()-1) {
                toAppend = generateToppingString(p);
            } else {
                toAppend = generateToppingString(p) + "\n";
            }
            ssb.append(toAppend);
        }

        return ssb;
    }

    /**
     * Creates a formatted string containing all the toppings of a pizza. Each line will have a
     * maximum of three toppings.
     * @param p the pizza whose toppings are to be displayed
     * @return the formatted topping string
     */
    private String generateToppingString(Pizza p) {
        if(p.getToppings().isEmpty()) {
            return "";
        }

        String toppingsString = "";
        ArrayList<Topping> toppings = p.getToppings();
        for(int i = 0; i < toppings.size(); i++) {
            String end = i == toppings.size()-1? "" : ", ";
            end += (i+1)%3 == 0 && i != toppings.size()-1 ? "\n" : "";
            toppingsString += toppings.get(i) + end;
        }

        return toppingsString + "\n";
    }

    /**
     * Gets the amount of items in the orders array.
     * @return the amount of items in the orders array.
     */
    @Override
    public int getItemCount() {
        return orders.size();
    }

    /**
     * Represents the View Holder which will display all the
     * @author Jan Marzan, Brian Zhang
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContents, tvPrice;

        /**
         * Constructor that binds view objects to their respective views and the parent view to an
         * onClick listener.
         * @param itemView the item view
         * @param rvInterface the RecyclerViewInterface to be implemented
         */
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

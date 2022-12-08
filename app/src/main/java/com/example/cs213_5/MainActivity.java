package com.example.cs213_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * The Main Activity serves to provide functionality to the base view of the application, containing
 * the bottom navigation bar and the fragment container.
 * @author Jan Marzan, Brian Zhang
 */
public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    /** The bottom navigation bar displaying all available fragments */
    BottomNavigationView navigation;
    /** The fragment that allows for the creation of Chicago Style pizzas */
    ChicagoFragment chicagoFragment;
    /** The fragment that allows for the creation of New York Style pizzas */
    NewYorkFragment newYorkFragment;
    /** The fragment that allows for the editing and placing of individual orders */
    OrderFragment orderFragment;
    /** The fragment that allows for the viewing of all orders and canceling of individual orders */
    StoreOrdersFragment storeOrdersFragment;
    /** A text view displaying welcome text on first start up */
    TextView welcomeTextView;

    /** The current order */
    public static Order currentOrder = new Order();
    /** The store orders */
    public static StoreOrder storeOrder = new StoreOrder();

    /**
     * Starts the Main Activity. Binds view objects to their respective views in the layout.
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setItemIconTintList(null);
        navigation.setSelectedItemId(R.id.store_orders);
        navigation.setOnItemSelectedListener(this);

        chicagoFragment = new ChicagoFragment();
        newYorkFragment = new NewYorkFragment();
        orderFragment = new OrderFragment();
        storeOrdersFragment = new StoreOrdersFragment();

        welcomeTextView = findViewById(R.id.tvW);

        if(savedInstanceState == null) {
            welcomeTextView.setText(R.string.welcome_text);
        }
    }

    /**
     * Begins fragment transaction for a navigation menu item's respective fragment when selected.
     * @param item the navigation menu item
     * @return true if the fragment transaction was successful
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        welcomeTextView.setText("");

        switch(item.getItemId()) {
            case R.id.chicago:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, chicagoFragment).commit();
                return true;
            case R.id.ny:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, newYorkFragment).commit();
                return true;
            case R.id.store_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, storeOrdersFragment).commit();
                return true;
            case R.id.current_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, orderFragment).commit();
                return true;
        }

        return false;
    }

}
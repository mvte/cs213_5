package com.example.cs213_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView navigation;
    ChicagoFragment chicagoFragment;
    NewYorkFragment newYorkFragment;
    OrderFragment orderFragment;
    StoreOrdersFragment storeOrdersFragment;
    TextView welcomeTextView;

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
    }

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
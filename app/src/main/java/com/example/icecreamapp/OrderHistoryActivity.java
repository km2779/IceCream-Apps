package com.example.icecreamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<OrderItem> orders;
    ArrayList<String> string_orders;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        setTitle("Order History");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        orders = (ArrayList<OrderItem>) i.getSerializableExtra("KEY");
        Log.d("DEBUG", orders.toString());
        string_orders = new ArrayList<>();
        for (OrderItem item: orders)
            string_orders.add(item.toString());

        listview = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
          this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                string_orders
        );
        listview.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

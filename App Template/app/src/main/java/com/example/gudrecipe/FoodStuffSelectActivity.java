package com.example.gudrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FoodStuffSelectActivity extends AppCompatActivity {

    private static final String LOG_TAG = FoodStuffSelectActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final String EXTRA_MESSAGE =
            "com.example.android.GudRecipe.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        setContentView(R.layout.food_select_activity);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
        recyclerView = (RecyclerView) findViewById(R.id.food_select_recycler);
        recyclerView.setHasFixedSize(true);

        //define measurments array for spinner
        String[] food_type_array = getResources().getStringArray(R.array.food_type_list);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        mAdapter = new myAdapter(food_type_array);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String[] measurements_array = getResources().getStringArray(R.array.food_type_list);
                Toast.makeText(getApplicationContext(), measurements_array[position], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                String message = measurements_array[position];
                       intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                // do whatever
            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }}

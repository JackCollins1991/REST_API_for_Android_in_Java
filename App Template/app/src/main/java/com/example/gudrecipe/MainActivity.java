package com.example.gudrecipe;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.PersistableBundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final String EXTRA_MESSAGE =
            "com.example.android.GudRecipe.extra.MESSAGE";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static ArrayList<String> ingredientslist = new ArrayList<String>();
    public static ArrayList<String[]> recipeData = new ArrayList<String[]>();
    public static ArrayList<String> saveState = new ArrayList<String>();
    public static int done = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity instance = this;
        setContentView(R.layout.activity_main);
        android.util.Log.d(LOG_TAG, "creation!");

        // get intent and set the text
        Intent intent = getIntent();
        String food_stuff_message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.food_stuff_display);

        textView.setText(food_stuff_message);
        saveState.add("0");
        saveState.add("0");
        saveState.add("");



        //list view
        final ListView listview = findViewById(R.id.recipe_display);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredientslist);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listview.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,clickedItem,Toast.LENGTH_LONG).show();
                ingredientslist.remove(position);
                recipeData.remove(position);
                android.util.Log.d(LOG_TAG, String.valueOf(ingredientslist));
                android.util.Log.d(LOG_TAG, String.valueOf(recipeData));
                listview.invalidateViews();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set recycler view here
        //recyclerView = (RecyclerView) findViewById(R.id.food_type_input);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        //define measurments array for spinner
        String[] measurements_array = getResources().getStringArray(R.array.Measurements);

        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
       // recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
       // mAdapter = new myAdapter(measurements_array);
       // recyclerView.setAdapter(mAdapter);
        //recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
         //   @Override public void onClick(View view, int position) {
        //        String[] measurements_array = getResources().getStringArray(R.array.Measurements);
        //        Toast.makeText(getApplicationContext(), measurements_array[position], Toast.LENGTH_LONG).show();
        //    }

        //    @Override public void onLongClick(View view, int position) {
                // do whatever
       //     }

        //    @Override
        //    public void onItemClick(View view, int position) {
         //       Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_LONG).show();
         //   }

        //    @Override
        //    public void onLongItemClick(View view, int position) {
//
//            }
//        }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Spinner spin = (Spinner) findViewById(R.id.measurement_type_input);
        spin.setOnItemSelectedListener(this);

//Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, measurements_array );
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        //get saved instance state

            TextView textViewQuantity = findViewById(R.id.quantity_input);
            textViewQuantity.setText (saveState.get(0));
            Spinner spinMeasure = findViewById(R.id.measurement_type_input);
            spinMeasure.setSelection(Integer.parseInt(saveState.get(1)));
            TextView textViewLabel = findViewById(R.id.ingredient_label_input);
            textViewLabel.setText(saveState.get(2));

        TextView textViewFoodStuff = findViewById(R.id.food_stuff_display);
        android.util.Log.d(LOG_TAG, textViewFoodStuff.getText().toString());



    }



@Override
    public void onPause(){
        super.onPause();


        //quantity
        TextView textViewQuantity = findViewById(R.id.quantity_input);
        String quantity = textViewQuantity.getText().toString();
        //measurement type
        Spinner spinMeasure = findViewById(R.id.measurement_type_input);
        int position = spinMeasure.getSelectedItemPosition();
        //label
        TextView textViewLabel = findViewById(R.id.ingredient_label_input);
        String label = textViewLabel.getText().toString();

        //put in intent
        getIntent().putExtra("q", quantity);
        getIntent().putExtra("m", position);
        getIntent().putExtra("l", label);

        android.util.Log.d(LOG_TAG, "pause" + quantity);


    }
    @Override
    public void onResume(){
        super.onResume();

        //get values from bundle
        //TextView textViewQuantity = findViewById(R.id.quantity_input);
        //textViewQuantity.setText (getIntent().getStringExtra("q"));
        //Spinner spinMeasure = findViewById(R.id.measurement_type_input);
        //spinMeasure.setSelection(getIntent().getIntExtra("m",0));
        //TextView textViewLabel = findViewById(R.id.ingredient_label_input);
        //textViewLabel.setText(getIntent().getStringExtra("l"));
        //android.util.Log.d(LOG_TAG, "Resume" + getIntent().getStringExtra("q"));

        //method 2
        //get saved instance state

        TextView textViewQuantity = findViewById(R.id.quantity_input);
        textViewQuantity.setText (saveState.get(0));
        Spinner spinMeasure = findViewById(R.id.measurement_type_input);
        spinMeasure.setSelection(Integer.parseInt(saveState.get(1)));
        TextView textViewLabel = findViewById(R.id.ingredient_label_input);
        textViewLabel.setText(saveState.get(2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Performing action onItemSelected and onNothing selected

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        String[] measurements_array = getResources().getStringArray(R.array.Measurements);
        Toast.makeText(getApplicationContext(), measurements_array[position], Toast.LENGTH_LONG).show();
    }


    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void goToSelect(View view) {
        Intent intent = new Intent(this, FoodStuffSelectActivity.class);
//        String message = "";
 //       intent.putExtra(EXTRA_MESSAGE, message);
 //       android.util.Log.d(LOG_TAG, "Button clicked!");
        startActivity(intent);
    }

    public void addToRecipe(View view) {
        TextView textViewQuantity = findViewById(R.id.quantity_input);
        TextView textViewFoodStuff = findViewById(R.id.food_stuff_display);
        TextView textViewLabel = findViewById(R.id.ingredient_label_input);

        if(textViewQuantity.getText().toString().matches("") || textViewQuantity.getText().toString().matches("0")||
                textViewFoodStuff.getText().toString().matches("") || textViewLabel.getText().toString().matches("")) {
            Toast.makeText(MainActivity.this,"Enter all fields",Toast.LENGTH_LONG).show();

        }
        else {
            // get text strings for ingredient line
            //quantity

            String quantity = textViewQuantity.getText().toString();
            //measurement type
            Spinner spinMeasure = findViewById(R.id.measurement_type_input);
            String measure = spinMeasure.getSelectedItem().toString();
            ;
            //food stuff

            String foodStuff = textViewFoodStuff.getText().toString();
            //label

            String label = textViewLabel.getText().toString();
            //make a string array [] for the input data
            String[] ingredientArray = {foodStuff, quantity, measure, label};
            recipeData.add(ingredientArray);
            //make a string to display for this ingredient entry
            String ingredientLine = label + ": " + quantity + " " + measure + "/n";
            ingredientslist.add(ingredientLine);
            //log tag
            android.util.Log.d(LOG_TAG, String.valueOf(ingredientslist));
            android.util.Log.d(LOG_TAG, String.valueOf("recipe data is" + recipeData));
            ListView listview = findViewById(R.id.recipe_display);
            listview.invalidateViews();
            saveState.set(0, "0");
            saveState.set(1, "0");
            saveState.set(2, "");
            textViewQuantity.setText("0");
            textViewLabel.setText("");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //quantity
        TextView textViewQuantity = findViewById(R.id.quantity_input);
        String quantity = textViewQuantity.getText().toString();
        //measurement type
        Spinner spinMeasure = findViewById(R.id.measurement_type_input);
        int position = spinMeasure.getSelectedItemPosition();
        //food stuff
        TextView textViewFoodStuff = findViewById(R.id.food_stuff_display);
        String foodStuff = textViewFoodStuff.getText().toString();
        //label
        TextView textViewLabel = findViewById(R.id.ingredient_label_input);
        String label = textViewLabel.getText().toString();
        savedInstanceState.putString("q", quantity);

        savedInstanceState.putInt("m", position);
        savedInstanceState.putString("l", label);
        saveState.set(0,quantity);
        saveState.set(1, Integer.toString(position));
        saveState.set(2, label);
        android.util.Log.d(LOG_TAG, "saved bundle" + savedInstanceState.getString("q"));

    }


    public void startCalculateNutrition(View view) {
        android.util.Log.d(LOG_TAG, "startCalculateN arraylist is" + recipeData.size());
        for(int i = 0; i < recipeData.size(); i++) {
            String[] myArray = recipeData.get(i);
            NutritionDataHolder.addIngredient(myArray);
            String foodStuff = myArray[0];
            BackgroundWorker backgroundworker = new BackgroundWorker(this);
            backgroundworker.execute("login", foodStuff);
        }


}

    public void showCalcToast() {
        Toast.makeText(MainActivity.this,"Calculating",Toast.LENGTH_LONG).show();
    }

    public void startCalcActivity() {
        Intent intent = new Intent(this, NutritionCalculator.class);
        startActivity(intent);
    }
}


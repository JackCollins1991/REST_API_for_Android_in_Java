package com.example.gudrecipe;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class NutritionCalculator extends AppCompatActivity {
    public static ArrayList<String[]> datatable = new ArrayList<String[]>();
    public static ArrayList<String[]> resulttable = new ArrayList<String[]>();
    public static ArrayList<String> resultTotals = new ArrayList<String>();
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.d(LOG_TAG, "nutrition display created log");
        setContentView(R.layout.calculate_nutrition);
        android.util.Log.d(LOG_TAG, "calculator activity getData returns size" + NutritionDataHolder.getData().size());
        android.util.Log.d(LOG_TAG, "nutrition display created log" + datatable.size());
        calculateItems(NutritionDataHolder.getData());


        TextView textView = findViewById(R.id.total_textview);
        android.util.Log.d(LOG_TAG, "the totals text view display text should be" + resultTotals.get(0));
        String charseq = (resultTotals.get(0));
        textView.setText(charseq);
    }


    private void calculateItems(ArrayList<String[]> datatable) {
        inputHundredGrams(datatable);
        calculateDisplay(datatable);
        calculatetotals(resulttable);
        NutritionDataHolder.flushData();

    }

    private void calculatetotals(ArrayList<String[]> resulttable) {
        double running_total = 0;
        String[] totals = new String[6];
        for(int i = 3; i < 9; i++){
            for(int j = 0; j < resulttable.size(); j++){
                running_total = running_total + Double.parseDouble((resulttable.get(j))[i]);
            }
            totals[i-3] = Double.toString(running_total);
            running_total = 0;
        }
        String totaldisplay =   Arrays.toString(totals);//totals.toString();
        resultTotals.add(totaldisplay);
    }

    private void calculateDisplay(ArrayList<String[]> datatable) {
        for(int i =0; i < datatable.size(); i++){
            String[] resultString = new String[9];
            //ingredient data here
            resultString[0] = (datatable.get(i)[1]);
            resultString[1] = (datatable.get(i)[2]);
            resultString[2] = (datatable.get(i)[3]);
            //nutrition amounts start here
            resultString[3] = Float.toString((Float.parseFloat((datatable.get(i)[4]))*(Float.parseFloat((datatable.get(i)[10])))));
            resultString[4] = Float.toString((Float.parseFloat((datatable.get(i)[5]))*(Float.parseFloat((datatable.get(i)[10])))));
            resultString[5] = Float.toString((Float.parseFloat((datatable.get(i)[6]))*(Float.parseFloat((datatable.get(i)[10])))));
            resultString[6] = Float.toString((Float.parseFloat((datatable.get(i)[7]))*(Float.parseFloat((datatable.get(i)[10])))));
            resultString[7] = Float.toString((Float.parseFloat((datatable.get(i)[8]))*(Float.parseFloat((datatable.get(i)[10])))));
            resultString[8] = Float.toString((Float.parseFloat((datatable.get(i)[9]))*(Float.parseFloat((datatable.get(i)[10])))));
            resulttable.add(resultString);
        }
    }

    private void inputHundredGrams(ArrayList<String[]> datatable) {
        String[] measurements_array = getResources().getStringArray(R.array.Measurements);
        for (int i = 0; i < datatable.size(); i++){
            String grams = "";
            Float qty;
            Float hunGramsTotal;
            String measure = (datatable.get(i))[2];
            if(measure.equals(measurements_array[0])){
                grams = "0.36";
            }
            if (measure.equals(measurements_array[1])){
                grams = "5";
            };
            if(measure.equals(measurements_array[2])){
                grams = "14.3";
            };
            if(measure.equals(measurements_array[3])){
                grams = "128";
            };
            if(measure.equals(measurements_array[4])){
                grams = "1000";
            };
            qty = Float.parseFloat ((datatable.get(i))[1]);
            Float grams_float = Float.parseFloat(grams);
            hunGramsTotal = (qty * grams_float)/100;
            (datatable.get(i))[10] = hunGramsTotal.toString();
        }
    }

}

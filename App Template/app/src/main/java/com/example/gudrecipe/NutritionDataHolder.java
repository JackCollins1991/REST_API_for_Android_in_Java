package com.example.gudrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import java.util.ArrayList;

import static com.example.gudrecipe.MainActivity.ingredientslist;

public class NutritionDataHolder {
    private static final String LOG_TAG = NutritionDataHolder.class.getSimpleName();

    private static ArrayList<String[]> recipedata = new ArrayList<>();
    private static ArrayList<String[]> ingredientdata = new ArrayList<>();
    private static ArrayList<String[]> nutritiondata = new ArrayList<>();
    private static int done = 0;
    Context context;

    NutritionDataHolder (Context ctx) {
        context = ctx;

    }


    public static void addIngredient (String[] ingredients){
        ingredientdata.add(ingredients);
    }

    public static void addNutrition (String nutrition) {
        android.util.Log.d(LOG_TAG, "addNutrition method nutrition string is: " + nutrition);
        String[] nutritionarray = nutrition.split(",");
        android.util.Log.d(LOG_TAG, "addNutrition method nutrition array is: " + Integer.toString(nutritionarray.length));
        nutritiondata.add(nutritionarray);
        android.util.Log.d(LOG_TAG, "addNutrition method nutritiondata is: " + Integer.toString(nutritiondata.size()));
    }

    public void combineData () {
        android.util.Log.d(LOG_TAG, "combineData method ingrediatdaat is: " + Integer.toString(ingredientdata.size()));
        android.util.Log.d(LOG_TAG, "combineData method nutritiondata is: " + Integer.toString(nutritiondata.size()));
        for(int j = 0; j < ingredientdata.size(); j++) {
            String[] stringarray = new String[11];
            for (int i = 0; i < 4; i++) {
                stringarray[i] = (ingredientdata.get(j))[i];
            }
            for (int k = 4; k < 11; k++) {
                stringarray[k] = (nutritiondata.get(j))[k - 3];
            }
            android.util.Log.d(LOG_TAG, String.valueOf("combineData method Holder array is" + stringarray));
            recipedata.add(stringarray);
            android.util.Log.d(LOG_TAG, String.valueOf("combineData method Holder 2d array is" + recipedata));
            android.util.Log.d(LOG_TAG, ("getDatamethod returns" + getData().size()));
            Intent intent = new Intent(context, NutritionCalculator.class);
            context.startActivity(intent);
            android.util.Log.d(LOG_TAG, String.valueOf("combineData method post start activity executed"));
        }
    }

    public static ArrayList<String[]> getData(){
        return recipedata;
    }
    public static void flushData() {
        for(int i = 0; i < recipedata.size(); i++) {
            recipedata.remove(i);
        }
        for(int i = 0; i < ingredientdata.size(); i++) {
            ingredientdata.remove(i);
        }
        for(int i = 0; i < nutritiondata.size(); i++) {
            nutritiondata.remove(i);
        }
    }

    public static int getNutritionSize() {
        return nutritiondata.size();
    }

    public static int getIngredientSize() {
        return ingredientdata.size();
    }


}

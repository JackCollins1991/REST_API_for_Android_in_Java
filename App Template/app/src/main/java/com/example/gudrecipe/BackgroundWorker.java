package com.example.gudrecipe;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = BackgroundWorker.class.getSimpleName();
    Context context;

    BackgroundWorker (Context ctx) {
        context = ctx;

    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        Log.d(LOG_TAG, "type in dobackground is: "+ type);
        String login_url = "https://abducting-restauran.000webhostapp.com/login.php";
        if (type.equals("login")) {
            try {
                Log.d(LOG_TAG, "start try" + params[1]);
                String input_food = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("android_input","UTF-8")+"="+URLEncoder.encode(input_food,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    Log.d(LOG_TAG, "background while loop line writing" + line);
                        result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                android.util.Log.d(LOG_TAG, "background log: "+result);
                return result;
            } catch (MalformedURLException e) {
                android.util.Log.d(LOG_TAG, "MalformedURLException");
                e.printStackTrace();
            } catch (IOException e) {
                android.util.Log.d(LOG_TAG, "IOException e");
                e.printStackTrace();
            }


        }
        android.util.Log.d(LOG_TAG, "There was a problem with your connection to the internet");
        return "There was a problem with your connection to the internet";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(LOG_TAG, "onPreExecute log tag");
    }

    @Override
    protected void onPostExecute(String result) {
        android.util.Log.d(LOG_TAG, "Post Execute started");
        NutritionDataHolder.addNutrition(result);
        Log.d(LOG_TAG, "sock: " + result);
        android.util.Log.d(LOG_TAG, "calcresult: "+result);

        if (NutritionDataHolder.getNutritionSize() == NutritionDataHolder.getIngredientSize()){
            NutritionDataHolder nutritiondataholder = new NutritionDataHolder(this.context);

            nutritiondataholder.combineData();

        }
        else{
            Log.d(LOG_TAG, "nutrition and ingredients not equal yet");
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


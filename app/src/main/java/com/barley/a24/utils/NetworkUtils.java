package com.barley.a24.utils;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class NetworkUtils {

    private static final String BASE_URL_CASH_RATE = "https://api.privatbank.ua/p24api/exchange_rates?json&date=01.12.2014";

    public static JSONObject getCurrencyJSONObject(String date) {
        JSONObject result = null;
        try {
            result = new JSONLoadTask().execute(BASE_URL_CASH_RATE).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static class JSONLoadTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject result = null;
            URL url = null;
            if (strings == null) {
                return null;
            }
            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(BASE_URL_CASH_RATE);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = new JSONObject(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return result;
        }
    }
}

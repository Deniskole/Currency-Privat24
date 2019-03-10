package com.barley.a24.utils;

import android.util.Log;

import com.barley.a24.data.CurrencyRate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtils {

    private static final String PARAMS_EXCHANGE_RATE = "exchangeRate";

    private static final String PARAMS_BASE_CURRENCY = "baseCurrency";
    private static final String PARAMS_CURRENCY = "currency";
    private static final String PARAMS_SALE_RATE_NB = "saleRateNB";
    private static final String PARAMS_PURCHASE_RATE_NB = "purchaseRateNB";
    private static final String PARAMS_SALE_RATE = "saleRate";
    private static final String PARAMS_PURCHASE_RATE = "purchaseRate";

    public static ArrayList<CurrencyRate> getCurrencyListFromJson(JSONObject jsonObject) {
        ArrayList<CurrencyRate> result = new ArrayList<>();
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(PARAMS_EXCHANGE_RATE);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String baseCurrency = object.getString(PARAMS_BASE_CURRENCY);
                String currency = object.getString(PARAMS_CURRENCY);
                double saleRateNB = object.getDouble(PARAMS_SALE_RATE_NB);
                double purchaseRateNB = object.getDouble(PARAMS_PURCHASE_RATE_NB);
                double saleRate = 0;
                double purchaseRate = 0;
                try {
                    saleRate = object.getDouble(PARAMS_SALE_RATE);
                    purchaseRate = object.getDouble(PARAMS_PURCHASE_RATE);
                } catch (Exception e) {

                }
                CurrencyRate currencyRate = new CurrencyRate(baseCurrency, currency, saleRateNB, purchaseRateNB, saleRate, purchaseRate);
                result.add(currencyRate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

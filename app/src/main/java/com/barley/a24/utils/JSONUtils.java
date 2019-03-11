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

                String baseCurrency = "";
                String currency = "";
                double saleRateNB = 0;
                double purchaseRateNB = 0;
                double saleRate = 0;
                double purchaseRate = 0;

                try {
                    baseCurrency = object.getString(PARAMS_BASE_CURRENCY);
                    currency = object.getString(PARAMS_CURRENCY);
                    saleRate = object.getDouble(PARAMS_SALE_RATE);
                    purchaseRate = object.getDouble(PARAMS_PURCHASE_RATE);
                    saleRateNB = object.getDouble(PARAMS_SALE_RATE_NB);
                    purchaseRateNB = object.getDouble(PARAMS_PURCHASE_RATE_NB);
                } catch (Exception e) {
                }
                CurrencyRate currencyRate = new CurrencyRate(baseCurrency, currency, saleRateNB, purchaseRateNB, saleRate, purchaseRate);
                if (!baseCurrency.isEmpty() && !currency.isEmpty() && saleRate != 0 && purchaseRate != 0) {
                    result.add(currencyRate);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

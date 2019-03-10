package com.barley.a24.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.barley.a24.R;
import com.barley.a24.data.CurrencyRate;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private ArrayList<CurrencyRate> currencyRatesList;

    public void setCurrencyRatesList(ArrayList<CurrencyRate> currencyRatesList) {
        this.currencyRatesList = currencyRatesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_item, viewGroup, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder currencyViewHolder, int i) {

        CurrencyRate currencyRate = currencyRatesList.get(i);

        currencyViewHolder.textViewBaseCurrency.setText(currencyRate.getBaseCurrency());
        currencyViewHolder.textViewCurrency.setText(currencyRate.getCurrency());

        currencyViewHolder.textViewSaleRate.setText(Double.toString(currencyRate.getSaleRate()));
        currencyViewHolder.textViewPurchaseRate.setText(Double.toString(currencyRate.getPurchaseRate()));

        currencyViewHolder.textViewSaleRateNB.setText(Double.toString(currencyRate.getSaleRateNB()));
        currencyViewHolder.textViewPurchaseRateNB.setText(Double.toString(currencyRate.getPurchaseRateNB()));

    }

    @Override
    public int getItemCount() {
        return currencyRatesList.size();
    }


    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewBaseCurrency;
        TextView textViewCurrency;
        TextView textViewSaleRate;
        TextView textViewPurchaseRate;
        TextView textViewSaleRateNB;
        TextView textViewPurchaseRateNB;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBaseCurrency = itemView.findViewById(R.id.textViewBaseCurrency);
            textViewCurrency = itemView.findViewById(R.id.textViewCurrency);
            textViewSaleRate = itemView.findViewById(R.id.textViewSaleRate);
            textViewPurchaseRate = itemView.findViewById(R.id.textViewPurchaseRate);
            textViewSaleRateNB = itemView.findViewById(R.id.textViewSaleRateNB);
            textViewPurchaseRateNB = itemView.findViewById(R.id.textViewPurchaseRateNB);
        }
    }


}

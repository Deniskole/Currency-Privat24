package com.barley.a24;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.barley.a24.adapters.CurrencyAdapter;
import com.barley.a24.data.CurrencyRate;
import com.barley.a24.utils.JSONUtils;
import com.barley.a24.utils.NetworkUtils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CurrencyRate> list = null;
    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;
    private static String dateStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DateFormat df = new SimpleDateFormat(" d.MM.yyyy");
        dateStr = df.format(Calendar.getInstance().getTime());

        mDisplayDate = findViewById(R.id.tvDate);
        mDisplayDate.setText(getResources().getString(R.string.select_date) + " " + dateStr);

        list = new ArrayList<>();

        JSONObject jsonObject = NetworkUtils.getCurrencyJSONObject(dateStr.trim());
        list = JSONUtils.getCurrencyListFromJson(jsonObject);

        recyclerView = findViewById(R.id.recyclerViewCurrency);
        currencyAdapter = new CurrencyAdapter();
        currencyAdapter.setCurrencyRatesList(list);
        recyclerView.setAdapter(currencyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void donwloadData() {
        list.clear();
        JSONObject jsonObject = NetworkUtils.getCurrencyJSONObject(dateStr);
        list = JSONUtils.getCurrencyListFromJson(jsonObject);
        currencyAdapter.setCurrencyRatesList(list);
        currencyAdapter.notifyDataSetChanged();
    }


    public void setDate(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateStr = String.format("%s.%s.%s", dayOfMonth, month, year);
                mDisplayDate.setText(getResources().getString(R.string.select_date) + " " + dateStr);
                donwloadData();
            }
        };
    }
}


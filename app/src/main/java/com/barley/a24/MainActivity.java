package com.barley.a24;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.barley.a24.adapters.CurrencyAdapter;
import com.barley.a24.data.CurrencyRate;
import com.barley.a24.utils.JSONUtils;
import com.barley.a24.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CurrencyRate> list = null;
    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;

    private static final String TAG = "MainActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;
    private String dateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        JSONObject jsonObject = NetworkUtils.getCurrencyJSONObject(dateStr);
        list = JSONUtils.getCurrencyListFromJson(jsonObject);

        mDisplayDate = findViewById(R.id.tvDate);
        recyclerView = findViewById(R.id.recyclerViewCurrency);
        currencyAdapter = new CurrencyAdapter();
        currencyAdapter.setCurrencyRatesList(list);
        recyclerView.setAdapter(currencyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setData();

    }


    public void donwloadData() {
        JSONObject jsonObject = NetworkUtils.getCurrencyJSONObject(dateStr);
        list.clear();
        list = JSONUtils.getCurrencyListFromJson(jsonObject);
        currencyAdapter.setCurrencyRatesList(list);
        currencyAdapter.notifyDataSetChanged();
    }


    public void setData() {
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateStr = String.format("%s.%s.%s", dayOfMonth, month, year);
                donwloadData();
                mDisplayDate.setText(dateStr);
            }
        };
    }

}


package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddEditCityFragment.OnCityEditedListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize city list
        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Moscow", "RU"));
        dataList.add(new City("Sydney", "NSW"));
        dataList.add(new City("Berlin", "BE"));
        dataList.add(new City("Vienna", "W"));
        dataList.add(new City("Tokyo", "TK"));
        dataList.add(new City("Beijing", "BJ"));
        dataList.add(new City("Osaka", "OS"));
        dataList.add(new City("New Delhi", "DL"));

        // Find ListView
        cityList = findViewById(R.id.city_list);

        // Adapter (simple built-in row layout)
        cityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );
        cityList.setAdapter(cityAdapter);

        // Tap a city to edit it
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City clickedCity = dataList.get(position);
            AddEditCityFragment.newInstance(clickedCity)
                    .show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    // Called by the fragment after editing
    @Override
    public void onCityEdited() {
        cityAdapter.notifyDataSetChanged();
    }
}

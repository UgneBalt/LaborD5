package org.ugne.labord5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<CurrencyRate> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listViewId);

        arrayAdapter = new ArrayAdapter<CurrencyRate>(
                this,
                android.R.layout.simple_list_item_1
        );

        listView.setAdapter(arrayAdapter);

        loadCurrencyRates();
    }

    private void loadCurrencyRates() {
        DataLoader executor = new DataLoader(arrayAdapter);
        executor.execute();


    }
}

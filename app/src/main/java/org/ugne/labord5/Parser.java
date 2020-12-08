package org.ugne.labord5;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    public List<CurrencyRate> parse(JSONObject rates) {
        List<CurrencyRate> result = new ArrayList<CurrencyRate>();

        if(rates != null)
        {
            try {
                JSONObject allRates = rates.getJSONObject("rates");
                Iterator<String> allKeys = allRates.keys();
                while (allKeys.hasNext())
                {
                    String currency = allKeys.next();
                    Double rate = allRates.getDouble(currency);
                    CurrencyRate row = new CurrencyRate();
                    row.code = currency;
                    row.rate = rate;
                    result.add(row);
                }
            } catch (JSONException ex) {
                Log.e("App", "Failure", ex);
            }
        }

        return result;
    }
}

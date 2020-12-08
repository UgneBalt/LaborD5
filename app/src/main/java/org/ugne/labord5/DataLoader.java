package org.ugne.labord5;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DataLoader extends AsyncTask<Void, Void, JSONObject> {
    ArrayAdapter<CurrencyRate> arrayFromMA;

    public DataLoader(ArrayAdapter<CurrencyRate> arrayAdapter) {
    arrayFromMA = arrayAdapter;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String str="https://api.exchangeratesapi.io/latest";
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream())); //kanalas atsisiusti psl duom

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onPostExecute(JSONObject rates) {
        Parser p = new Parser();
        List<CurrencyRate> result = p.parse(rates);

        arrayFromMA.addAll(result);
    }
}

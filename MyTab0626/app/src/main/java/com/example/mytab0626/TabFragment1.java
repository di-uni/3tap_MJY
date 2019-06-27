package com.example.mytab0626;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class TabFragment1 extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DataList> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.Recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        arrayList = new ArrayList<DataList>();
        Jsontask jsontask = new Jsontask();
        jsontask.execute();

        return view;
    }
    public class Jsontask extends AsyncTask<String, String, String> {

        HttpURLConnection httpURLConnection = null;
        // HttpsURLConnection httpsURLConnection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        String fullfile;

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://api.myjson.com/bins/167hen");

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                stringBuffer = new StringBuffer();
                String line = " ";
                while((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);

                }

                fullfile = stringBuffer.toString();
                JSONObject jsonObject = new JSONObject(fullfile);
                JSONObject jsonObjectChild = jsonObject.getJSONObject("phonenumber");
                for(Iterator key = jsonObjectChild.keys(); key.hasNext(); ){
                    JSONObject phonenumbers = (JSONObject) jsonObjectChild.get((String) key.next());

                    String name = phonenumbers.getString("name");
                    String number = phonenumbers.getString("number");
                    arrayList.add(new DataList(name, number));
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            DataAdapter dataAdapter = new DataAdapter(arrayList, getContext());
            recyclerView.setAdapter(dataAdapter);
        }
    }
}

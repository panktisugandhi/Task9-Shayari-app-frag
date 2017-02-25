package com.example.pankti.task10;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
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

/**
 * Created by welcome on 2/25/2017.
 */

public class Gridfrag extends Fragment{

    GridView gridView;
    Quotefrag listViewfragment = new Quotefrag();

    int[] val = new int[]{R.drawable.moti,R.drawable.valti,
            R.drawable.love,R.drawable.fship,
            R.drawable.life,R.drawable.images,
            R.drawable.positive};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View  view = inflater.inflate(R.layout.activity_grid, container, false);

        gridView=(GridView) view.findViewById(R.id.fragment_grid);
        new MyGridClass().execute("http://rapidans.esy.es/test/getallcat.php");
        return view;
    }
    class MyGridClass extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        Exception exceptionGrid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;


                } catch (Exception e) {
                    this.exceptionGrid = e;

                }
            } catch (Exception e) {
                this.exceptionGrid=e;

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            ArrayList<GridPost> Gpost = new ArrayList<>();

            try {

                JSONObject rootObject = new JSONObject(s);


                JSONArray dataArray = rootObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject jsonObject = dataArray.getJSONObject(i);


                    GridPost p = new GridPost();

                    p.setId(jsonObject.getInt("id"));
                    p.setCatagory(jsonObject.getString("name"));

                    Gpost.add(p);
                }



            } catch (Exception e) {
                this.exceptionGrid = e;
                Toast.makeText(getActivity(), "Requires Internet Connection", Toast.LENGTH_SHORT).show();
            }


            GridViewAdapter customAdapterGrid = new GridViewAdapter(getActivity(),Gpost,val);

            gridView.setAdapter(customAdapterGrid);
        }
    }

}

package com.example.pankti.task10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by welcome on 2/25/2017.
 */

public class Showfrag extends Fragment {

    private String id;
   // private TextView t1;
    private Button share;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.activity_show, container, false);
        id = getArguments().getString("Quote");
       TextView t1 = (TextView)view.findViewById(R.id.quote);
        id = getArguments().getString("Quote");
        t1.setText(id);


        share = (Button)view.findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                id = getArguments().getString("Quote");
                shareIntent.getStringExtra("Quote");
                shareIntent.putExtra(Intent.EXTRA_TEXT,id);
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });

        return view;
    }

}

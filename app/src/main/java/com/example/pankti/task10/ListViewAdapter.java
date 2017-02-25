package com.example.pankti.task10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/26/2017.
 */

public class ListViewAdapter extends BaseAdapter {


    ArrayList<ListPost> listPosts;
    LayoutInflater layoutInflater;
    Context contextList;
    Showfrag showfrag;

    ListViewAdapter(Context contextList,ArrayList<ListPost> listPosts){
        this.contextList=contextList;
        this.listPosts=listPosts;
    }

    @Override
    public int getCount() {
        return listPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return listPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    static class ViewHolder
    {
//      TextView id;
//        TextView cat_id;
        TextView quotes;


    }
    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {
        final ViewHolder holderList;


        if (view == null) {
            holderList = new ViewHolder();
            layoutInflater = (LayoutInflater) contextList.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_row, parent, false);



            holderList.quotes=(TextView) view.findViewById(R.id.tv_list_view);

            view.setTag(holderList);
        }

        else{
            holderList=(ViewHolder) view.getTag();
        }


        holderList.quotes.setText(listPosts.get(position).getQuotes());
        holderList.quotes.setSelected(true);
        holderList.quotes.requestFocus();

        holderList.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Activity activityList = (Activity) contextList;

                showfrag = new Showfrag();

                Bundle bundleList = new Bundle();
                bundleList.putString("Quote",listPosts.get(position).getQuotes());
                showfrag.setArguments(bundleList);

                FragmentManager fmq = activityList.getFragmentManager();
                FragmentTransaction ftq = fmq.beginTransaction();
                ftq.replace(R.id.linear,showfrag);
                ftq.addToBackStack("");
                ftq.commit();

            }
        });

        return view;
    }
}

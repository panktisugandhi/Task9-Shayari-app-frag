package com.example.pankti.task10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/26/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    ArrayList<GridPost> gridPosts;
    Context context;
    LayoutInflater inflater;
    int[] val;


    GridViewAdapter(Context context,ArrayList<GridPost> gridPosts,int[] val){

        this.context=context;
        this.gridPosts=gridPosts;
        this.val=val;

    }

    @Override
    public int getCount() {
        return gridPosts.size();
    }

    @Override
    public Object getItem(int i) {
        return gridPosts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        TextView desc;
        ImageView img;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_row, viewGroup, false);



            holder.desc=(TextView) view.findViewById(R.id.tv_grid_view);
            holder.desc.setSelected(true);
            holder.desc.requestFocus();

            holder.img=(ImageView) view.findViewById(R.id.grid_image);

            view.setTag(holder);
        }

        else{
            holder = (ViewHolder) view.getTag();
        }


        holder.desc.setText(gridPosts.get(i).getCatagory());
        holder.desc.setSelected(true);
        holder.img.setImageResource(val[i]);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Quotefrag quotefrag  = new Quotefrag();

                Bundle bundleGrid = new Bundle();
                bundleGrid.putInt("QuotesList",gridPosts.get(i).getId());
                quotefrag.setArguments(bundleGrid);


                MainActivity activityGrid = (MainActivity) context;

               FragmentManager fmi = activityGrid.getSupportFragmentManager();
                FragmentTransaction fti = fmi.beginTransaction();
                fti.replace(R.id.linear,quotefrag);
                fti.addToBackStack("");
                fti.commit();



            }
        });

        return view;
    }
}

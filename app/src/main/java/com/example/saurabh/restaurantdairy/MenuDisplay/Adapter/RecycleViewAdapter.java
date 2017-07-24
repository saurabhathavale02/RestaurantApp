package com.example.saurabh.restaurantdairy.MenuDisplay.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.AddToCart.AddToCartActivity;
import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 7/11/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
{

    private final String TAG =RecycleViewAdapter.class.getName();
    List<MenuItem> menutypelist=new ArrayList<MenuItem>();
    View.OnClickListener mClickListener;


    public RecycleViewAdapter(List<MenuItem>items) {

        this.menutypelist = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menuitem_list, parent, false);

        final ViewHolder holder=new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), AddToCartActivity.class);
                intent.putExtra("SelectedMenuId",menutypelist.get(holder.getAdapterPosition()).getId());
                intent.putExtra("SelectedMenuItem",holder.getAdapterPosition());
                intent.putExtra("MenuItem", (Serializable) menutypelist);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.MenuName.setText(menutypelist.get(position).getName());
        holder.Description.setText(menutypelist.get(position).getDescription());
        holder.Price.setText("$"+menutypelist.get(position).getPriceLarge().floatValue());

    }

    @Override
    public int getItemCount() {
        return menutypelist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        public final View mView;

        public final TextView MenuName,Description,Price;
        public final ImageView Icon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Description = (TextView) view.findViewById(R.id.description);
            MenuName = (TextView) view.findViewById(R.id.MenuName);
            Icon=(ImageView) view.findViewById(R.id.icon);
            Price=(TextView) view.findViewById(R.id.Price);

        }
    }


}

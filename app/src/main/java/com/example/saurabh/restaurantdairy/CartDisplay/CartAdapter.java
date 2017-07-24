package com.example.saurabh.restaurantdairy.CartDisplay;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.AddToCart.AddToCartActivity;
import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.selectedfoodmodel.SelectedFood;

import java.util.List;

/**
 * Created by saura on 7/19/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
{
    private final String TAG =CartAdapter.class.getName();

    List<SelectedFood> selectedFoods;
    public CartAdapter(List<SelectedFood> selectedFoods)
    {
        Log.i(TAG,"in constructor="+selectedFoods.size());
        this.selectedFoods=selectedFoods;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView selected_food_name, selected_quantity, selected_cost;
        public ImageButton Delete;

        public MyViewHolder(View view)
        {
            super(view);
            selected_food_name = (TextView) view.findViewById(R.id.selected_food_name);
            selected_quantity = (TextView) view.findViewById(R.id.selected_quantity);
            selected_cost = (TextView) view.findViewById(R.id.selected_cost);
            Delete=(ImageButton) view.findViewById(R.id.Delete);
        }


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartlist, parent, false);

        final MyViewHolder holder=new MyViewHolder(itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG,"holder.getAdapterPosition()="+holder.getAdapterPosition());
                Log.i(TAG,"item click="+selectedFoods.get(holder.getAdapterPosition()).getId());

                Intent intent = new Intent(v.getContext(), AddToCartActivity.class);
                intent.putExtra("SelectedMenuId",selectedFoods.get(holder.getAdapterPosition()).getId());
                v.getContext().startActivity(intent);

            }
        });

        holder.Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedFoods.remove(Integer.valueOf(holder.getAdapterPosition()));
                Log.i(TAG,"delete="+holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SelectedFood selectedFood = selectedFoods.get(position);
        holder.selected_food_name.setText(selectedFood.getSelected_food());
        holder.selected_quantity.setText(""+selectedFood.getQuantity());
        holder.selected_cost.setText(""+selectedFood.getPrice()*selectedFood.getQuantity());
    }

    @Override
    public int getItemCount()
    {
        Log.i(TAG,"in constructor="+selectedFoods.size());
        return selectedFoods.size();
    }
}



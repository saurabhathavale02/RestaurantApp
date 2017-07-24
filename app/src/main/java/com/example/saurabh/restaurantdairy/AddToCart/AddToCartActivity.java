package com.example.saurabh.restaurantdairy.AddToCart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuItem;
import com.example.saurabh.restaurantdairy.apimodel.selectedfoodmodel.SelectedFood;
import com.example.saurabh.restaurantdairy.root.App;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddToCartActivity extends AppCompatActivity
{
    private final String TAG =AddToCartActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.FoodImage)
    ImageView foodimage;
    @BindView(R.id.Food_Name)
    TextView Food_Name;
    @BindView(R.id.Price)
    TextView Price;
    @BindView(R.id.Food_Description)
    TextView Food_Description;
    @BindView(R.id.Quantity_number)
    TextView Quantity_number;
    @BindView(R.id.Sub)
    Button Sub;
    @BindView(R.id.Add)
    Button Add;
    @BindView(R.id.addcart)
    Button Add_To_Cart;

    String food,description;
    Float price;
    Integer Quantity=1;
    Integer position,MenuId,i=0,flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtocart);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        MenuId= getIntent().getExtras().getInt("SelectedMenuId");
        Log.i(TAG,"MenuId="+MenuId);
        if(((App)getApplicationContext()).selectedFood.size()>0)
        {
            flag=0;
            i=0;
            while (i < ((App)getApplicationContext()).selectedFood.size())
            {
                if(((App)getApplicationContext()).selectedFood.get(i).getId().equals(MenuId))
                {
                    food= ((App)getApplicationContext()).selectedFood.get(i).getSelected_food();
                    price=((App)getApplicationContext()).selectedFood.get(i).getPrice();
                    Quantity=((App)getApplicationContext()).selectedFood.get(i).getQuantity();
                    description=((App)getApplicationContext()).selectedFood.get(i).getSelected_food_desc();
                    flag=1;
                    break;
                }
                i++;
            }
        }
        if(flag==0)
        {
            position= getIntent().getExtras().getInt("SelectedMenuItem");
            List<MenuItem> menuItem= (List<MenuItem>) getIntent().getSerializableExtra("MenuItem");

            food= menuItem.get(position).getName();
            price=menuItem.get(position).getPriceLarge().floatValue();
            description=menuItem.get(position).getDescription();
            Quantity=1;
        }

        Food_Name.setText(food);
        Price.setText("$"+price);
        Quantity_number.setText(""+Quantity);
        Food_Description.setText(description);

        Add_To_Cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(flag==0) {
                    SelectedFood selectedFood = new SelectedFood(MenuId, food, price, Quantity, description);
                    ((App) getApplicationContext()).selectedFood.add(selectedFood);
                }
                else
                {
                    ((App)getApplicationContext()).selectedFood.get(i).setSelected_food(food);
                    ((App)getApplicationContext()).selectedFood.get(i).setPrice(price);
                    ((App)getApplicationContext()).selectedFood.get(i).setQuantity(Quantity);
                    ((App)getApplicationContext()).selectedFood.get(i).setSelected_food_desc(description);
                }
                finish();
            }
        });


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Quantity=Quantity+1;
                Quantity_number.setText(""+Quantity);
            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Quantity>1)
                {
                    Quantity=Quantity-1;
                    Quantity_number.setText(""+Quantity);
                }
            }
        });
    }
}

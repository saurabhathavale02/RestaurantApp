package com.example.saurabh.restaurantdairy.MenuDisplay.Adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 7/11/2017.
 */

@SuppressLint("ValidFragment")
public class MenuListFragment extends Fragment
{
    private final String TAG =MenuListFragment.class.getName();
    List<MenuItem> menutypelist= new ArrayList<MenuItem>();
    int menutype;
    public MenuListFragment(int menutype, List<MenuItem> menuItems)
    {
        menutypelist.clear();
        Log.i(TAG,"menutype="+menutype);
        this.menutype=menutype;
        if(menutype==0)
        {
            for (int i = 0; i < 5; i++) {
                Log.i(TAG, "menutype=" + menuItems.get(i));
                menutypelist.add(menuItems.get(i));
            }
        }

        if(menutype==1)
        {
            for (int i = 5; i < 11; i++) {
                Log.i(TAG, "menutype=" + menuItems.get(i));
                menutypelist.add(menuItems.get(i));
            }
        }

        if(menutype==2)
        {
            for (int i = 11; i < 15; i++) {
                Log.i(TAG, "menutype=" + menuItems.get(i));
                menutypelist.add(menuItems.get(i));
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.menulist, container, false);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        final RecycleViewAdapter recycleViewAdapter=new RecycleViewAdapter(menutypelist);
        rv.setAdapter(recycleViewAdapter);
        return rv;
    }
}

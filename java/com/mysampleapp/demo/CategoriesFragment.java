package com.mysampleapp.demo;


import android.app.ListActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mysampleapp.R;

/**
 * Created by HP on 6/9/2016.
 */
public class CategoriesFragment extends Fragment {
    final String[] items = new String[] { "Recharge", "Travel", "Fashion",
            "Electronics", "Food & Dining",  "Flowers,Gifts & Jewellary", "Beauty & Health", "Home Furnishing & Decor",
            "Entertainment", "Sports & Fitness","Web Hosting & Domains","Education & Learning","Miscellaneous"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.categories_layout, container,false);
        ListView list = (ListView)view.findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);
        return view;
    }

}

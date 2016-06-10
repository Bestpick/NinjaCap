package com.mysampleapp.demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;


import com.mysampleapp.R;

/**
 * Created by HP on 6/9/2016.
 */
public class StoresFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,Bundle savedInstanceState){
        return  inflater.inflate(R.layout.primary_layout,null);
    }
}

package com.mysampleapp.demo;

/**
 * Created by HP on 6/9/2016.
 */

import android.content.Context;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.mysampleapp.R;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    //array of integers for images IDs
    private Integer[] mImageIds = {
            R.drawable.sg1,
            R.drawable.sg2,
            R.drawable.sg3

    };

    //constructor
    public ImageAdapter (Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);

        imageView.setImageResource(mImageIds[i]);
        imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        return imageView;
    }
}
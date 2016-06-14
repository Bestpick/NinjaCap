
package com.mysampleapp.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysampleapp.R;

import java.util.ArrayList;

/**
 * Created by HP on 6/13/2016.
 */
public class GridAdapterCatShow extends BaseAdapter {
    private Context context;
    private  ArrayList<String> cat_name=new ArrayList<String>();
    private  ArrayList<String> cat_com=new ArrayList<String>();
    private  ArrayList<Integer> Imageid=new ArrayList<Integer>();
    public GridAdapterCatShow(Context c, ArrayList<String> cat_name,ArrayList<String> cat_com, ArrayList<Integer> Imageid) {
        context = c;
        this.Imageid = Imageid;
        this.cat_name = cat_name;
        this.cat_com=cat_com;
    }

    @Override
    public int getCount() {
        return this.cat_com.size();
    }

    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_cat, null);
            TextView textViewCom = (TextView) grid.findViewById(R.id.grid_text_com);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            TextView textViewName = (TextView) grid.findViewById(R.id.grid_text_name);

            textViewName.setText(cat_name.get(position));
            textViewCom.setText(cat_com.get(position));
            Integer x=Imageid.get(position);
            imageView.setImageResource(x);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }

}

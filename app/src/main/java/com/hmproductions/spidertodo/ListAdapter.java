package com.hmproductions.spidertodo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harsh Mahajan on 8/6/2017.
 */

class ListAdapter extends ArrayAdapter<Item> {

    ListAdapter(Context context, ArrayList<Item> items, int resource)
    {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View myView = convertView;

        if(myView == null)
        {
            myView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            TextView rowNumber = (TextView)myView.findViewById(R.id.rowNumber_textView);
            rowNumber.setText(String.valueOf(MainActivity.items.get(position).getRowNumber()));

            TextView itemName = (TextView)myView.findViewById(R.id.itemName_textView);
            itemName.setText(MainActivity.items.get(position).getItemName());
        }

        else
        {
            TextView rowNumber = (TextView)myView.findViewById(R.id.rowNumber_textView);
            rowNumber.setText(String.valueOf(MainActivity.items.get(position).getRowNumber()));

            TextView itemName = (TextView)myView.findViewById(R.id.itemName_textView);
            itemName.setText(MainActivity.items.get(position).getItemName());
        }

        return myView;
    }

    @Override
    public int getCount() {
        return MainActivity.rowNum-1;
    }
}

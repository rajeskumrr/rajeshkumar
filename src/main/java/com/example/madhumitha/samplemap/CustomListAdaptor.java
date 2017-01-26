package com.example.madhumitha.samplemap;

/**
 * Created by Rajesh kumar on 13/11/16.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomListAdaptor  extends ArrayAdapter<String> {

    private final Activity Context;
    private final String[] ListItemsName;

    public CustomListAdaptor(Activity context, String[] content) {
        super(context, R.layout.bookslist, content);
        this.Context = context;
        this.ListItemsName = content;

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = Context.getLayoutInflater();
        View ListViewSingle = inflater.inflate(R.layout.bookslist, null, true);

        TextView ListViewItems = (TextView)ListViewSingle.findViewById(R.id.bookname);

        ListViewItems.setText(ListItemsName[position]);

        return ListViewSingle;
    };
}
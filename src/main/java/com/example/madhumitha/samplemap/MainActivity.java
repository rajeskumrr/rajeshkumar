package com.example.madhumitha.samplemap;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ListAdapter listAdapter;

    DatabaseHelper  DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        DatabaseHelper = new DatabaseHelper(MainActivity.this);

*/
/*        try {
            DatabaseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DatabaseHelper.open();*//*


        c = DatabaseHelper.selecttop("SELECT  Title , snippet FROM MapDetails order by id desc LIMIT 10 ");

        Integer i = 0;
        int count = c.getCount();

        ListItemsName = new String[count];

        if (c.moveToFirst()) {
            do {
                ListItemsName[i] = (c.getString(0))+"--"+(c.getString(1));
                ListItemsName[i] = ListItemsName[i].replaceAll("\t","");
                ListItemsName[i] = ListItemsName[i].replaceAll("--","\n");
                i = i + 1;
            } while (c.moveToNext());
        }
*/
        Intent intent = getIntent();
        String[] ListItemsName = intent.getStringArrayExtra("Strings");

        listView = (ListView)findViewById(R.id.booklist);
        listAdapter = new CustomListAdaptor(MainActivity.this,ListItemsName);
        listView.setAdapter(listAdapter);
    }
}
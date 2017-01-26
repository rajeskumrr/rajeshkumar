package com.example.madhumitha.samplemap;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Rajesh kumar on 14/11/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    String DATABASE_PATH = null;
    private static String DATABASE_NAME = "Maps.sqlite";
    private SQLiteDatabase DataBase;
    private final Context context1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 10);
        this.context1 = context;
        this.DATABASE_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DATABASE_PATH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor select(String table, String[] columns, String selection, String selectionArgs, String groupBy, String having, String orderBy) {
        return DataBase.query(table, null, null, null, null, null, null);
    }

    public Cursor selectwithwhere(String sql) {
        return DataBase.rawQuery("select * from mapdetails where  "+ sql, null);
    }

    public Cursor selecttop(String sql) {
        return DataBase.rawQuery(sql, null);
    }

    public void open() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        DataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void createDataBase() throws IOException {
        this.getReadableDatabase();
        try {
            InputStream in = context1.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream out = new FileOutputStream(outFileName);
            byte[] j = new byte[5000];
            int i;
            while ((i = in.read(j)) > 0) {out.write(j, 0, i);}
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            throw new Error();
        }
    }
}
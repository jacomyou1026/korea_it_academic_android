package com.example.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "POSITION";
    private Context context;

    public DBHelper(Context context){
        super(context,DB_NAME,null ,1);
        this.context = context;
        if(selectAllPosition().size() == 0)
            setList();
    }

    public void setList() {
        String[] data = null;
        InputStream inputStream = context.getResources().openRawResource(R.raw.position);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while(true) {
                String str = br.readLine();
                if(str == null)
                    break;
                data = str.split("\t");
                add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name ='POSITION'" , null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            Log.i("DB_TABLE", "onCreate: 해당 테이블이 이미 존재");

        }else{
            Log.i("DB_TABLE", "onCreate: 해당 테이블이 생성됨");
            String sql = "CREATE TABLE POSITION(ID INTEGER PRIMARY KEY AUTOINCREMENT,CITY TEXT,GU TEXT,DONG TEXT,NX TEXT,NY TEXT)";
            sqLiteDatabase.execSQL(sql);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql =
                "DROP TABLE IF EXISTS ADDRESS";
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);

    }
    public void add(String arr[]) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("result", "add: "+Arrays.toString(arr));
        ContentValues values = new ContentValues();
        values.put("CITY", arr[0]);
        values.put("GU", arr[1]);
        values.put("DONG", arr[2]);
        values.put("NX", arr[3]);
        values.put("NY", arr[4]);

        db.insert("POSITION", null, values);
        db.close();
    }


    public ArrayList<String[]> selectAllPosition(){
        ArrayList<String[]> list = new ArrayList<String[]>();
        String sql = "SELECT * FROM POSITION";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {
                list.add(new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)});
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public ArrayList<String> selectCity(){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT DISTINCT CITY FROM POSITION";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
    public ArrayList<String> selectGu(String city){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "SELECT DISTINCT GU FROM POSITION WHERE CITY like '"+city+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            do {
                if(cursor.getString(0).equals(" "))
                    list.add("-");
                else
                    list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
    public String[] selectPosition(String city){
        String[] list = new String[2];
        String sql = "SELECT NX, NY FROM POSITION WHERE CITY like '"+city+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
                list[0] = cursor.getString(0);
                list[1] = cursor.getString(1);

        }
        db.close();
        return list;
    }
    public String[] selectPosition(String city, String gu){
        String[] list = new String[2];
        String sql = "SELECT NX, NY FROM POSITION WHERE CITY like '"+city+"' and GU like '"+gu+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            list[0] = cursor.getString(0);
            list[1] = cursor.getString(1);

        }
        db.close();
        return list;
    }
}

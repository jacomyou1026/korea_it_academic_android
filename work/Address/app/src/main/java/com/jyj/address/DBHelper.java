package com.jyj.address;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Address";
    private final String TAG = "DBHelper";


    //context,DB명,
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 3);

    }

    //최초에 한번실행
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate");
        Cursor cursor = sqLiteDatabase
                .rawQuery("select name from sqlite_master where type='table' and name='ADDRESS'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0)
            Log.d(TAG, "onCreate: 테이블이 이미 존재 합니다.");
        else {
            Log.d(TAG, "onCreate: 테이블이 생성.");
            String sql = "create table address(id integer primary key autoincrement, name text, tel text)";
            sqLiteDatabase.execSQL(sql);
        }
    }

    //DB version바뀌면 수정하는 부분
    //DB버전이 바뀌면 sql문이 바뀔 가능성이 있다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade");
        String sql = "drop table if exists address";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public long add(AddressVO vo) {
        //쓰기용
        SQLiteDatabase db = this.getWritableDatabase();

        //등록 데이터, 수정데이터 넣어줌
        //id 는 autoincrement이기에 안해주어도 됨
        ContentValues values = new ContentValues();
        values.put("name", vo.getName());
        values.put("tel", vo.getTel());

        //테이블명,컬럼체크,
        long result = db.insert("Address", null, values); //데이터 삽입
        Log.d(TAG, "add: " + result);
        db.close();
        return result;
    }


    public List<AddressVO> selectAllAdressVO() {
        ArrayList<AddressVO> list = new ArrayList<>();
        String sql = "select * from address";
        //읽을때  - this.getReadableDatabase()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        Log.d(TAG, "selectAllAdressVO : "+cursor.getCount());

        if(cursor.moveToFirst()){
         do {
             list.add(new AddressVO(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
         }while (cursor.moveToNext()); //다음데이터 이동
        }
        cursor.close();
        db.close();
        return list;

    }

    public List<AddressVO> selectAddressVO(String serach){
        ArrayList<AddressVO> list = new ArrayList<>();
        String sql = "select * from address where name like '%'|| ? || '%' or tel like '%'|| ? || '%' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{serach,serach});
        Log.d(TAG, "selectAdressVO : "+cursor.getCount());

        if(cursor.moveToFirst()){
            do {
                list.add(new AddressVO(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext()); //다음데이터 이동
        }
        cursor.close();
        db.close();

        return list;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //delete()인수 : 테이블명,조건식(where절),인자값-배열 : ?에 들어갈 데이터들 / 타입신경안쓰고 물음표에 들어갈거 순서대로작성
        int result = db.delete("address","id=?",new String[]{String.valueOf(id)});
        Log.d(TAG,"delete : "+result);
        db.close();

    }

    public void update(AddressVO vo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",vo.getName());
        values.put("tel",vo.getTel());
        int result = db.update("address",values,"id=?",new String[]{String.valueOf(vo.getId())});
        Log.d(TAG,"update : "+result);
        db.close();
    }
}
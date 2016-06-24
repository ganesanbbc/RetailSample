package com.cognizant.sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ProductDB.db";
    public static final String PRODUCT_TABLE_NAME = "ProductTable";
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_IMG = "img";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_ADD_CART = "card";
    private String[] productName={"Microwave oven","Television","Vacuum Cleaner","Table","Chair","Almirah"};
    private String[] productImg={"microwave_oven_img","television_img","vacuum_cleaner_img","table_img","chair_img","almirah_img"};
    private Integer[] productPrice={4999,9999,3999,1999,999,2999};
    private int total;


    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists "+PRODUCT_TABLE_NAME +
                        "("+PRODUCT_ID+" integer primary key, "
                           +PRODUCT_NAME+" text,"
                           +PRODUCT_IMG+" text,"
                           +PRODUCT_PRICE+" integer,"
                           +PRODUCT_ADD_CART+" text)"
        );
        if((int)(DatabaseUtils.queryNumEntries(db,PRODUCT_TABLE_NAME))== 0)
        insertProducts(db);
    }

    public void insertProducts(SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        for(int i=0 ;i< productName.length;i++) {
            contentValues.put(PRODUCT_ID, i);
            contentValues.put(PRODUCT_NAME, productName[i]);
            contentValues.put(PRODUCT_IMG, productImg[i]);
            contentValues.put(PRODUCT_PRICE, productPrice[i]);
            contentValues.put(PRODUCT_ADD_CART, "false");
            db.insert(PRODUCT_TABLE_NAME,null,contentValues);
        }
    }

    public Cursor getData(String prdName){
      SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM "+PRODUCT_TABLE_NAME+" WHERE "+PRODUCT_NAME+" = '"+prdName+"'", null );
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean updateCart(String ProductName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =   new ContentValues();
        contentValues.put(PRODUCT_ADD_CART,"true");
        db.update(PRODUCT_TABLE_NAME,contentValues,PRODUCT_NAME+" = ?",new String[]{ProductName});
        return true;
    }

    public ArrayList<HashMap<String,String>> displayCart(){

       ArrayList<HashMap<String,String>> hashMaps = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select "+PRODUCT_NAME+","+PRODUCT_PRICE+" from "+PRODUCT_TABLE_NAME+" where "+PRODUCT_ADD_CART+"='true'",null);
        res.moveToFirst();

        while(res.isAfterLast()== false){
            HashMap<String,String> hashMap=new HashMap<String, String>();
            hashMap.put(PRODUCT_NAME,res.getString(res.getColumnIndex(PRODUCT_NAME)));
            hashMap.put(PRODUCT_PRICE,res.getString(res.getColumnIndex(PRODUCT_PRICE)));
            res.moveToNext();
            hashMaps.add(hashMap);
        }
        return hashMaps;
    }
    public Boolean removeCart(String ProductName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =   new ContentValues();
        contentValues.put(PRODUCT_ADD_CART,"false");
        db.update(PRODUCT_TABLE_NAME,contentValues,PRODUCT_NAME+" = ?",new String[]{ProductName});
        return true;
    }

    public int getTotal(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select sum(price) from "+PRODUCT_TABLE_NAME+" where "+PRODUCT_ADD_CART+" ='true'", null);
        if(c.moveToFirst()){
            total = c.getInt(0);
        } else {
            total=0;
        }

            return total;
    }
}

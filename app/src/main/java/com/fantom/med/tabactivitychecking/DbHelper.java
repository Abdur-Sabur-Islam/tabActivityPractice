package com.fantom.med.tabactivitychecking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 7/13/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "shopping_database.db";
    public static final int DATABASE_VERSITON =260;

    public static final String TABLE_SHOPINGDATABASE="databasetable";

    public static final String PRODUCT_ID="_id";
    public static final String PRODUCTNAME="pname";
    public static final String PRODUCTQUANTITY="pquantity";
    public static final String PERKG_PRODUCT_PRICE = "perkagprice";
    public static final String TOTAL_PRODUCT_PRICE = "price";

    private String CREATE_TABLE_SHOPPING = "create table "+TABLE_SHOPINGDATABASE+
            "( "+PRODUCT_ID+" integer primary key autoincrement,"+PRODUCTNAME+" text, "+
            PRODUCTQUANTITY+" text, "+PERKG_PRODUCT_PRICE+" text, "+TOTAL_PRODUCT_PRICE+" text);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSITON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SHOPPING);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPINGDATABASE);
        onCreate(db);
    }
}


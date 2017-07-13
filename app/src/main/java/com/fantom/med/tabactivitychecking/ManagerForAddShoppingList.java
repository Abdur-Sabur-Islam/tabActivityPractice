package com.fantom.med.tabactivitychecking;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 7/13/2017.
 */

public class ManagerForAddShoppingList {
    private Activity mContext;
    DbHelper database;

    public ManagerForAddShoppingList(Activity mContext) {
        this.mContext = mContext;
        database = new DbHelper(mContext);
    }

    public long addShoppingList(ShoppingRequireData shopData){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.PRODUCTNAME,shopData.getProductName());
        contentValues.put(DbHelper.PRODUCTQUANTITY,shopData.getProductQuintity());
        contentValues.put(DbHelper.PERKG_PRODUCT_PRICE,shopData.getProductPricePerKg());
        contentValues.put(DbHelper.TOTAL_PRODUCT_PRICE,shopData.getProductTotalPrice());
        long result = sqLiteDatabase.insert(DbHelper.TABLE_SHOPINGDATABASE,null,contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateShopingList(ShoppingRequireData shopData, int id){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.PRODUCTNAME,shopData.getProductName());
        contentValues.put(DbHelper.PRODUCTQUANTITY,shopData.getProductQuintity());
        contentValues.put(DbHelper.PERKG_PRODUCT_PRICE,shopData.getProductPricePerKg());
        contentValues.put(DbHelper.TOTAL_PRODUCT_PRICE,shopData.getProductTotalPrice());

        long result = sqLiteDatabase.update(DbHelper.TABLE_SHOPINGDATABASE,contentValues,DbHelper.PRODUCT_ID+"=?"
                ,new String[]{String.valueOf(id)});

        sqLiteDatabase.close();
        return result;
    }

    public long deleteShopinList(int id){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        long result = sqLiteDatabase.delete(DbHelper.TABLE_SHOPINGDATABASE,DbHelper.PRODUCT_ID+"=?",
                new String[]{String.valueOf(id)});
        return result;
    }
    public ArrayList<ShoppingRequireData> getShopingList(){

        ArrayList<ShoppingRequireData> lists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        String selectQuery = "SELECT*FROM "+ DbHelper.TABLE_SHOPINGDATABASE;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(DbHelper.PRODUCT_ID));
                String productName = cursor.getString(cursor.getColumnIndex(DbHelper.PRODUCTNAME));
                String productQuantity = cursor.getString(cursor.getColumnIndex(DbHelper.PRODUCTQUANTITY));
                String productPricePerKg = cursor.getString(cursor.getColumnIndex(DbHelper.PERKG_PRODUCT_PRICE));
                String productPriceTotal = cursor.getString(cursor.getColumnIndex(DbHelper.TOTAL_PRODUCT_PRICE));
                ShoppingRequireData shoppingRequireData = new ShoppingRequireData(id,productName,productQuantity,productPricePerKg,productPriceTotal);
                lists.add(shoppingRequireData);
            }while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return lists;
    }

    public ShoppingRequireData getShopList(int id){
        String selectQuery = "SELECT*FROM "+ DbHelper.TABLE_SHOPINGDATABASE+ " where " +DbHelper.PRODUCT_ID+" = "+id;
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        ShoppingRequireData shoppingRequireData = null;
        if(cursor.moveToFirst()){
            do{
                int productId = cursor.getInt(cursor.getColumnIndex(DbHelper.PRODUCT_ID));
                String productName = cursor.getString(cursor.getColumnIndex(DbHelper.PRODUCTNAME));
                String productQuantity = cursor.getString(cursor.getColumnIndex(DbHelper.PRODUCTQUANTITY));
                String productPricePerKg = cursor.getString(cursor.getColumnIndex(DbHelper.PERKG_PRODUCT_PRICE));
                String productPriceTotal = cursor.getString(cursor.getColumnIndex(DbHelper.TOTAL_PRODUCT_PRICE));
                shoppingRequireData = new ShoppingRequireData(productId,productName,productQuantity,productPricePerKg,productPriceTotal);
            }while (cursor.moveToNext());
        }
        return shoppingRequireData;
    }
}
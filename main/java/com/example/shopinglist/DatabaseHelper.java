package com.example.shopinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static  final String DATABASE_NAME = "ShoppingList.db";
    public  static  final String TABLE_NAME = "shopping_list";
    public  static  final String Collumn1 = "id";
    public  static  final String Collumn2 = "name";
    public  static  final String Collumn3 = "price";
    public  static  final String Collumn4 = "how_many";
    public  static  final String Collumn5 = "is_purchased";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT, name Text, price DOUBLE, how_many INTEGER, is_purchased INTEGER DEFAULT 0 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists " + TABLE_NAME);
        onCreate(db);
    }
}

package com.example.myshop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HelperSql extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myshop.db";

    public HelperSql( Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }
    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + UserDBContext.UserEntry.TABLE_NAME + " (" +
                    UserDBContext.UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserDBContext.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    UserDBContext.UserEntry.COLUMN_NAME_USER_PASSWORD + " TEXT," +
                   // UserDbContext.UserEntry.COLUMN_USER_UPDATE + " TEXT," +
                    UserDBContext.UserEntry.COLUMN_NAME_USER_ROLE + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_PRODUCT =
            "CREATE TABLE " + ProductDBContext.ProductEntry.TABLE_NAME + " (" +
                    ProductDBContext.ProductEntry._ID + " INTEGER PRIMARY KEY," +
                    ProductDBContext.ProductEntry.COLUMN_PRODUCT_NAME  + " TEXT," +
                    ProductDBContext.ProductEntry.COLUMN_PRODUCT_DESC  + " TEXT," +
                    ProductDBContext.ProductEntry.COLUMN_PRODUCT_IMG  + " TEXT," +
                    ProductDBContext.ProductEntry.COLUMN_PRODUCT_Quantity  + " TEXT," +
                    ProductDBContext.ProductEntry.COLUMN_PRODUCT_PRICES  + " REAL)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_USER);
        db.execSQL(SQL_CREATE_ENTRIES_PRODUCT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(UserDBContext.SQL_DELETE_USER_ENTRIES);
        onCreate(db);
    }
}

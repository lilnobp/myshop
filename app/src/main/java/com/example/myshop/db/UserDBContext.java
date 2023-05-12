package com.example.myshop.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.myshop.model.User;

import java.util.ArrayList;

public final class UserDBContext {

    private final HelperSql helperSql;

    public static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public UserDBContext(Context context){
        this.helperSql = new HelperSql(context);
    }
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_USER_PASSWORD = "password";
        public static final String COLUMN_NAME_USER_ROLE = "role";
    }

    public void addUserToDb(User user, boolean isServer){

        SQLiteDatabase db = helperSql.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(UserEntry.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(UserEntry.COLUMN_NAME_USER_PASSWORD, user.getPassword());
        values.put(UserEntry.COLUMN_NAME_USER_ROLE, user.getRole());
        // Insert the new row, returning the primary key value of the new row
        db.insert(UserEntry.TABLE_NAME, null, values);

    }

    public ArrayList<User> getAllUsers(){
        SQLiteDatabase db = helperSql.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserEntry.COLUMN_NAME_USERNAME,
                UserEntry.COLUMN_NAME_USER_PASSWORD,
                UserEntry.COLUMN_NAME_USER_ROLE
        };


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserEntry.COLUMN_NAME_USER_ROLE + " DESC";

        Cursor cursor = db.query(
                UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<User> users = new ArrayList<>();


        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(UserEntry._ID));
            String username = cursor.getString( cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_USERNAME));
            String password = cursor.getString( cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_USER_PASSWORD));
            String role = cursor.getString( cursor.getColumnIndexOrThrow(UserEntry.COLUMN_NAME_USER_ROLE));
            //boolean serverUpdate = Boolean.parseBoolean(cursor.getString( cursor.getColumnIndexOrThrow(UserEntry.COLUMN_USER_UPDATE)));
            User user = new User(username,password);

            user.setRole(role);

            users.add(user);
        }
        cursor.close();
        return users;
    }




}


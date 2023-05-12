package com.example.myshop.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.myshop.model.Product;

import java.util.ArrayList;

public class ProductDBContext  {

    private final HelperSql helperSql;

    /*
    public static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductDbContext.ProductEntry.TABLE_NAME;*/

    public ProductDBContext(Context context) {
        this.helperSql = new HelperSql(context);
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_PRODUCT_NAME = "product_name";

        public static final String COLUMN_PRODUCT_Quantity = "product_quantity";

        public static final String COLUMN_PRODUCT_DESC = "product_desc";

        public static final String COLUMN_PRODUCT_IMG = "product_image";
        public static final String COLUMN_PRODUCT_PRICES = "product_prices";
    }

    public Object getObjectFromId(long Id) {
        SQLiteDatabase db = helperSql.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICES,
                ProductEntry.COLUMN_PRODUCT_Quantity,
                ProductEntry.COLUMN_PRODUCT_IMG,
                ProductEntry.COLUMN_PRODUCT_DESC

        };
        String selection = ProductEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(Id)};

        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null

        );
        Product product = new Product();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ProductEntry._ID));

            int product_qtty = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_Quantity));

            String product_img = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_IMG));
            String product_name = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
            String product_desc = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_DESC));
            double prices = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_PRICES));
            product.setPrices(prices);
            product.setId(id);
            product.setDescription(product_desc);
            product.setId(id);
            product.setQuantity(product_qtty);
            product.setName(product_name);
            product.setImageLink(product_img);

        }
        cursor.close();
        return product;
    }



    public void addNewElement(Object element, boolean serverUpdate) {
        SQLiteDatabase db = helperSql.getWritableDatabase();

        Product product = (Product) element;
        ContentValues values = new ContentValues();
        values.put(ProductEntry._ID, product.getId());
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, product.getName());
        values.put(ProductEntry.COLUMN_PRODUCT_Quantity, product.getQuantity());
        values.put(ProductEntry.COLUMN_PRODUCT_PRICES, product.getPrices());
        values.put(ProductEntry.COLUMN_PRODUCT_DESC, product.getDescription());
        values.put(ProductEntry.COLUMN_PRODUCT_IMG, product.getImageLink());

        db.insert(ProductEntry.TABLE_NAME, null, values);
    }


    public ArrayList<Product> getAllProducts() {
        SQLiteDatabase db = helperSql.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_PRICES,
                ProductEntry.COLUMN_PRODUCT_Quantity,
                ProductEntry.COLUMN_PRODUCT_IMG,
                ProductEntry.COLUMN_PRODUCT_DESC

        };


        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null

        );
        ArrayList<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ProductEntry._ID));

            int product_qtty = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_Quantity));

            String product_img = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_IMG));
            String product_name = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
            String product_desc = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_DESC));
            double prices = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_PRICES));
            Product product = new Product();
            product.setPrices(prices);
            product.setId(id);
            product.setDescription(product_desc);
            product.setId(id);
            product.setQuantity(product_qtty);
            product.setName(product_name);
            product.setImageLink(product_img);
            products.add(product);
        }
        cursor.close();
        return products;
    }
}
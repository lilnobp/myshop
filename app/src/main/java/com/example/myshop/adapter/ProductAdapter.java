package com.example.myshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;

import com.example.myshop.model.Product;

public class ProductAdapter extends BaseAdapter {

    private Context context;


    private  Product[] products;

    public ProductAdapter(Context context, Product[] products){
        this.context = context;
        this.products = products;

    }
    @Override
    public int getCount() {
        return products.length;
    }

    @Override
    public Object getItem(int position) {
        return products[position];
    }

    @Override
    public long getItemId(int position) {
        return products[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = products[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.product_list, null);
        }
        final ImageView imageProduct = convertView.findViewById(R.id.image_product);
        final TextView productName = convertView.findViewById(R.id.etProductName);
        final TextView productQuantity = convertView.findViewById(R.id.etQuantity);
        final TextView productPrices = (TextView)convertView.findViewById(R.id.etPrices);

        //new DownloadImageTask(imageProduct,"https://i.imgur.com/DvpvklR.png")
                //.execute("https://i.imgur.com/DvpvklR.png");

        //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageProduct);
        Glide.with(context).load(product.getImageLink()).into(imageProduct);
        productName.setText(product.getName());
        productQuantity.setText("Quantity in Stock : " + product.getQuantity());
        productPrices.setText(product.getPrices() +" €");

        return convertView;
    }
}

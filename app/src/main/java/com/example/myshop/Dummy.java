package com.example.myshop;

import com.example.myshop.model.Product;

import java.util.ArrayList;

public class Dummy {

    public static Product[] getDummyProducts(){
        ArrayList<Product>products = new ArrayList<>();


        Product product3 = new Product();
        product3.setId(2002);
        product3.setDescription("Erst kürzlich hatte Toyota durch die Blume mitgeteilt, dass vorerst keine weiteren reinen GR-Modelle geplant sind. Aber es gibt bald eine leicht sportliche Variante des weltweit sehr erfolgreichen RAV4 in Gestalt des neuen Toyota RAV4 GR SPORT.  ");
        product3.setImageLink("https://cdn.motor1.com/images/mgl/mMxq0P/s3/toyota-rav4-gr-sport.jpg");
        product3.setName("Toyota Rav4 2021 2.0 ");
        product3.setPrices(999.99);
        product3.setQuantity(2);
        products.add(product3);

        return products.toArray(new Product[0]);
    }
}

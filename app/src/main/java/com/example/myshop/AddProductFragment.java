package com.example.myshop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myshop.db.ProductDBContext;
import com.example.myshop.model.Product;


public class AddProductFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_add_product, container, false);

        Button btnBack = viewGroup.findViewById(R.id.btnback);

        Button btnAddProduct = viewGroup.findViewById(R.id.btnAddProduct);
        EditText nameProduct = viewGroup.findViewById(R.id.etNameProduct);
        EditText productPrice = viewGroup.findViewById(R.id.etProductPrice);
        EditText productDec =  viewGroup.findViewById(R.id.etProductDesc);
        ImageView productImage =  viewGroup.findViewById(R.id.etValidImage);

        Button btnValidImage = viewGroup.findViewById(R.id.btnImageValid);

        EditText productImageLink =  viewGroup.findViewById(R.id.etImageLink);

        btnValidImage.setOnClickListener(view -> {
            Glide.with(this.getContext()).load(productImageLink.getText().toString()).into(productImage);
        });



        btnBack.setOnClickListener(view -> {
            NavHostFragment.findNavController(AddProductFragment.this)
                    .navigate(R.id.action_addProductFragment_to_FirstFragment);
        });

        btnAddProduct.setOnClickListener(view -> {
            Product product = new Product();
            product.setId(2000 + LoginFragment.products.length + 1);
            product.setDescription(productDec.getText().toString());
            product.setImageLink(productImageLink.getText().toString());
            product.setName(nameProduct.getText().toString());
            product.setPrices(new Double(productPrice.getText().toString()));
            product.setQuantity(1);
            new ProductDBContext(view.getContext()).addNewElement(product,false);
            LoginFragment.products = new ProductDBContext(this.getContext()).getAllProducts().toArray(new Product[0]);
            NavHostFragment.findNavController(AddProductFragment.this)
                    .navigate(R.id.action_addProductFragment_to_FirstFragment);
        });

         return  viewGroup;

    }
}
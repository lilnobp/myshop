package com.example.myshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.myshop.databinding.FragmentSecondBinding;
import com.example.myshop.model.Product;


public class ProductDetailsFragment extends Fragment {

    private FragmentSecondBinding binding;
    public static Product selectedProduct;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(binding.etDImage);
        Glide.with(this.getContext()).load(selectedProduct.getImageLink()).into(binding.etDImage);
        binding.etProductDesc.setText(selectedProduct.getDescription());
        binding.etNameProduct.setText(selectedProduct.getName());
        binding.etProductPrice.setText(selectedProduct.getPrices() + " €");


        binding.btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductDetailsFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
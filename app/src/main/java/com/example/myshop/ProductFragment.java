package com.example.myshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myshop.adapter.ProductAdapter;
import com.example.myshop.databinding.FragmentFirstBinding;
import com.example.myshop.db.ProductDBContext;

public class ProductFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductAdapter productAdapter = new ProductAdapter(this.getContext(), LoginFragment.products);
        binding.gridProduct.setAdapter(productAdapter);

        binding.gridProduct.setOnItemClickListener((adapterView, view1, i, l) -> {

            ProductDetailsFragment.selectedProduct = new ProductDBContext(view.getContext()).getAllProducts().get(i);
            NavHostFragment.findNavController(ProductFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
        /*
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_s_main, container, false);
        GridView stockMenuList = root.findViewById(R.id.menuList);
        MenuAdapter menuAdapter = new MenuAdapter(Dummy.getStockMenus(),this.getContext());
        stockMenuList.setAdapter(menuAdapter);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProductFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

         */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
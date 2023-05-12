package com.example.myshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myshop.databinding.FragmentSecond2Binding;
import com.example.myshop.db.UserDBContext;
import com.example.myshop.model.User;

public class RegisterFragment extends Fragment {

    private FragmentSecond2Binding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecond2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegister.setOnClickListener(view1 -> {
            User user = new User(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
            user.setRole("normale");
            UserDBContext userDBContext = new UserDBContext(view1.getContext());
            userDBContext.addUserToDb(user, false);
            NavHostFragment.findNavController(RegisterFragment.this)
                    .navigate(R.id.action_Second2Fragment_to_First2Fragment);
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_Second2Fragment_to_First2Fragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package com.example.myshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myshop.databinding.FragmentFirst2Binding;
import com.example.myshop.db.ProductDBContext;
import com.example.myshop.db.UserDBContext;
import com.example.myshop.model.Product;
import com.example.myshop.model.User;


public class LoginFragment extends Fragment {

    private FragmentFirst2Binding binding;
    public static Product [] products;
    private static User[] users;
    public static User connectedUser;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirst2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        products = new ProductDBContext(this.getContext()).getAllProducts().toArray(new Product[0]);
        users = new UserDBContext(this.getContext()).getAllUsers().toArray(new User[0]);

        // to initially add the  super user, when application start
        if(users.length==0){
            User user = new User("testUser","12345");
            user.setRole("admin");
            new UserDBContext(this.getContext()).addUserToDb(user,false);
        }

        // to initially add 2 products, when application start

        if(products.length==0){

            ProductDBContext productDBContext = new ProductDBContext(this.getContext());
            Product product1 = new Product();
            product1.setId(2000);
            product1.setDescription("The all new lighter and slimmer PlayStation4 system has a 1TB hard drive for all of the greatest games, TV, music and more. Incredible Games You've come to the right place. Exclusive games take you on incredible journeys, from critically acclaimed indies to award winning AAA hits. Endless Entertainment Something new and amazing is always in reach. Find what you're looking for and get it at the touch of a button via PlayStation entertainment options like PlayStation Vue and more. Connect your controller to your PS4 system with the USB cable: ");
            product1.setImageLink("https://cdn.lesnumeriques.com/optim/produits/16/34465/sony-playstation-4-slim_e4f20c8dbe94f8b2__1200_675__overflow.jpg");
            product1.setName("PS4 Slim 4 256 Gigabits");
            product1.setPrices(299.99);
            product1.setQuantity(20);
            productDBContext.addNewElement(product1,false);

            Product product2 = new Product();
            product2.setId(2001);
            product2.setDescription("Dieses Produkt bei euch zu Hause\n" +
                    "Lass dich von ausgewählten Instagram-Styles inspirieren. Oder möchtest auch du andere mit deinen Wohnideen begeistern?\n" +
                    "Dann tagge dein Foto mit @home24_de – die schönsten Posts erscheinen auf der jeweiligen Produktseite.");
            product2.setImageLink("https://shop.static.ingka.ikea.com/category-images/Category_sofas-and-armchairs.jpg");
            product2.setName("Das Geheimrezept für eine gemütliche Atmosphäre daheim: Bequeme Sofas");
            product2.setPrices(1299.99);
            product2.setQuantity(20);
            productDBContext.addNewElement(product2,false);

        }


        binding.etSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_First2Fragment_to_Second2Fragment);
            }
        });
        binding.btnLogin.setOnClickListener(view1 -> {
            login();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void login(){

        if(!binding.etUsername.getText().toString().isEmpty() ||binding.etUsername.getText() != null ){
            if(!binding.etPassword.getText().toString().isEmpty()|| binding.etPassword.getText() != null){

                for (User user : users) {
                    String username = binding.etUsername.getText().toString().toLowerCase().trim();
                    String password = binding.etPassword.getText().toString();
                    if(user.getUsername().toLowerCase().trim().equals(binding.etUsername.getText().toString().toLowerCase().trim())
                            && (user.getPassword().equals(binding.etPassword.getText().toString()))){
                        connectedUser = user;
                        Intent switchActivityIntent = new Intent(this.getActivity(), HomeActivity.class);
                        startActivity(switchActivityIntent);
                        //.getActivity().finish();
                    }
                }
            }
            else  {
                Toast.makeText(this.getContext(),"Please Provide a Password ", Toast.LENGTH_LONG).show();
            }
        }
        else  {
            Toast.makeText(this.getContext(),"Please Provide a Username ", Toast.LENGTH_LONG).show();
        }
    }

}
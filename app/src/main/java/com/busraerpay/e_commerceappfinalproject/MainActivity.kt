package com.busraerpay.e_commerceappfinalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.busraerpay.e_commerceappfinalproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    //kullanıcının otomatik giriş yapmasını sağlayacağız eğer önceden giriş yapmışsa. direk mainpagefragmente yönlendirilcek
    private lateinit var auth: FirebaseAuth
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment

        val navController= navHostFragment.navController

        val bottomNavigation: BottomNavigationView = binding.bottomNavigationView
        bottomNavigation.setupWithNavController(navController)

        //bottom visibility
        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.signupFragment ||
                destination.id == R.id.loginFragment ||
                destination.id == R.id.forgotPasswordFragment ||
                destination.id == R.id.productDetailFragment){

                binding.bottomNavigationView.visibility= View.GONE
            }else{
                binding.bottomNavigationView.visibility= View.VISIBLE
            }
        }
    }




}
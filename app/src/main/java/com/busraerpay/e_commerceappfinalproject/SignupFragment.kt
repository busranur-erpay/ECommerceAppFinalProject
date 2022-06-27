package com.busraerpay.e_commerceappfinalproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentSignupBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupFragment : Fragment() {
    private lateinit var button: Button
    private lateinit var fragmentSignupBinding: FragmentSignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view=inflater.inflate(R.layout.fragment_signup, container, false)
        fragmentSignupBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false)
       // auth = Firebase.auth
       /*
        button=view.findViewById(R.id.button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }*/
        return fragmentSignupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        auth.currentUser?.let {
            findNavController().navigate(R.id.action_signupFragment_to_nav_shopping)
        }

        fragmentSignupBinding.signupButton.setOnClickListener {

            singupUser()

            //findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        fragmentSignupBinding.haveaccountText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }


    private  fun singupUser(){
        //eposta ve password alınıp giriş işlemi yapılacak.
        //şifre ve eposta boş ise TOAST mesajı göster ve girişi kabul etme
        auth.createUserWithEmailAndPassword(
            fragmentSignupBinding.signupEmailInput.text.toString(),
            fragmentSignupBinding.signupPasswordInput.text.toString())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    saveUserDb()
                    Log.v("AKBANK", "SUCCES")
                    findNavController().navigate(R.id.action_signupFragment_to_nav_shopping)
                   /*
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user) */
                } else {
                    // If sign in fails, display a message to the user.
                    //text bölümüne taskın içinde oluşan bir hata varsa eğer bu hatanın mesajını göstersin
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }

    fun saveUserDb(){

    }

}
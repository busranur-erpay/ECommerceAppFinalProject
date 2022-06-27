package com.busraerpay.e_commerceappfinalproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordFragment : Fragment() {
    private lateinit var fragmentForgotPasswordBinding: FragmentForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentForgotPasswordBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_forgot_password,container,false)
        return fragmentForgotPasswordBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentForgotPasswordBinding.forgotButton.setOnClickListener {
            verificationEmail()
        }

        fragmentForgotPasswordBinding.forgotBack.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
    }

    private  fun verificationEmail(){

        auth.sendPasswordResetEmail(fragmentForgotPasswordBinding.forgotEmailInput.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.v("VERIFICATION", "Email sent.")
                    Toast.makeText(context, "Email sent", Toast.LENGTH_SHORT).show()
                }
            }
    }


}
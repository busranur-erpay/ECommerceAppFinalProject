package com.busraerpay.e_commerceappfinalproject

import android.content.ContentValues
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
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentLoginBinding
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var fragmentLoginBinding: FragmentLoginBinding
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
        fragmentLoginBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return fragmentLoginBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       fragmentLoginBinding.loginButton.setOnClickListener {
           loginUser()
            //findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        fragmentLoginBinding.forgotpasswordText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        fragmentLoginBinding.loginBack.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }


    private  fun loginUser(){

        auth.signInWithEmailAndPassword(
            fragmentLoginBinding.loginEmailInput.text.toString(),
            fragmentLoginBinding.loginPasswordInput.text.toString())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    Log.v("AKBANK", "LOGIN SUCCES")
                    findNavController().navigate(R.id.action_loginFragment_to_nav_shopping)
                    /*
                     // Sign in success, update UI with the signed-in user's information
                     Log.d(TAG, "createUserWithEmail:success")
                     val user = auth.currentUser
                     //updateUI(user) */
                } else {
                    // If sign in fails, display a message to the user.
                    //text b??l??m??ne task??n i??inde olu??an bir hata varsa e??er bu hatan??n mesaj??n?? g??stersin
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(activity, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    //updateUI(null)
/*
//HATALARI G??STER VE ??CON EKLE
                    try {
                        throw task.exception!!
                    }catch (FirebaseAuthWeakPasswordException e){
                        fragmentLoginBinding.loginPasswordInput.setError("error",resources.getDrawable(R.drawable.ic_outline_lock))
                        fragmentLoginBinding.loginPasswordInput.requestFocus()
                    }
*/
                    }
            }


    }

}


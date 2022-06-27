package com.busraerpay.e_commerceappfinalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {

    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentProfileBinding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        return fragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentProfileBinding.button3.setOnClickListener {
            signoutUser()
           findNavController().navigate(R.id.action_profile_to_mainActivity)
        }

    }

    private fun signoutUser(){
        auth.signOut()
        Log.v("SIGNOUT","SUCCESS")
        Toast.makeText(activity, "CIKIS BAŞARALI", Toast.LENGTH_SHORT).show()
    }

}
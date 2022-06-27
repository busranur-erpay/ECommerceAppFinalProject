package com.busraerpay.e_commerceappfinalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.busraerpay.e_commerceappfinalproject.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    private lateinit var fragmentSuccessBinding: FragmentSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSuccessBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_success,container,false)

        return fragmentSuccessBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSuccessBinding.successButton.setOnClickListener {
            findNavController().navigate(R.id.action_successFragment_to_home)
        }
    }


}
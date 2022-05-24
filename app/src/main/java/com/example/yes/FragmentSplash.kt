package com.example.yes

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yes.databinding.FragmentSplashBinding


class FragmentSplash : Fragment() {

    lateinit var binding : FragmentSplashBinding
    lateinit var handler : Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler()
        handler.postDelayed({
            findNavController().navigate(R.id.action_fragmentSplash_to_fragmentLogin)
        },3000)
    }



}
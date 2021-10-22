package com.example.sephora.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sephora.R
import com.example.sephora.databinding.FragmentSplashBinding
import com.example.sephora.ui.MainActivity
import kotlinx.coroutines.*

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            findNavController().navigate(R.id.action_splash_to_productList, savedInstanceState)
        }

        return FragmentSplashBinding.inflate(inflater, container, false).root
    }
}
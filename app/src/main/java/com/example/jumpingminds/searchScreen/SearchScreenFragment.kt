package com.example.jumpingminds.searchScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jumpingminds.databinding.FragmentSearchScreenBinding

class SearchScreenFragment : Fragment() {
    private lateinit var binding: FragmentSearchScreenBinding
//    private val args: SearchScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Log.i("helloabc",args.category)
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
}
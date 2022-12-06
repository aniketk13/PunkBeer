package com.example.jumpingminds

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds.databinding.FragmentHomeBinding
import com.example.jumpingminds.databinding.FragmentSearchResultBinding
import com.example.jumpingminds.homePage.HomePageAdapter
import com.example.jumpingminds.homePage.HomeViewModel

class SearchResultFragment : Fragment() {

    private lateinit var viewModel: SearchResultViewModel
private lateinit var binding: FragmentSearchResultBinding
    private val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SearchResultViewModel::class.java]
        val  searchText=args.search
        when(args.category){
            "Beer"->viewModel.getBeerByBeerName(searchText)
            "Yeast"->viewModel.getBeerByYeast(searchText)
            "Malt"->viewModel.getBeerByMalt(searchText)
            "Hops"->viewModel.getBeerByHops(searchText)
            "Food"->viewModel.getBeerByFood(searchText)
        }
        viewModel.requests.observe(requireActivity()){
            binding.recyclerView.adapter = HomePageAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL,false)
        }
        return binding.root
    }


}
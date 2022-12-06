package com.example.jumpingminds.SearchResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds.RecyclerAdapter
import com.example.jumpingminds.databinding.FragmentSearchResultBinding

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
        val searchText = args.search
        when (args.category) {
            "Beer" -> viewModel.getBeerByBeerName(searchText)
            "Yeast" -> viewModel.getBeerByYeast(searchText)
            "Malt" -> viewModel.getBeerByMalt(searchText)
            "Hops" -> viewModel.getBeerByHops(searchText)
            "Food" -> viewModel.getBeerByFood(searchText)
        }
        viewModel.requests.observe(requireActivity()) {
            binding.recyclerView.adapter = RecyclerAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, false
            )
        }
        return binding.root
    }


}
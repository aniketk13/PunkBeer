package com.example.jumpingminds.SearchResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds.api.models.Beer
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
            binding.recyclerView.adapter =
                SearchResultAdapter(it, object : SearchResultAdapter.ItemClickListener {
                    override fun onItemClick(signature: Beer) {
                        viewModel.insertBeer(signature)
                        findNavController().navigate(
                            SearchResultFragmentDirections.actionSearchResultFragmentToBeerDetails(
                                signature.id
                            )
                        )
                    }

                })
            binding.recyclerView.layoutManager = LinearLayoutManager(
                activity, LinearLayoutManager.VERTICAL, false
            )
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(SearchResultFragmentDirections.actionSearchResultFragmentToHomeFragment())
            }
        })
    }

}
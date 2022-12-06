package com.example.jumpingminds.homePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds.R
import com.example.jumpingminds.RecyclerAdapter
import com.example.jumpingminds.databinding.FragmentHomeBinding
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var searchText = ""

    // on below line we are creating a variable for our slider adapter.
    lateinit var sliderAdapter: SliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        viewModel.getBeer()

        val filter = resources.getStringArray(R.array.PunkFilter)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, filter)
        binding.dropdownMenu.setAdapter(arrayAdapter)

        binding.searchBeer.queryHint = "Search ${binding.dropdownMenu.text}"
        binding.searchBeer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    searchText = p0.replace(" ", "_")
                    Log.i("helloabc", searchText)

                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToSearchResultFragment(
                            binding.dropdownMenu.text.toString(),
                            searchText
                        )
                    )
                }
                Log.i("helloabc", searchText)
                Log.i("helloabc", binding.dropdownMenu.text.toString())
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
        viewModel.getBeerByABV()
        viewModel.beerABV.observe(requireActivity()) {
            binding.recyclerView2.adapter = RecyclerAdapter(it)
            binding.recyclerView2.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.requests.observe(requireActivity()) {
            binding.recyclerView.adapter = RecyclerAdapter(it)
            binding.recyclerView.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // on below line we are initializing our slider adapter and adding our list to it.
            sliderAdapter = SliderAdapter(it)
            // on below line we are setting auto cycle direction for our slider view from left to right.
            binding.imageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR

            // on below line we are setting adapter for our slider.
            binding.imageSlider.setSliderAdapter(sliderAdapter)

            // on below line we are setting scroll time in seconds for our slider view.
            binding.imageSlider.scrollTimeInSec = 3

            // on below line we are setting auto cycle to true to auto slide our items.
            binding.imageSlider.isAutoCycle = true

            // on below line we are calling start auto cycle to start our cycle.
            binding.imageSlider.startAutoCycle()
        }
        return binding.root
    }


}
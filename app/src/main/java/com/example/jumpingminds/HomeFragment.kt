package com.example.jumpingminds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jumpingminds.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var searchText=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        viewModel.getBeer()
        val filter=resources.getStringArray(R.array.PunkFilter)
        val arrayAdapter=ArrayAdapter(requireContext(),R.layout.dropdown_item,filter)
        binding.dropdownMenu.setAdapter(arrayAdapter)
        binding.searchBeer.queryHint="Search ${binding.dropdownMenu.text}"
//        binding.dropdownMenu.onItemClickListener= AdapterView.OnItemClickListener{}
        binding.searchBeer.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    searchText=p0.replace(" ","_")
                }
                when(binding.dropdownMenu.text.toString()){
                    "Beer"->viewModel.getBeerByBeerName(searchText)
                    "Yeast"->viewModel.getBeerByYeast(searchText)
                    "Malt"->viewModel.getBeerByMalt(searchText)
                    "Hops"->viewModel.getBeerByHops(searchText)
                    "Food"->viewModel.getBeerByFood(searchText)
                }
                Log.i("helloabc",searchText)
                Log.i("helloabc",binding.dropdownMenu.text.toString())
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
        viewModel.requests.observe(requireActivity()){
            binding.recyclerView.adapter = HomePageAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        }
        return binding.root
    }


}
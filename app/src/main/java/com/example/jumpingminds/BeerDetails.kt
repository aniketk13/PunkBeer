package com.example.jumpingminds

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jumpingminds.databinding.FragmentBeerDetailsBinding
import com.example.jumpingminds.homePage.SliderAdapter
import com.smarteist.autoimageslider.SliderView

class BeerDetails : Fragment() {
    private lateinit var viewModel: BeerDetailsViewModel
    private lateinit var binding: FragmentBeerDetailsBinding
    private val args: BeerDetailsArgs by navArgs()
    lateinit var sliderAdapter: SliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeerDetailsBinding.inflate(inflater, container, false)
        Log.i("helloabc", args.id)
        viewModel = ViewModelProvider(requireActivity())[BeerDetailsViewModel::class.java]
        viewModel.getBeerById(args.id)

        viewModel.requests.observe(requireActivity()) {
            Log.i("helloabc", it.toString())
            it.add(it[0])
            it.add(it[1])
            // on below line we are initializing our slider adapter and adding our list to it.
            sliderAdapter = SliderAdapter(it)
            // on below line we are setting auto cycle direction for our slider view from left to right.
            binding.descriptionBeerImageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            // on below line we are setting adapter for our slider.
            binding.descriptionBeerImageSlider.setSliderAdapter(sliderAdapter)
            // on below line we are setting scroll time in seconds for our slider view.
            binding.descriptionBeerImageSlider.scrollTimeInSec = 3
            // on below line we are setting auto cycle to true to auto slide our items.
            binding.descriptionBeerImageSlider.isAutoCycle = true
            // on below line we are calling start auto cycle to start our cycle.
            binding.descriptionBeerImageSlider.startAutoCycle()

            binding.descriptionBeerName.text = it[0].name
            binding.descriptionBeerBrewedDate.text = it[0].first_brewed
            binding.descriptionBeerVolume.text = it[0].abv.toString() + "%"
            binding.descriptionBeerTagline.text = it[0].tagline
            binding.descriptionBeerDescription.text = it[0].description


        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(BeerDetailsDirections.actionBeerDetailsToHomeFragment())
            }
        })
    }
}
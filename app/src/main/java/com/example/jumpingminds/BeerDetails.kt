package com.example.jumpingminds

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.text.bold
import androidx.core.text.underline
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jumpingminds.api.models.Beer
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
//        Log.i("helloabc", args.id)
        viewModel = ViewModelProvider(requireActivity())[BeerDetailsViewModel::class.java]
        viewModel.getBeerById(args.id)

        viewModel.requests.observe(requireActivity()) {
            Log.i("helloabc", it.toString())
            it.add(it[0])
            it.add(it[1])
            // on below line we are initializing our slider adapter and adding our list to it.
            sliderAdapter = SliderAdapter(it, object : SliderAdapter.ItemClickListener {
                override fun onItemClick(signature: Beer) {
                }
            })
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
            binding.descriptionBeerBrewedDate.text =
                SpannableStringBuilder().bold { append("First Brewed On:   ") }
                    .append(it[0].first_brewed)
            binding.descriptionBeerVolume.text =
                SpannableStringBuilder().bold { append("Alcohol Concentration:   ") }
                    .append(it[0].abv.toString()).append("%")
            binding.descriptionBeerTagline.text = it[0].tagline
            binding.descriptionBeerDescription.text =
                SpannableStringBuilder().bold {
                    underline { append("Product Description:") }.append(
                        "   "
                    )
                }
                    .append(it[0].description)
            var food_pairing = "\n"
            for (i in it[0].food_pairing) {
                food_pairing = "$food_pairing • $i \n"
            }
            binding.descriptionBeerFoodPairing.text =
                SpannableStringBuilder().bold {
                    underline { append("Best When Paired With:") }.append(
                        "   "
                    )
                }
                    .append(food_pairing)
            binding.descriptionBeerBrewingTips.text =
                SpannableStringBuilder().bold { underline { append("Some Brewing Tips:") }.append("   ") }
                    .append(it[0].brewers_tips)
            var malt = "\n"
            var hops = "\n"
            var yeast = it[0].ingredients.yeast
            for (i in it[0].ingredients.malt) {
                malt = "$malt • ${i.name} - ${i.amount.value} ${i.amount.unit} \n"
            }

            for (i in it[0].ingredients.hops) {
                hops = "$hops • ${i.name} - ${i.amount.value} ${i.amount.unit} \n"
            }
            binding.descriptionBeerIngredients.text =
                SpannableStringBuilder().bold { underline { append("Ingredients:\n") }.append("\nMalt:") }
                    .append(malt).bold { append("\nHops:") }.append(hops)
                    .bold { append("\nYeast:   ") }.append(yeast)


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
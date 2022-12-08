package com.example.jumpingminds.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.jumpingminds.R
import com.example.jumpingminds.api.models.Beer
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

// on below line we are creating a class for slider
// adapter and passing our array list to it.
class SliderAdapter(
    slideList: ArrayList<Beer>, private val itemClickListener: SliderAdapter.ItemClickListener
) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(signature: Beer)
    }

    // on below line we are creating a
    // new array list and initializing it.
    var sliderList: ArrayList<Beer> = slideList

    // on below line we are calling get method
    override fun getCount(): Int {
        // in this method we are returning
        // the size of our slider list.
        return 3
    }

    // on below line we are calling on create view holder method.
    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        // inside this method we are inflating our layout file for our slider view.
        val inflate: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, null)

        // on below line we are simply passing
        // the view to our slider view holder.
        return SliderViewHolder(inflate)
    }

    // on below line we are calling on bind view holder method to set the data to our image view.
    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {

        // on below line we are checking if the view holder is null or not.
        if (viewHolder != null) {
            // if view holder is not null we are simply
            // loading the image inside our image view using glide library
//            Glide.with(viewHolder.itemView).load(sliderList.get(position)).fitCenter()
//                .into(viewHolder.imageView)
            val beer = sliderList[position]
            Picasso.with(viewHolder.itemView.context).load(beer.image_url)
                .into(viewHolder.imageView)
            viewHolder.beerName.text = beer.name
            viewHolder.beerVolume.text = beer.abv.toString() + "%"
            viewHolder.itemView.setOnClickListener {
                itemClickListener.onItemClick(sliderList[position])
            }
//            viewHolder.beerName.text=viewHolder.itemView
        }
    }

    // on below line we are creating a class for slider view holder.
    class SliderViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

        // on below line we are creating a variable for our
        // image view and initializing it with image id.
        var imageView: ImageView = itemView!!.findViewById(R.id.myimage)
        var beerName: TextView = itemView!!.findViewById(R.id.beer_name)
        var beerVolume: TextView = itemView!!.findViewById(R.id.beer_volume)
    }
}
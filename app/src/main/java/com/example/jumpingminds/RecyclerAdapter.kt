package com.example.jumpingminds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jumpingminds.api.models.Beer
import com.squareup.picasso.Picasso

class RecyclerAdapter(
    private val signatureList: ArrayList<Beer>,
//    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

//    interface ItemClickListener {
//        fun onItemClick(signature: Beer, position: Int)
//    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerImage: ImageView = itemView.findViewById(R.id.beerImage)
        val beerName: TextView = itemView.findViewById(R.id.beerName)
        val beerDate: TextView = itemView.findViewById(R.id.beerDate)
        val beerVolume: TextView = itemView.findViewById(R.id.beerVolume)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.punk_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val signature = signatureList[position]
        holder.beerName.text = signature.name
        holder.beerDate.text = signature.first_brewed
        holder.beerVolume.text = signature.abv.toString() + " %"
//        holder.beerImage.setImageURI(signature.image_url.toUri())
        holder.beerImage.clipToOutline = true
        Picasso.with(holder.beerImage.context).load(signature.image_url).into(holder.beerImage)
//        Log.i("helloabc",signature.image_url.toUri().toString())

//        holder.itemView.setOnClickListener {
//            itemClickListener.onItemClick(signatureList[position], position)
//        }
    }

    override fun getItemCount(): Int {
        return signatureList.size
    }
//
//    private fun convertLongToTime(createdAt: Long): String {
//        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
//        val date = simpleDateFormat.format(createdAt * 1000L)
//        return date
//    }
}
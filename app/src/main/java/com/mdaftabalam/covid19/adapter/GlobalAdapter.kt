package com.mdaftabalam.covid19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdaftabalam.covid19.R
import com.mdaftabalam.covid19.model.GlobalModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_global.view.*

class GlobalAdapter(private val globals: ArrayList<GlobalModel>) : RecyclerView.Adapter<GlobalAdapter.ViewHolder>() {

    //Inflate the custom view from xml layout file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_global, parent, false)
        return ViewHolder(v)
    }

    //Display the current user full name and location in view holder custom view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(globals[position].image).into(holder.img)
        holder.name.text = globals[position].name
        holder.case.text = globals[position].case
        holder.death.text = globals[position].death
        holder.recover.text = globals[position].recover
    }

    //Return the size of users list & total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return globals.size
    }

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.imgCountry
        val name: TextView = itemView.txtCountry
        val case: TextView = itemView.txtCase
        val death: TextView = itemView.txtDeath
        val recover: TextView = itemView.txtRecover
    }

    //This two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
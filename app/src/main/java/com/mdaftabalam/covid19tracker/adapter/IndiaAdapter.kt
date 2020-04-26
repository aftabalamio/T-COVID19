package com.mdaftabalam.covid19tracker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mdaftabalam.covid19tracker.R
import com.mdaftabalam.covid19tracker.model.IndiaModel
import kotlinx.android.synthetic.main.item_india.view.*

class IndiaAdapter(private val india: ArrayList<IndiaModel>) : RecyclerView.Adapter<IndiaAdapter.ViewHolder>() {

    //Inflate the custom view from xml layout file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_india, parent, false)
        return ViewHolder(v)
    }

    //Display the current user full name and location in view holder custom view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.state.text = india[position].state
        holder.code.text = india[position].stateCode
        holder.case.text = "Confirmed ${india[position].confirmed}"
        holder.death.text = "Deaths ${india[position].deaths}"
        holder.recover.text = "Recovered ${india[position].recovered}"
        holder.active.text = "Active ${india[position].active}"
        holder.time.text = india[position].updatedTime
    }

    //Return the size of users list & total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return india.size
    }

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val state: TextView = itemView.txtState
        val code: TextView = itemView.txtCode
        val case: TextView = itemView.indiaCase
        val death: TextView = itemView.indiaDeath
        val recover: TextView = itemView.indiaRecover
        val active: TextView = itemView.indiaActive
        val time: TextView = itemView.indiaTime
    }

    //This two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
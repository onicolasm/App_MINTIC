package com.example.descubre_antioquia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_layout.view.*


class RecyclerAdapter(val context: Context, private val sitesList: ArrayList<MyDataItem>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

/*
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout, parent, false
        )
        return MyViewHolder(itemView) //,mListener add
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val currentItem = sitesList[position]
        //holder.titleImage.setImageResource(currentItem.titleImage)
        //holder.tvHeading.text = currentItem.heading
        //holder.tvDetail.text = currentItem.detail
        holder.userId.text = sitesList[position].heading
        holder.title.text = sitesList[position].detail

    }

    override fun getItemCount(): Int {
        return sitesList.size
    }

    class MyViewHolder(itemView: View) : // add , listener: onItemClickListener
        RecyclerView.ViewHolder(itemView) {

        //val titleImage: ShapeableImageView = itemView.findViewById(R.id.titleImage)
        //val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        //val tvDetail: TextView = itemView.findViewById(R.id.tvDetail)
        val userId: TextView
        var title: TextView

        init {
            //itemView.setOnClickListener {
              //  listener.onItemClick(adapterPosition)
            //}
            userId = itemView.userId
            title = itemView.title
        }
    }
}

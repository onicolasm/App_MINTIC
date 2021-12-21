package com.example.descubre_antioquia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.card_layout.view.*


class RecyclerAdapter(val context: Context, private val sitesList: ArrayList<MyDataItem>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private var imageId = arrayOf(
        R.drawable.piedra_1,
        R.drawable.santafe_2,
        R.drawable.jardin_3,
        R.drawable.estadio_4,
        R.drawable.jerico_5
    )

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_layout, parent, false
        )
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleImage.setImageResource(imageId[position])
        holder.heading.text = sitesList[position].heading
        holder.detail.text = sitesList[position].detail }

    override fun getItemCount(): Int {
        return sitesList.size }

    class MyViewHolder(
        itemView: View,
        listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        var titleImage: ShapeableImageView = itemView.findViewById(R.id.titleImage)
        val heading: TextView
        var detail: TextView
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition) }
            heading = itemView.heading
            detail = itemView.detail
            titleImage = itemView.titleImage
        }
    }
}

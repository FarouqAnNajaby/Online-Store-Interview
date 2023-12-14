package com.example.onlinestorefarouq.ui.beranda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinestorefarouq.R
import com.example.onlinestorefarouq.repository.data.model.Product
import com.example.onlinestorefarouq.repository.data.response.ItemsItem

class ProductAdapter (
    private val listHero: ArrayList<Product>,
    private val context: Context
) : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_product,
            parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, stock, photo,variant) = listHero[position]
        Glide.with(context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvStock.text = stock.toString()
        holder.tvVariant.text = variant.toString()
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_product)
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_product)
        val tvVariant: TextView = itemView.findViewById(R.id.tv_total_stock_product)
        val tvStock: TextView = itemView.findViewById(R.id.tv_total_variant_product)
    }
}
package com.example.yjahz.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yjahz.data.model.TrendingProduct
import com.example.yjahz.databinding.TrendingItemLayoutBinding

class TrendingProductAdapter  (private val trendingList:ArrayList<TrendingProduct.Data.Item>): RecyclerView.Adapter<TrendingProductAdapter.ViewHolder>(){
    class ViewHolder(val binding: TrendingItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  TrendingItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.item= trendingList[position]

    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

}

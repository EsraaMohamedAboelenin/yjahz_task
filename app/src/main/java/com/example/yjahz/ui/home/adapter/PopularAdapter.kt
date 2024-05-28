package com.example.yjahz.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yjahz.R
import com.example.yjahz.data.model.Categories
import com.example.yjahz.data.model.Popular
import com.example.yjahz.databinding.CategoryItemLayoutBinding
import com.example.yjahz.databinding.PopularItemLayoutBinding

class PopularAdapter  (private val popularList:ArrayList<Popular.Data.Item>): RecyclerView.Adapter<PopularAdapter.ViewHolder>(){
    class ViewHolder(val binding: PopularItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  PopularItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.item = popularList[position]
        if(popularList[position].distance.isNullOrEmpty())
        holder.binding.distance.visibility = View.GONE
       holder.binding.rating.rating = popularList[position].rate.toFloat()
        if(popularList[position].isFavorite)
            holder.binding.favIcon.setImageResource(R.drawable.fav)
        else holder.binding.favIcon.setImageResource(R.drawable.ic_add_to_fav)

    }

    override fun getItemCount(): Int {
        return popularList.size
    }

}

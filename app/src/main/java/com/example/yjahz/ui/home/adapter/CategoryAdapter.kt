package com.example.yjahz.ui.home.adapter

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yjahz.data.model.Categories
import com.example.yjahz.databinding.CategoryItemLayoutBinding

class CategoryAdapter (private val categories:ArrayList<Categories.Data.Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    class ViewHolder(val binding:CategoryItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
holder.binding.category = categories[position]

    }

    override fun getItemCount(): Int {
        return categories.size
    }

}
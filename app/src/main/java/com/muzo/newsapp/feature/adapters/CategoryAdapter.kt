package com.muzo.newsapp.feature.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzo.newsapp.databinding.CategoryRowBinding

class CategoryAdapter(private val list: List<String>,val onCategoryClickListener: (category: String) -> Unit):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding :CategoryRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(category: String) {

            binding.buttonCategory.text = category

            binding.buttonCategory.setOnClickListener {
                onCategoryClickListener(category)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=CategoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=list[position]
        holder.bind(currentItem)


    }
    override fun getItemCount(): Int {
       return  list.size
    }

}
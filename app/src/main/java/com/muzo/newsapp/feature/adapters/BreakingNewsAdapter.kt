package com.muzo.newsapp.feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.ItemRowBinding

class BreakingNewsAdapter(var list: List<Article>):RecyclerView.Adapter<BreakingNewsAdapter.ViewHolder>() {



   inner class ViewHolder(private val binding: ItemRowBinding):RecyclerView.ViewHolder(binding.root) {

       fun bind(item:Article){
           binding.apply {
               title.text=item.title
               content.text=item.content
               time.text=item.publishedAt
               val url=item.urlToImage
               IvNews.load(url)

           }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=list[position]
        holder.bind(currentItem)

    }
    override fun getItemCount(): Int {
        return list.size
    }
}
package com.muzo.newsapp.feature.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.databinding.ItemRowBinding

class PaginationAdapter(
    val onNewsClickListener: (item: Article) -> Unit
) : PagingDataAdapter<Article, PaginationAdapter.MyViewHolder>(diffCallback) {


    inner class MyViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.apply {
                title.text = item.title
                content.text = item.content
                time.text = item.publishedAt
                val url = item.urlToImage
                IvNews.load(url)

                root.setOnClickListener {
                    onNewsClickListener(item)
                }
            }
        }
    }


    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}
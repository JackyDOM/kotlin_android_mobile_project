package com.example.myfirstapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.databinding.ViewBannerCategorybook1Binding
import com.squareup.picasso.Picasso

class CategoryBook1Adapter: ListAdapter<Book, CategoryBook1Adapter.CategoryBook1ViewHolder>(CategoryBook1DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBook1ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewBannerCategorybook1Binding.inflate(inflater, parent, false)
        return CategoryBook1ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryBook1ViewHolder, position: Int) {
        val categoryBook1 = getItem(position)
        holder.bindCategoryBook1(categoryBook1)
    }

    class CategoryBook1ViewHolder(private var binding: ViewBannerCategorybook1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bindCategoryBook1(category1Book: Book){
            Picasso.get().load(category1Book.book_image).into(binding.bannerCategory)
            binding.txtPricateCategory1.text = category1Book.price
            binding.txtTitleCategory1.text = category1Book.title
        }

    }

    class CategoryBook1DiffCallback: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id === newItem.id
        }


    }
}
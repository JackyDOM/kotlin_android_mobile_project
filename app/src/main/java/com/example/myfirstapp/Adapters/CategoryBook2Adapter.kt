package com.example.myfirstapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.databinding.ViewHolderCategorybook2Binding
import com.squareup.picasso.Picasso

class CategoryBook2Adapter: ListAdapter<Book, CategoryBook2Adapter.CategoryBook2ViewHolder>(CategoryBook1DiffCallback()) {
    class CategoryBook2ViewHolder(private var binding: ViewHolderCategorybook2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bindCategoryBook2(category2book: Book){
            Picasso.get().load(category2book.book_image).into(binding.bannerCategory2)
            binding.txtPricateCategory2.text = category2book.price
            binding.txtTitleCategory2.text = category2book.title
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryBook2ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategorybook2Binding.inflate(inflater, parent, false)
        return CategoryBook2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryBook2ViewHolder, position: Int) {
        val categoryBook2 = getItem(position)
        holder.bindCategoryBook2(categoryBook2)
    }
}
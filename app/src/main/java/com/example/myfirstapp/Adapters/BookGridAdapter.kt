package com.example.myfirstapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.databinding.ViewHolderGridbooksBinding
import com.squareup.picasso.Picasso

class BookGridAdapter: ListAdapter<Book, BookGridAdapter.BookGridViewHolder>(BookGridDiffCallback()) {
    class BookGridViewHolder(private val binding:ViewHolderGridbooksBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindBookGrid(bookGrid: Book){
            Picasso.get().load(bookGrid.book_image).into(binding.imageBookGrid)
            binding.txtTitleBookGrid.text = bookGrid.title
            binding.txtPriceBookGrid.text = bookGrid.price
        }
    }

    class BookGridDiffCallback: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id === newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookGridViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderGridbooksBinding.inflate(inflater, parent, false)
        return BookGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookGridViewHolder, position: Int) {
        val bookGrid = getItem(position)
        holder.bindBookGrid(bookGrid)

    }
}
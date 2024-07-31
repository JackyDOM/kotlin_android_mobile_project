package com.example.myfirstapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Home_Screen.BookDetailActivity
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.databinding.ViewHolderAuthorBookBinding
import com.squareup.picasso.Picasso

class BookAuthorAdapter : ListAdapter<Book, BookAuthorAdapter.ViewHolderBookAuthor>(BookAuthorDiffCallback()) {
    class ViewHolderBookAuthor(private val binding: ViewHolderAuthorBookBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bindAuthorBook(authorBook: Book){
            Picasso.get().load(authorBook.book_image).into(binding.bookAuthorImage)
            binding.txtBookAuthorTitle.text = authorBook.title

            binding.bookAuthorImage.setOnClickListener{v->
                var context = v.context
                var intentAuthorBook = Intent(context, BookDetailActivity::class.java).apply {
                    putExtra("book_image_url", authorBook.book_image)
                    putExtra("book_title", authorBook.title)
                    putExtra("book_price", authorBook.price)
                    putExtra("book_publisher", authorBook.publisher)
                    putExtra("book_Category_Name", authorBook.category.name)
                    putExtra("author_name", authorBook.author.author_name)
                    putExtra("description", authorBook.description)
                    putExtra("book_id", authorBook.id)
                    putExtra("book_pdf", authorBook.book_pdf)
                }
                context.startActivity(intentAuthorBook)
            }
        }
    }

    class BookAuthorDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id === newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBookAuthor {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderAuthorBookBinding.inflate(inflater, parent, false)
        return ViewHolderBookAuthor(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderBookAuthor, position: Int) {
        val authorBook = getItem(position)
        holder.bindAuthorBook(authorBook)
    }
}
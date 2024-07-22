package com.example.myfirstapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Author
import com.example.myfirstapp.databinding.ViewHolderAuthorBinding
import com.squareup.picasso.Picasso

class AuthorAdapter: ListAdapter<Author, AuthorAdapter.AuthorViewHolder>(AuthorDiffCallback()) {
    class AuthorViewHolder(private val binding: ViewHolderAuthorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindAuthorImage(authorImage: Author){
            Picasso.get().load(authorImage.author_image).into(binding.authorImage)
            binding.txtAuthorName.text = authorImage.author_name
        }
    }

    class AuthorDiffCallback : DiffUtil.ItemCallback<Author>() {
        override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
            return oldItem.id === newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderAuthorBinding.inflate(inflater, parent, false)
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val authorImage = getItem(position)
        holder.bindAuthorImage(authorImage)
    }
}
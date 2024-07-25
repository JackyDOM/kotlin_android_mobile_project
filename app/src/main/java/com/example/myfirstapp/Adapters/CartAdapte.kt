package com.example.myfirstapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.databinding.ViewHolderCartBinding
import com.squareup.picasso.Picasso

class CartAdapte : ListAdapter<Book, CartAdapte.CartViewHolder>(CartDiffCallback()) {
    class CartViewHolder(private val binding: ViewHolderCartBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindCart(cart: Book){
                Picasso.get().load(cart.book_image).into(binding.CartBookImage)
                binding.txtTitleCart.text = cart.title
                binding.txtPriceCart.text = cart.price
            }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id === newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCartBinding.inflate(inflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = getItem(position)
        holder.bindCart(cart)
    }
}
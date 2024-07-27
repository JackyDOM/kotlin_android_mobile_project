package com.example.myfirstapp.Adapters

import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.Modals.BookX
import com.example.myfirstapp.Modals.CartDataBookItem
import com.example.myfirstapp.Modals.CartdataItem
import com.example.myfirstapp.databinding.ViewHolderCartBinding
import com.squareup.picasso.Picasso


class CartAdapte : ListAdapter<CartDataBookItem, CartAdapte.CartViewHolder>(CartDiffCallback()) {
    private val accessToken: String? = null
    private val emptyCartTextView: TextView? = null
//    private val context: Context? = null
    private val progressDialog: ProgressDialog? = null
    private val isDeleting = false

    class CartViewHolder(private val binding: ViewHolderCartBinding):
        RecyclerView.ViewHolder(binding.root){

        //increase and deacrease when user click on button
        private var quantity: Int = 0
        init {
            // Attach click listeners to buttons
            binding.btnIncrease.setOnClickListener { increaseQuantity() }
            binding.btnDecrease.setOnClickListener { decreaseQuantity() }
            // Set onClickListener for delete ImageView
            binding.delete.setOnClickListener {  }
        }

        fun bindCart(cart: CartDataBookItem){
            Picasso.get().load(cart.book.book_image).into(binding.CartBookImage)
            binding.txtTitleCart.text = cart.book.title
            binding.txtPriceCart.text = cart.book.price
        }

        private fun increaseQuantity(){
            quantity++
            binding.txtQuantity.text = quantity.toString()
        }

        private fun decreaseQuantity() {
            if (quantity > 0) {
                quantity--
                binding.txtQuantity.text = quantity.toString()
            }
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<CartDataBookItem>() {
        override fun areItemsTheSame(oldItem: CartDataBookItem, newItem: CartDataBookItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CartDataBookItem, newItem: CartDataBookItem): Boolean {
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
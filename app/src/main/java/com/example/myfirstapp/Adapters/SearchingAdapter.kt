package com.example.myfirstapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstapp.Modals.DataSearchingItem
import com.example.myfirstapp.R

class SearchingAdapter(
    private var searchResults: List<DataSearchingItem>,
    private val context: Context?
) : RecyclerView.Adapter<SearchingAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImageView: ImageView = itemView.findViewById(R.id.imageSearching)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    fun updateData(newResults: List<DataSearchingItem>) {
        this.searchResults = newResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_search, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val searching = searchResults[position]
        holder.titleTextView.text = searching.title
        Glide.with(holder.bookImageView.context).load(searching.book_image).into(holder.bookImageView)
    }
}
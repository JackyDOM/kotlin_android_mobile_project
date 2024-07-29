package com.example.myfirstapp.Cart_Screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.Adapters.CartAdapte
import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.Modals.BookX
import com.example.myfirstapp.Modals.CartDataBookItem
import com.example.myfirstapp.Services.ApiServiceGetCart
import com.example.myfirstapp.databinding.FragmentCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapte
    var accessToken: String? = null
    var userId = 0 // Add this line


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val sharedPreferences =
            requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val retrievedAccessToken = sharedPreferences.getString("access_token", null)
        userId = sharedPreferences.getInt("user_id", -1)

        if (!retrievedAccessToken.isNullOrEmpty()) {
            // Log the retrieved access token
            Log.d("AccessToken", "Retrieved Access Token: $retrievedAccessToken");

        } else {
            // Handle scenario where access token is not available or empty
            Toast.makeText(requireContext(), "Access token not available or empty", Toast.LENGTH_LONG).show()
        }


        // Store the retrieved access token for later use
        accessToken = retrievedAccessToken;

        // If access token is available, load banner images
        if (accessToken != null) {
            loadCartImage()
        }

        binding.recycleViewCart.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            cartAdapter = CartAdapte(requireContext())
            adapter = cartAdapter
        }


        return binding.root
    }

    private fun loadCartImage(){
        val httpClient = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiServiceGetCart = httpClient.create(ApiServiceGetCart::class.java)
        val task: Call<List<CartDataBookItem>> = apiServiceGetCart.loadCartImage("Bearer $accessToken")

        task.enqueue(object : Callback<List<CartDataBookItem>> {
            override fun onResponse(call: Call<List<CartDataBookItem>>, response: Response<List<CartDataBookItem>>) {
                if (response.isSuccessful) {
                    val books: List<CartDataBookItem>? = response.body()
                    Log.d("CartFragment", "Books received: $books")
                    if (books != null) {
                        cartAdapter.submitList(books)
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to load cart items", Toast.LENGTH_LONG).show()
                    Log.e("CartFragment", "Failed to load cart items: " + response.message())
                }
            }

            override fun onFailure(call: Call<List<CartDataBookItem>>, t: Throwable?) {
                Toast.makeText(requireContext(), "Failed to load cart items", Toast.LENGTH_LONG).show()
                Log.e("CartFragment", "Failed to load cart items", t)
            }
        })
    }
}
package com.example.myfirstapp.Services

import com.example.myfirstapp.Modals.Book
import com.example.myfirstapp.Modals.BookX
import com.example.myfirstapp.Modals.CartDataBookItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServiceGetCart {
    @GET("/events/cart")
    fun loadCartImage(@Header("Authorization") authorizationHeader: String): Call<List<CartDataBookItem>>
}
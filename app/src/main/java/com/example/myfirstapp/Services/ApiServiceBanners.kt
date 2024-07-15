package com.example.myfirstapp.Services

import com.example.myfirstapp.Modals.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServiceBanners {
    @GET("/books/book")
    fun loadBannerImage(@Header("Authorization") authorizationHeader: String): Call<List<Book>>
}
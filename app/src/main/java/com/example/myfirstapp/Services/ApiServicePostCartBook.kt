package com.example.myfirstapp.Services

import com.example.myfirstapp.Modals.CartdataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServicePostCartBook {
    @POST("/events/cart")
    fun AddCartBook(@Header("Authorization") authorizationHeader: String,
                    @Body addToCart: CartdataItem
    ): Call<CartdataItem>
}
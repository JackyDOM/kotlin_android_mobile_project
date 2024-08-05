package com.example.myfirstapp.Services

import com.example.myfirstapp.Modals.PaymentCart
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Header

interface ApiServicePayment {
    @POST("/events/payment")
    fun makePayment(@Header("Authorization") authorizationHeader: String, @Body paymentCart: PaymentCart): Call<Void>
}
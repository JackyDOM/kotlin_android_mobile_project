package com.example.myfirstapp.Services

import com.example.myfirstapp.Modals.PaymentData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServicePayment {
    @GET("/events/payment")
    fun makePayment(@Header("Authorization") authorizationHeader: String, @Body paymentData: PaymentData): Call<Void>
}
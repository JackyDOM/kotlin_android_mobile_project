package com.example.myfirstapp.Modals

data class PaymentData(
    val book: Int,
    val card_holder_name: Int,
    val card_number: String,
    val cvv: String,
    val expiration_date: String,
    val id: String,
    val price: Double,
)
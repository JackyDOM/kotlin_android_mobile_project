package com.example.myfirstapp.Modals

data class PaymentCart(
    val book_id: Int,
    val card_holder_name: Int,
    val card_number: String,
    val cvv: String,
    val expiration_date: String,
    val price: String,
    val user_id: Double
)
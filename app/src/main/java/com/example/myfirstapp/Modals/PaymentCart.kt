package com.example.myfirstapp.Modals

data class PaymentCart(
    val book_id: Int,
    val card_holder_name: String,
    val card_number: String,
    val cvv: String,
    val expiration_date: String,
    val price: Int,
    val user_id: Int
)
package com.example.myfirstapp.Modals

data class CartDataBookItem(
    val book: BookX,
    val id: Int,
    val quantity: Int,
    val user: User
)
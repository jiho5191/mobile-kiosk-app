package com.example.mobile_team10

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        val existing = cartItems.find { it.name == item.name }
        if (existing != null) {
            existing.count += 1
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }

    fun getItems(): List<CartItem> = cartItems

    fun clear() {
        cartItems.clear()
    }
}
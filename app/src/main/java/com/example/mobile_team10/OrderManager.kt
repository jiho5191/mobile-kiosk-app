package com.example.mobile_team10

object OrderManager {
    private val orderList = mutableListOf<List<CartItem>>()

    fun addOrder(order: List<CartItem>) {
        orderList.add(order.map { it.copy() })  // 깊은 복사로 안전하게 저장
    }

    fun getOrders(): List<List<CartItem>> = orderList

    fun clearOrders() {
        orderList.clear()
    }
}
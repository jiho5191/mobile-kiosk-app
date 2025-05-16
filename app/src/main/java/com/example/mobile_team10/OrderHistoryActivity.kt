package com.example.mobile_team10

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        title="주문 내역"

        val historyText = findViewById<TextView>(R.id.orderHistoryText)

        val orders = OrderManager.getOrders()
        if (orders.isEmpty()) {
            historyText.text = "주문 내역이 없습니다."
        } else {
            val builder = StringBuilder()
            orders.forEachIndexed { index, order ->
                builder.append("🛒 주문 ${index + 1}\n")
                order.forEach { item ->
                    builder.append("- ${item.name} ${item.count}개 (${item.price}원)\n")
                }
                builder.append("\n")
            }
            historyText.text = builder.toString()
        }
    }
}
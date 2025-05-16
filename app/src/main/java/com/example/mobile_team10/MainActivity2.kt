package com.example.mobile_team10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 장바구니
class MainActivity2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var totalPriceText: android.widget.TextView
    private lateinit var orderButton: android.widget.Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        title="장바구니"

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalPriceText = findViewById(R.id.totalPriceText)
        orderButton = findViewById(R.id.orderButton)

        adapter = CartAdapter(CartManager.getItems().toMutableList()) { item ->
            CartManager.removeItem(item)
            adapter.updateItems(CartManager.getItems().toMutableList())
            updateTotalPrice()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        updateTotalPrice()

        orderButton.setOnClickListener {
            val total = CartManager.getItems().sumOf { it.price * it.count }
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("주문 확인")
                .setMessage("총 금액은 ${total}원입니다.\n주문하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    OrderManager.addOrder(CartManager.getItems())
                    CartManager.clear()
                    adapter.updateItems(CartManager.getItems().toMutableList())
                    updateTotalPrice()
                    dialog.dismiss()
                }
                .setNegativeButton("아니요") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun updateTotalPrice() {
        val total = CartManager.getItems().sumOf { it.price * it.count }
        totalPriceText.text = "총 금액: ${total}원"
    }
}
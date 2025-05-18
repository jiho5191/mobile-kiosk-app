package com.example.mobile_team10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var totalPriceText: TextView
    private lateinit var orderButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        title = "장바구니"

        recyclerView = findViewById(R.id.cartRecyclerView)
        totalPriceText = findViewById(R.id.totalPriceText)
        orderButton = findViewById(R.id.orderButton)

        adapter = CartAdapter(
            CartManager.getItems().toMutableList(),
            onRemoveClick = { item ->
                CartManager.removeItem(item)
                adapter.updateItems(CartManager.getItems().toMutableList())
                updateTotalPrice()
            },
            onQuantityChanged = {
                updateTotalPrice()
            }
        )

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        updateTotalPrice()

        orderButton.setOnClickListener {
            val total = CartManager.getItems().sumOf { it.price * it.count }
            AlertDialog.Builder(this)
                .setTitle("주문 확인")
                .setMessage("총 금액은 ${total}원입니다.\n주문하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    OrderManager.addOrder(CartManager.getItems())
                    CartManager.clear()
                    adapter.updateItems(CartManager.getItems().toMutableList())
                    updateTotalPrice()
                    Toast.makeText(this, "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show()
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
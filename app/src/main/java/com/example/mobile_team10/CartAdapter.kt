package com.example.mobile_team10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var items: MutableList<CartItem>,
    private val onRemoveClick: (CartItem) -> Unit,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.itemName)
        val priceText: TextView = view.findViewById(R.id.itemPrice)
        val countText: TextView = view.findViewById(R.id.itemCount)
        val buttonIncrease: Button = view.findViewById(R.id.buttonIncrease)
        val buttonDecrease: Button = view.findViewById(R.id.buttonDecrease)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.priceText.text = "${item.price}원"
        holder.countText.text = "${item.count}개"

        holder.buttonIncrease.setOnClickListener {
            item.count++
            holder.countText.text = "${item.count}개"
            onQuantityChanged()
        }

        holder.buttonDecrease.setOnClickListener {
            item.count--
            if (item.count == 0) {
                onRemoveClick(item)
            } else {
                holder.countText.text = "${item.count}개"
            }
            onQuantityChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: MutableList<CartItem>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}
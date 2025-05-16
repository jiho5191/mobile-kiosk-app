package com.example.mobile_team10

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mobile_team10.CartItem
import com.example.mobile_team10.CartManager
import com.example.mobile_team10.R

class SetMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_set_menu, container, false)

        // dp to px 변환 함수
        fun Int.dpToPx(): Int = (this * requireContext().resources.displayMetrics.density).toInt()

        fun showConfirmationDialog(name: String, price: Int, drawable1: Int, drawable2: Int) {
            val linearLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                val padding = 16.dpToPx()
                setPadding(padding, padding, padding, padding)
            }

            val imageView1 = ImageView(requireContext()).apply {
                setImageResource(drawable1)
                layoutParams = LinearLayout.LayoutParams(140.dpToPx(), 140.dpToPx())
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            val plusTextView = TextView(requireContext()).apply {
                text = "+"
                textSize = 24f
                setPadding(20.dpToPx(), 0, 20.dpToPx(), 0)
                gravity = Gravity.CENTER
            }

            val imageView2 = ImageView(requireContext()).apply {
                setImageResource(drawable2)
                layoutParams = LinearLayout.LayoutParams(140.dpToPx(), 140.dpToPx())
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            linearLayout.addView(imageView1)
            linearLayout.addView(plusTextView)
            linearLayout.addView(imageView2)

            AlertDialog.Builder(requireContext())
                .setTitle(name)
                .setMessage("가격: $price 원")
                .setView(linearLayout)
                .setPositiveButton("추가") { dialog, _ ->
                    CartManager.addItem(CartItem(name, price))
                    Toast.makeText(requireContext(), "$name 을(를) 장바구니에 담았습니다.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }

        val set1 = view.findViewById<LinearLayout>(R.id.set1)
        set1.setOnClickListener {
            showConfirmationDialog("만두전골 + 참치주먹밥", 23000, R.drawable.mandoo, R.drawable.jumukbab)
        }

        val set2 = view.findViewById<LinearLayout>(R.id.set2)
        set2.setOnClickListener {
            showConfirmationDialog("오코노미야키 + 과일화채", 20000, R.drawable.okonomiyaki, R.drawable.hwachae)
        }

        return view
    }
}
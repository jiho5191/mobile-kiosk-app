package com.example.mobile_team10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class SetMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_set_menu, container, false)

        fun showConfirmationDialog(name: String, price: Int, imageResId: Int) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(name)
            builder.setMessage("가격: $price 원")
            builder.setIcon(imageResId)
            builder.setPositiveButton("추가") { dialog, _ ->
                CartManager.addItem(CartItem(name, price))
                Toast.makeText(requireContext(), "$name 을(를) 장바구니에 담았습니다.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            builder.setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            builder.setCancelable(false)
            builder.show()
        }

        val set1 = view.findViewById<LinearLayout>(R.id.set1)
        set1.setOnClickListener {
            // 대표 이미지 하나만 사용 (만두전골)
            showConfirmationDialog("Set 1 : 만두전골 + 참치주먹밥", 23000, R.drawable.mandoo)
        }

        val set2 = view.findViewById<LinearLayout>(R.id.set2)
        set2.setOnClickListener {
            // 대표 이미지 하나만 사용 (오코노미야키)
            showConfirmationDialog("Set 2 : 오코노미야키 + 과일화채", 20000, R.drawable.okonomiyaki)
        }

        return view
    }
}
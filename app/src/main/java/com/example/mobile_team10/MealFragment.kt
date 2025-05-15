package com.example.mobile_team10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MealFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal, container, false)

        fun showConfirmationDialog(name: String, price: Int, imageResId: Int) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(name)
            builder.setMessage("가격: $price 원")
            builder.setIcon(imageResId)
            builder.setPositiveButton("추가") { dialog, _ ->
                CartManager.addItem(CartItem(name, price))
                Toast.makeText(requireContext(), "$name 이(가) 장바구니에 담았습니다", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            builder.setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setCancelable(false)
            builder.show()
        }

        val mandoo = view.findViewById<LinearLayout>(R.id.mandoo)
        mandoo.setOnClickListener {
            showConfirmationDialog("만두전골", 20000, R.drawable.mandoo)
        }

        val okonomiyaki = view.findViewById<LinearLayout>(R.id.okonomiyaki)
        okonomiyaki.setOnClickListener {
            showConfirmationDialog("오코노미야키", 13000, R.drawable.okonomiyaki)
        }

        val dubu = view.findViewById<LinearLayout>(R.id.dubu)
        dubu.setOnClickListener {
            showConfirmationDialog("두부김치", 12000, R.drawable.dubu)
        }

        val daepae = view.findViewById<LinearLayout>(R.id.daepae)
        daepae.setOnClickListener {
            showConfirmationDialog("대패숙주볶음", 15000, R.drawable.daepae)
        }

        val jumukbab = view.findViewById<LinearLayout>(R.id.jumukbab)
        jumukbab.setOnClickListener {
            showConfirmationDialog("참치주먹밥", 4000, R.drawable.jumukbab)
        }

        val hwachae = view.findViewById<LinearLayout>(R.id.hwachae)
        hwachae.setOnClickListener {
            showConfirmationDialog("과일화채", 8000, R.drawable.hwachae)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
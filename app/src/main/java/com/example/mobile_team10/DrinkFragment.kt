package com.example.mobile_team10

import androidx.appcompat.app.AlertDialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DrinkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrinkFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_drink, container, false)

        // Find views by IDs
        val waterView = view.findViewById<View>(R.id.water)
        val drinkView = view.findViewById<View>(R.id.drink)

        // Set click listeners with dialog confirmation
        waterView?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("물")
                .setMessage("가격: 1,000원")
                .setIcon(R.drawable.water)
                .setPositiveButton("추가") { dialog, _ ->
                    CartManager.addItem(CartItem("물", 1000))
                    android.widget.Toast.makeText(requireContext(), "물을 장바구니에 담았습니다.", android.widget.Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        drinkView?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("콜라/사이다")
                .setMessage("가격: 2,000원")
                .setIcon(R.drawable.drink)
                .setPositiveButton("추가") { dialog, _ ->
                    CartManager.addItem(CartItem("콜라/사이다", 2000))
                    android.widget.Toast.makeText(requireContext(), "콜라/사이다를 장바구니에 담았습니다.", android.widget.Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
                .show()
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
         * @return A new instance of fragment DrinkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DrinkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
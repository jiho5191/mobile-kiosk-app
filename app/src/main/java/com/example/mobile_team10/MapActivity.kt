package com.example.mobile_team10

import android.content.Intent

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Toast

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val booths = listOf(
            R.id.booth_semiconductor,
            R.id.booth_electronics,
            R.id.booth_game,
            R.id.booth_business,
            R.id.booth_design,
            R.id.booth_newmaterials,
            R.id.booth_mechatronics,
            R.id.booth_mechanical,
            R.id.booth_mechanical_design,
            R.id.booth_biochem,
            R.id.booth_sales
        )

        booths.forEach { id ->
            findViewById<View>(id).setOnClickListener {
                Toast.makeText(this, "현재는 컴퓨터공학과의 서비스만 제공중입니다", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<View>(R.id.booth_cs).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.travelmobileapp_flightcheckout

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.TouchDelegate
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelmobileapp_flightcheckout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val popupMenu = PopupMenu(this, binding.menuBtn, 0, 0, R.style.PopupMenuRounded)
        popupMenu.menuInflater.inflate(R.menu.my_menu, popupMenu.menu)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.saveBtn.setOnClickListener{
            startActivity(Intent(this, CheckoutTicketActivity::class.java))
        }
        binding.menuBtn.setOnClickListener{
            popupMenu.show()
        }

        toolbar.post {
            val rect = Rect()
            binding.menuBtn.getHitRect(rect)

            rect.top -= 100
            rect.bottom += 100
            rect.left -= 100
            rect.right += 100

            toolbar.touchDelegate = TouchDelegate(rect, binding.menuBtn)
        }
    }
}
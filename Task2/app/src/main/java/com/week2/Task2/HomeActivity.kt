package com.week2.Task2

import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(){
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.home_navigation)
        bottomNavigationView.itemIconTintList = null
    }
}
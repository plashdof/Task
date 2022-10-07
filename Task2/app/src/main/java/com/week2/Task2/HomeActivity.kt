package com.week2.Task2


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val bnView = findViewById<BottomNavigationView>(R.id.home_navigation)
        bnView.itemIconTintList = null


        // BottomNavigationView 로 Fragment 전환하는 로직

        supportFragmentManager.beginTransaction().add(frame_container.id, HomeFragment()).commit()
        bnView.setOnItemSelectedListener {
            changePage(
                when(it.itemId){
                    R.id.navigation_home->HomeFragment()
                    R.id.navigation_game->GameFragment()
                    R.id.navigation_newhot->NewhotFragment()
                    R.id.navigation_stored->StoredFragment()
                    else->HomeFragment()
                }
            )
            true
        }
    }

    private fun changePage(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(frame_container.id, fragment).commit()
    }


}
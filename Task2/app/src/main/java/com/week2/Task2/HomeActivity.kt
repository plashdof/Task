package com.week2.Task2


import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_home)
        Log.d("aa","onStart")

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

    override fun onResume() {
        super.onResume()
        Log.d("aa","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("aa","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("aa","onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("aa","onRestart")
    }

}
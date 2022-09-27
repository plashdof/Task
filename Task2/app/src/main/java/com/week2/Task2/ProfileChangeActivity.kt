package com.week2.Task2

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ProfileChangeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.d("aa", "onCreate")
    }

    override fun onStart() {
        Log.d("aa", "onStart")
        super.onStart()
        setContentView(R.layout.activity_profilechange)


        val gomainBtn = findViewById<ImageButton>(R.id.profilechange_goback_btn)
        overridePendingTransition(R.anim.left_right_enter, R.anim.left_right_enter)

        gomainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.right_left_exit)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.right_left_exit, R.anim.right_left_exit)
    }



    override fun onResume() {
        super.onResume()
        Log.d("aa", "onResume")
    }


}
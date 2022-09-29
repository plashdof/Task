package com.week2.Task2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AddChangeProfileActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addchangeprofile)

        overridePendingTransition(R.anim.none, R.anim.none)

        val profile1 = findViewById<ImageButton>(R.id.change_profile1)
        val profile2 = findViewById<ImageButton>(R.id.change_profile2)
        val profile3 = findViewById<ImageButton>(R.id.change_profile3)
        val profile4 = findViewById<ImageButton>(R.id.change_profile4)
        val profile5 = findViewById<ImageButton>(R.id.change_profile5)

        profile1.setOnClickListener {
            val bit = getViewBitmap(profile1)
            movetoAddProfile(bit)
        }
        profile2.setOnClickListener {
            val bit = getViewBitmap(profile2)
            movetoAddProfile(bit)
        }
        profile3.setOnClickListener {
            val bit = getViewBitmap(profile3)
            movetoAddProfile(bit)
        }
        profile4.setOnClickListener {
            val bit = getViewBitmap(profile4)
            movetoAddProfile(bit)
        }
        profile5.setOnClickListener {
            val bit = getViewBitmap(profile5)
            movetoAddProfile(bit)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(R.anim.none, R.anim.none)
    }

    // 뷰를 Bitmap 으로 변환해주는 메소드

    private fun getViewBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun movetoAddProfile(bitmap : Bitmap){
        val intent = Intent(this@AddChangeProfileActivity, AddProfileActivity::class.java)
            .putExtra("profileimg",bitmap)
            .putExtra("changeprofile", "success")
        startActivity(intent)
    }


}
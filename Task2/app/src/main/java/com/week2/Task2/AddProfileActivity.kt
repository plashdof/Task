package com.week2.Task2

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.week2.Task2.databinding.ActivityAddprofileBinding
import org.json.JSONArray

class AddProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddprofileBinding
    private lateinit var sharedPreferences:SharedPreferences
    var profilename : String =""
    var profilenamearr : ArrayList<String> = ArrayList()
    var flag : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addprofile)

        binding = ActivityAddprofileBinding.inflate(layoutInflater)

        val gobackBtn = findViewById<ImageButton>(R.id.addprofile_goback_btn)
        val storeBtn = findViewById<Button>(R.id.addprofile_store_btn)

        storeBtn.isEnabled = false
        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)

        // 뒤로가기버튼 클릭 리스너
        gobackBtn.setOnClickListener{
            movebackPage()
        }

        // 저장버튼 클릭 리스너
        storeBtn.setOnClickListener{
            makeProfile()
        }

    }


    override fun onStart() {
        super.onStart()
        Log.d("aa","onStart")

        val editText = findViewById<EditText>(R.id.addprofile_input)
        val imgBtn = findViewById<ImageButton>(R.id.addprofile_image_btn)
        val storeBtn = findViewById<Button>(R.id.addprofile_store_btn)

        // local 에 저장된 String 형태의 profile 이름 배열을 불러오기.
        // JSONArray -> ArrayList 로 형태변환
        val getShared = sharedPreferences.getString("profilenamearr", "ERROR")

        if(getShared != "ERROR"){
            var arrJson = JSONArray(getShared)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
        }


        // EditText에 글자 입력시, 저장버튼 활성화(+색바뀜).
        // 바뀌는 Text값을 profilename String 변수에 저장

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                profilename = editText.text.toString()

                // text 입력되어있을때만 버튼 활성화
                storeBtn.isEnabled = profilename.isNotBlank()


                // text 입력되어있을때 색 : Red
                // text 입력 안되어있을때 색 : DarkRed
                if(profilename.isNotBlank()){
                    storeBtn.setTextColor(Color.RED)
                } else{
                    storeBtn.setTextColor(Color.rgb(59,11,23))
                }

            }
            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    // 프로필 추가 성공시 Toast 메세지
    // 뒤로가기 눌렀을때 Toast 메세지
    override fun onStop() {
        super.onStop()

        if(flag) {
            Toast.makeText(this@AddProfileActivity, "프로필을 추가했습니다", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@AddProfileActivity, "추가하신 프로필이 없습니다", Toast.LENGTH_SHORT).show()
        }
    }
    



    // 저장버튼 클릭시 호출 메소드
    // profilenamearr 에 새로운 profile 이름 추가한뒤, String으로 변환하여 Local 에 저장.
    private fun makeProfile(){

        // profile 이름 배열에 새로 입력한 이름 추가
        profilenamearr.add(profilename)
        flag = true

        // local 에 저장하기 위해 다시 String으로 형변환
        var jsonArr = JSONArray()
        for(i in profilenamearr){
            jsonArr.put(i)
        }
        var result = jsonArr.toString()

        // local 에 갱신된 profile 배열 String 형태로 저장
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("profilenamearr", result)
        editor.apply()

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    // 뒤로가기 버튼 클릭시 호출 메소드
    // 뒤로가기 버튼 클릭시 MainActivity로 화면이동
    private fun movebackPage(){
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }



}
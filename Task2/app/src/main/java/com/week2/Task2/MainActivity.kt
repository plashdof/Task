package com.week2.Task2


import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Task2.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var sharedPreferences : SharedPreferences
    private val data = mutableListOf<ProfileData>()
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("main","onCreate")

        overridePendingTransition(R.anim.none, R.anim.none)
        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)

        val addProfileBtn = findViewById<ImageButton>(R.id.main_addprofile_btn)
        val profileChangeBtn = findViewById<ImageButton>(R.id.main_fixprofile_btn)

        loadprofile()

        addProfileBtn.setOnClickListener{
            moveToAddProfilePage()
        }

        profileChangeBtn.setOnClickListener{
            moveToProfileChange()
        }

        if(intent.getStringExtra("addprofile") == "fail"){
            Toast.makeText(this,"프로필이 추가되지 않았습니다",Toast.LENGTH_SHORT).show()
        }

    }

    // 프로필 추가버튼 클릭시, AddProfileActivity로 화면이동
    private fun moveToAddProfilePage(){
        val intent = Intent(this, AddProfileActivity::class.java)
        startActivity(intent)
    }

    // 연필버튼 클릭시, ProfileChangeActivity로 화면이동
    private fun moveToProfileChange(){
        flag = true
        val intent = Intent(this, ProfileChangeActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.d("main","onStart")
    }

    
    // 프로필 삭제했을때, Toast 메세지 출력
    
    override fun onResume() {
        super.onResume()
        Log.d("main","onResume")
        if(intent.getStringExtra("result") == "success"){
            Toast.makeText(this@MainActivity, "프로필을 삭제했습니다", Toast.LENGTH_SHORT).show()
        }
    }
    
    // 프로필 삭제화면 이동시 Toast 메세지 출력

    override fun onPause() {
        super.onPause()

        Log.d("main","onPause")

        if(flag){
            Toast.makeText(this@MainActivity, "삭제할 프로필을 선택하세요", Toast.LENGTH_SHORT).show()
            flag = false
        }
    }


    // local 에 저장된 String 형태의 profile 이름 배열을 불러오기.
    // JSONArray -> ArrayList 로 형태변환
    
    private fun loadprofile(){

        val getSharedname = sharedPreferences.getString("profilenamearr", "ERROR")
        val getSharedimage = sharedPreferences.getString("profileimgarr", "ERROR")
        val profilenamearr : ArrayList<String> = ArrayList()
        val profileimgarr : ArrayList<String> = ArrayList()
        
        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행

        if(getSharedname != "ERROR"){
            val arrJson = JSONArray(getSharedname)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
        }

        if(getSharedimage != "ERROR"){
            val arrJsonimg = JSONArray(getSharedimage)
            for(i in 0 until arrJsonimg.length()) {
                profileimgarr.add(arrJsonimg.optString(i))
            }
        }

        recycler(profilenamearr, profileimgarr)


    }
    
    // recycler view 화면 출력

    private fun recycler(profilenamearr: ArrayList<String>, profileimgarr: ArrayList<String>){
        
        // 어뎁터 연결
        profileAdapter = ProfileAdapter(this)
        main_profiles.adapter = profileAdapter
        
        // 레이아웃 매니저 호출 & 레이아웃 설정
        val gridLayoutManager = GridLayoutManager(this, 2)
        main_profiles.layoutManager = gridLayoutManager

        
        
        // ArrayList 형태로 되어있는 profile data 를 View에 추가시키기
        
        data.apply{

            for(i in 0 until profilenamearr.size){
                
                // bitmap 을 drawable로 다시 변환시키는 로직
                val d = BitmapDrawable(resources,StringToBitmap(profileimgarr[i]))

                add(ProfileData(img = d, name = profilenamearr[i]))
            }
            
            profileAdapter.datas = data
        }
    }
    
    // SharedPreferences에 저장되어있는 String 형태의 Bitmap을 Bitmap으로 변환
    fun StringToBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(
                encodedString,
                Base64.DEFAULT
            ) // String 화 된 이미지를  base64방식으로 인코딩하여 byte배열을 만듬
            BitmapFactory.decodeByteArray(
                encodeByte,
                0,
                encodeByte.size
            ) //byte배열을 bitmapfactory 메소드를 이용하여 비트맵으로 바꿔준다.
            //만들어진 bitmap을 return
        } catch (e: Exception) {
            e.message
            null
        }
    }


    override fun onStop() {
        super.onStop()
        Log.d("main","onStop")
    }


    override fun onRestart() {
        super.onRestart()
        Log.d("main","onRestart")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("main","onDestroy")
    }




}





package com.week2.Task2


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Task2.adapter.ProfileAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var sharedPreferences : SharedPreferences
    private val data = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)


        loadprofile()


        // 프로필 추가버튼 클릭시, AddProfileActivity로 화면이동

        val addProfileBtn = findViewById<ImageButton>(R.id.main_addprofile_btn)


        fun moveToAddProfilePage(){
            val intent = Intent(this, AddProfileActivity::class.java)
            startActivity(intent)
        }

        addProfileBtn.setOnClickListener{
            moveToAddProfilePage()
        }

    }


    // local 에 저장된 String 형태의 profile 이름 배열을 불러오기.
    // JSONArray -> ArrayList 로 형태변환
    
    private fun loadprofile(){

        val getSharedname = sharedPreferences.getString("profilenamearr", "ERROR")
        val getSharedimage = sharedPreferences.getInt("profileimagearr", -1)
        var profilenamearr : ArrayList<String> = ArrayList()
        
        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행

        if(getSharedname == "ERROR"){
            recycler(profilenamearr)
        }else{
            var arrJson = JSONArray(getSharedname)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
            recycler(profilenamearr)
        }

    }
    
    // recycler view 화면 출력

    private fun recycler(profilenamearr: ArrayList<String>){
        profileAdapter = ProfileAdapter(this)
        main_profiles.adapter = profileAdapter

        val gridLayoutManager = GridLayoutManager(this, 2)
        main_profiles.layoutManager = gridLayoutManager

        
        
        // ArrayList 형태로 되어있는 profile data 를 View에 추가시키기
        
        data.apply{

            for(i in profilenamearr){
                add(ProfileData(img = R.drawable.profile4, name = i))
            }
            
            profileAdapter.datas = data
        }
    }






}
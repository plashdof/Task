package com.week2.Task2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.week2.Task2.adapter.ProfileAdapter
import com.week2.Task2.adapter.ProfileChangeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profilechange.*
import kotlinx.android.synthetic.main.recycler_delete_item.*
import org.json.JSONArray

class ProfileChangeActivity : AppCompatActivity(){

    private lateinit var profileChangeAdapter : ProfileChangeAdapter
    private lateinit var sharedpreferences : SharedPreferences
    private val data = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilechange)

        sharedpreferences = getSharedPreferences("test", MODE_PRIVATE)

        overridePendingTransition(R.anim.none, R.anim.none)

        loadprofilechange()

        val gomainBtn = findViewById<ImageButton>(R.id.profilechange_goback_btn)

        // 뒤로가기 버튼 눌렀을때 페이지 이동
        gomainBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.none)
        }

        // 휴지통 삭제버튼 눌렀을때, local 저장된 해당 포지션의 프로필데이터 삭제후, 페이지 이동
        profileChangeAdapter.setItemClickListener(object: ProfileChangeAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int){
                Log.d("aa", "Clicked")
                deleteprofile(position)
                var intent = Intent(this@ProfileChangeActivity, MainActivity::class.java)
                intent.putExtra("result", "success")
                startActivity(intent)
            }
        })

    }


    // 뒤로가기 버튼 애니메이션 재정의
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.none, R.anim.none)
    }

    
    // local 에서 데이터 불러와서 파싱하는 메소드
    private fun loadprofilechange(){
        val getSharedname = sharedpreferences.getString("profilenamearr", "ERROR")
        var profilenamearr : ArrayList<String> = ArrayList()

        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행

        if(getSharedname == "ERROR"){
            recycler(0)
        }else{
            var arrJson = JSONArray(getSharedname)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
            recycler(profilenamearr.size)
        }
    }
    
    
    // local 데이터 파싱 끝나면, recyclerview 화면에 띄우기
    private fun recycler(size :Int){
        profileChangeAdapter = ProfileChangeAdapter(this)
        profilechange_recycler.adapter = profileChangeAdapter

        val gridLayoutManager = GridLayoutManager(this, 2)
        profilechange_recycler.layoutManager = gridLayoutManager

        Log.d("aa", "$size")

        data.apply{

            for(i in 0 until size){
                add(R.drawable.main_delete)
            }

            profileChangeAdapter.datas = data
        }

    }


    // 프로필데이터 삭제하는 과정.
    
    // local 데이터 불러와서 배열로 데이터파싱
    // -> 해당 포지션의 데이터 삭제
    // -> 다시 String으로 데이터 파싱후 local 에 덮어쓰기
    private fun deleteprofile(position: Int){
        val getSharedname = sharedpreferences.getString("profilenamearr", "ERROR")
        var profilenamearr : ArrayList<String> = ArrayList()

        // 만약 저장된 profile 데이터 없다면 그냥 데이터 변환 하지 않고 recyler 함수 실행

        if(getSharedname == "ERROR"){
            return
        }else{
            var arrJson = JSONArray(getSharedname)
            for(i in 0 until arrJson.length()){
                profilenamearr.add(arrJson.optString(i))
            }
            profilenamearr.removeAt(position)
        }

        // local 에 저장하기 위해 다시 String으로 형변환
        var jsonArr = JSONArray()
        for(i in profilenamearr){
            jsonArr.put(i)
        }
        var result = jsonArr.toString()

        // local 에 갱신된 profile 배열 String 형태로 저장
        val editor: SharedPreferences.Editor = sharedpreferences.edit()
        editor.putString("profilenamearr", result)
        editor.apply()
    }

    




}

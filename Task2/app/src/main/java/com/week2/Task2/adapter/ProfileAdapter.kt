package com.week2.Task2.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.week2.Task2.HomeActivity
import com.week2.Task2.ProfileData
import com.week2.Task2.R


class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    var datas = mutableListOf<ProfileData>()


    inner class ViewHolder(private val view:View):RecyclerView.ViewHolder(view){
        private val name: TextView = view.findViewById(R.id.main_profile_name)
        private val image: ImageButton = view.findViewById(R.id.main_profile_btn)

        fun bind(item: ProfileData) {
            name.text = item.name
            Glide.with(itemView)
                .load(item.img)
                .into(image)
            
            
            // 프로필 버튼 클릭시, HomeActivity로 이동
            
            image.setOnClickListener{

                val intent = Intent(context, HomeActivity::class.java)
                intent.putExtra("data", item.img)
                intent.run{context.startActivity(this)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item,parent, false)
        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


}
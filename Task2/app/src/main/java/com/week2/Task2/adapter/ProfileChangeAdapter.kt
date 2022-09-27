package com.week2.Task2.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.week2.Task2.MainActivity
import com.week2.Task2.ProfileChangeActivity
import com.week2.Task2.R
import kotlinx.android.synthetic.main.recycler_delete_item.view.*
import org.json.JSONArray

class ProfileChangeAdapter(private val context: Context) : RecyclerView.Adapter<ProfileChangeAdapter.ViewHolder>(){

    var datas = mutableListOf<Int>()
    private lateinit var itemClickListener: OnItemClickListener


    inner class ViewHolder(private val view:View):RecyclerView.ViewHolder(view){
        private val image: ImageButton = view.findViewById(R.id.profilechange_delete_btn)


        fun bind(item: Int){
            Glide.with(itemView)
                .load(item)
                .into(image)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileChangeAdapter.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_delete_item, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProfileChangeAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.profilechange_delete_btn.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }


    // Click event 를, Activity에서 처리하기 위한 함수들

    interface OnItemClickListener{
        fun onClick(v:View, position:Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = datas.size



}
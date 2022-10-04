package com.week2.Task2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Task2.HomeActivity
import com.week2.Task2.ProfileData
import com.week2.Task2.databinding.RecyclerListItemBinding


class ProfileAdapter(private val datas: ArrayList<ProfileData>) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){


    inner class ViewHolder(private val viewBinding: RecyclerListItemBinding):RecyclerView.ViewHolder(viewBinding.root){
        private val context = viewBinding.root.context

        fun bind(item: ProfileData) {
            viewBinding.mainProfileBtn.setImageDrawable(item.img)
            viewBinding.mainProfileName.text = item.name
            
            // 프로필 버튼 클릭시, HomeActivity로 이동
            
            viewBinding.mainProfileBtn.setOnClickListener{

                val intent = Intent(context, HomeActivity::class.java)
                intent.run{context.startActivity(this)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val viewBinding = RecyclerListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


}
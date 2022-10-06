package com.week2.Task2.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.week2.Task2.TestData
import com.week2.Task2.databinding.RecyclerTestBinding
import kotlinx.android.synthetic.main.recycler_test.view.*

class TestAdapter(private val datas: ArrayList<TestData>) : RecyclerView.Adapter<TestAdapter.ViewHolder>(){

    inner class ViewHolder(private val viewBinding: RecyclerTestBinding):RecyclerView.ViewHolder(viewBinding.root){

        private val context = viewBinding.root.context

        fun bind(item: TestData) {
            Log.d("testrec", "bind")
            viewBinding.testImage.setImageResource(item.img)
            viewBinding.testText.text = item.text
            val position = adapterPosition
            viewBinding.testCheckbox.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        Log.d("testrec", "onCreateViewHolder")
        val viewBinding = RecyclerTestBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        Log.d("testrec","position = $position")
        // 이렇게 하게되면, 기존 체크되어있던 데이터가 날라감
//        if(datas[position].checked){
//            datas[position].checked = false
//        }

        holder.bind(datas[position])
    }

    override fun getItemCount(): Int{
        return datas.size
    }


}
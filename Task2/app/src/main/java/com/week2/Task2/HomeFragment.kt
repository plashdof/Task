package com.week2.Task2


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home,container,false)

        view.home_fragment_movie_seoul.setOnClickListener {
            Log.d("fraghome", "Clicked")
        }

        view.home_fragment_movie_kobra.setOnClickListener {
            Log.d("fraghome", "Clicked")
        }

        view.home_fragment_movie_ring.setOnClickListener {
            Log.d("fraghome", "Clicked")
        }

        view.home_fragment_movie_gwimyual.setOnClickListener {
            Log.d("fraghome", "Clicked")
        }

        return inflater.inflate(R.layout.fragment_home,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_fragment_movie_seoul.setOnClickListener {
            Log.d("fraghome","Clicked")
        }
    }





}
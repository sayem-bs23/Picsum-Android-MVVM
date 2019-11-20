package com.bs.picsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {
    private val numberOfColumn = 3
    lateinit var viewModel:PictureViewModel
    private var pictureAdapter: PictureAdapter = PictureAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindUI()

    }

    private fun bindUI() {
        //click and start new activity
        pictureAdapter.itemClickListener = object: ItemClickListener<Picture>{
            override fun onItemClick(position: Int, item: Picture) {
                val intent = Intent(applicationContext, PictureDetailActivity::class.java).apply {
                    putExtra("clickedPosition", position.toString())
                }
                startActivity(intent)
            }
        }

        //link to xml
        item_list.adapter = pictureAdapter
        item_list.layoutManager = GridLayoutManager(this, numberOfColumn)

        //setup model
        viewModel = ViewModelProvider(this).get(PictureViewModel::class.java)
        viewModel.pictures.observe(this, Observer{
            Log.d("dbg_obs", it[0].author)
            Log.d("dbg", "abc")
            pictureAdapter.setData(it)
        })
    }
}

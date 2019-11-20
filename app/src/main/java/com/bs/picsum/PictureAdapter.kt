package com.bs.picsum


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content.view.*


class PictureAdapter() : RecyclerView.Adapter<PictureAdapter.PictureHolder>() {
    var itemClickListener:ItemClickListener<Picture>? = null
    private val pictures = ArrayList<Picture>()

    fun setData( alist : ArrayList<Picture>){
        pictures.clear()
        pictures.addAll(alist)
        notifyDataSetChanged()
    }

    fun prepareThumbnailURL(str: String):String{
        val width = 200
        val height = 200
        var temp:String = ""
        var cnt : Int = 0
        var i: Int = str!!.length - 1
        while(cnt < 2){
            println(i)
            if(str[i] == '/'){
                cnt++
            }
            i--
        }
        var j = 0
        while(j <= i){
            temp += str[j]
            j++
        }

        temp += "/${width}/${height}"
        return temp
    }
    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val picture = pictures[position]
        holder.idView.text = picture.id

        val url:String = prepareThumbnailURL(pictures[position].download_url!!)
        Picasso.get().load(url).into(holder.image)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return PictureHolder(view).apply {
            rootLayout.setOnClickListener {
                itemClickListener?.onItemClick(adapterPosition, pictures[adapterPosition])
            }
        }
    }

    override fun getItemCount() = pictures.size

    inner class PictureHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootLayout: LinearLayout = view.itemRoot
        val idView: TextView = view.authorName
        val image: ImageView = view.imageView
    }
}
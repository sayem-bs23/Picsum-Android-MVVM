package com.bs.picsum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.picture_container.view.*


class PictureDetailPagerAdapter : RecyclerView.Adapter<PictureViewHolder>() {
//    var list: List<Picture> = listOf()
    private val list = ArrayList<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(parent)
    }
    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {

        holder.bind(list[position])
    }
    fun setItem(list: ArrayList<Picture>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size
}

class PictureViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    constructor(parent: ViewGroup) :
            this(LayoutInflater.from(parent.context).inflate(R.layout.picture_container, parent, false))
    fun bind(picture: Picture) {
        itemView.categoryName.text  = picture.author
        itemView.slideimg.setImageResource(R.drawable.img_sample)

        val imageUri = picture.download_url
        Picasso.get().load(imageUri).into(itemView.slideimg)



    }
}
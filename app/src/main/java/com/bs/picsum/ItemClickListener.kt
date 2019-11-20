package com.bs.picsum


interface ItemClickListener<T> {
    fun onItemClick(position:Int, item:T)
}
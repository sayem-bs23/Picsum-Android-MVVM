package com.bs.picsum

import retrofit2.http.GET
import retrofit2.http.Path

interface PictureService{

    @GET("v2/list/")
    suspend fun getAllPicture(): ArrayList<Picture>

}
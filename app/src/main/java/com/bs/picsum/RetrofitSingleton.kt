package com.bs.picsum

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton{

    private const val BASE_URL = "https://picsum.photos/"


    internal fun buildRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return  retrofit
    }

    val instanceAllPicture: PictureService by lazy{
        val retrofit = buildRetrofit()
        retrofit.create(PictureService::class.java)
    }

    val instanceSinglePicture:PictureService by lazy{
        val retrofit = buildRetrofit()
        retrofit.create(PictureService::class.java)
    }


}

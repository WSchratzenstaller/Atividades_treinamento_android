package com.schratzenstaller.wilcilene.projeto_integrador

import android.util.Log
import com.schratzenstaller.wilcilene.projeto_integrador.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetriever {
    val service: TmdbService
    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL.toString() )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TmdbService::class.java)
    }

    fun getAllMovies() {
//        service.
    }
}
package com.example.retrofit_mvvm_v001.api

import com.example.retrofit_mvvm_v001.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost():Response<Post>
}
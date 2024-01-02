package com.example.retrofit_mvvm_v001.repository

import com.example.retrofit_mvvm_v001.api.RetrofitInstance
import com.example.retrofit_mvvm_v001.model.Post

class PostRepository (private val retrofitInstance:RetrofitInstance){
    suspend fun getPost():Result<Post>{
        return try {
            val response = retrofitInstance.api.getPost()
            if (response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Error fetching post"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}
package com.example.retrofit_mvvm_v001.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_mvvm_v001.repository.PostRepository

class PostViewModelFactory(private val postRepository: PostRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)){
            return PostViewModel(postRepository) as T
        }
         throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
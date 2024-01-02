package com.example.retrofit_mvvm_v001.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_mvvm_v001.model.Post
import com.example.retrofit_mvvm_v001.repository.PostRepository
import kotlinx.coroutines.launch
import okhttp3.Response

class PostViewModel(private val postRepository: PostRepository):ViewModel() {
    private val _post = MutableLiveData<Post>()
    val post : LiveData<Post> = _post

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun loadPost(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = postRepository.getPost()
            if (result.isSuccess){
                _post.value = result.getOrNull()
            }else{
                _error.value = result.exceptionOrNull()?.message?:"Unknown Message"
            }
            _isLoading.value = false
        }
    }
}
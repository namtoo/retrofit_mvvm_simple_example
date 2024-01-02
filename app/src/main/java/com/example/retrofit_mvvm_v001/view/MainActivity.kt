package com.example.retrofit_mvvm_v001.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_mvvm_v001.api.RetrofitInstance
import com.example.retrofit_mvvm_v001.repository.PostRepository
import com.example.retrofit_mvvm_v001.ui.theme.Retrofit_MVVM_v001Theme
import com.example.retrofit_mvvm_v001.viewmodel.PostViewModel
import com.example.retrofit_mvvm_v001.viewmodel.PostViewModelFactory

class MainActivity : ComponentActivity() {
    private val TAG = "==> MainActivity "
    private lateinit var postViewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitInstance
        val postRepository = PostRepository(apiService)
        val postViewModelFactory = PostViewModelFactory(postRepository)

        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)

        postViewModel.post.observe(this, Observer {
            Log.d(TAG, "onCreate: Post "+it.body)
        })

        postViewModel.isLoading.observe(this, Observer {
            Log.d(TAG, "onCreate: is Loading ....$it")
        })

        postViewModel.error.observe(this, Observer {
            Log.d(TAG, "onCreate: Error $it")
        })

        postViewModel.loadPost()





        setContent { content() }
    }
}


@Composable
fun content(){
    Retrofit_MVVM_v001Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Retrofit_MVVM_v001Theme {
        Greeting("Android")
    }
}
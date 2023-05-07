package com.example.challengechapterlima.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapterlima.data.remote.ApiConfig
import com.example.challengechapterlima.data.remote.model.DetailMovieResponse
import com.example.challengechapterlima.data.remote.model.MovieResponse
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _movie = MutableLiveData<DetailMovieResponse>()
    val movie: LiveData<DetailMovieResponse> = _movie

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    fun getDetailMovie(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _loadingState.postValue(true)
        delay(2000L)
        val response = ApiConfig.getApiService().getDetailMovie(movieId = movieId)
        viewModelScope.launch(Dispatchers.Main) {
            _loadingState.postValue(false)
            _movie.postValue(response)
        }
    }
}
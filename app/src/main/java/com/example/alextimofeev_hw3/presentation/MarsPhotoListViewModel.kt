package com.example.alextimofeev_hw3.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alextimofeev_hw3.data.MarsPhotoRepository
import com.example.alextimofeev_hw3.entity.PhotoItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarsPhotoListViewModel: ViewModel() {
    private val repository = MarsPhotoRepository()
    private val _photos = MutableStateFlow<List<PhotoItem>>(arrayListOf())
    val photos = _photos.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadAllPhotos()
    }

    private fun loadAllPhotos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _photos.value = repository.getPhotosList()

            } catch (exception: Exception) {
                Log.d("newlog", "repository error: ${exception.message}")
            }
            _isLoading.value = false
        }
    }



}
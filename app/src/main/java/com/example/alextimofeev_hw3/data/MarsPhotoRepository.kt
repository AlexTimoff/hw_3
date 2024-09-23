package com.example.alextimofeev_hw3.data

import android.util.Log
import com.example.alextimofeev_hw3.entity.PhotoItem

class MarsPhotoRepository {
    suspend fun getPhotosList(sol: Int = 4102): List<PhotoItem> {
        try {
            val response = RetrofitInstance.getMarsPhotosApiResponse.getDataPhotosResponse(sol)
            return if (response.isSuccessful)
                response.body()?.photos ?: arrayListOf()
            else
                emptyList()
        } catch (ex: Exception) {
            Log.d("newlog", "${ex.message}")
        }

        return arrayListOf()
    }
}
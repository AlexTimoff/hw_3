package com.example.alextimofeev_hw3.data

import com.example.alextimofeev_hw3.entity.MarsPhotoInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov"


object RetrofitInstance{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val getMarsPhotosApiResponse: GetMarsPhotosApi = retrofit.create(
        GetMarsPhotosApi::class.java
    )
}

interface GetMarsPhotosApi{
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getDataPhotosResponse(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("camera") camera: String = CAMERA
    ): Response<MarsPhotoInfo>



    companion object{
        private const val API_KEY = "B4R61PeXl6OJaYOB5ccIN1uO0PzPbbZrq56NKol4"
        private const val CAMERA = "NAVCAM"
    }
}
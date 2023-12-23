package com.example.androidlab3
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface FlickrApi {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getPublicPhotos(): Response<List<Photo>>
}

class FlickrRepository {
    private val flickrApi: FlickrApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/feeds/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(FlickrApi::class.java)
    }

    suspend fun getPublicPhotos(): Response<List<Photo>> {
        return flickrApi.getPublicPhotos()
    }
}
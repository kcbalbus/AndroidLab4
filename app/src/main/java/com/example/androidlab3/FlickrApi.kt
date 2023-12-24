package com.example.androidlab3
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://www.flickr.com/services/feeds/"
interface FlickrApi {
    @GET("photos_public.gne")
    suspend fun getPublicPhotos(): List<Photo>
}

object WebClient {
    val client: FlickrApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JaxbConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }
}
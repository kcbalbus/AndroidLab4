package com.example.androidlab3
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import com.google.gson.Gson
import retrofit2.http.Query

private const val BASE_URL = "https://www.flickr.com/services/feeds/"
interface FlickrApi {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getPublicPhotos(): FlickrResponse

    @GET("photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getTagPhotos(@Query("tags") tag: String): FlickrResponse
}


object WebClient {
    val client: FlickrApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                ))
            .build()
            .create(FlickrApi::class.java)
    }
}
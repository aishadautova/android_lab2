package kz.android.celebrity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("X-Api-Key:tl0bQJpYjjGPbDiBUw+PHw==4VYwZ06elSLZcZ4M")
    @GET("celebrity")
    fun getCelebritiesByName(@Query("name") name: String): Call<List<Celebrity>>
}
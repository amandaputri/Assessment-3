package org.d3if2036.hitungkecepatan.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/amandaputri/konsepglb_api_json/main/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface KonsepGlbApiService{
    @GET("konsepglb.json")
    suspend fun getKonsepGlb(): String
}
object KonsepGlbApi {
    val service: KonsepGlbApiService by lazy {
        retrofit.create(KonsepGlbApiService::class.java)
    }
}
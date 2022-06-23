package org.d3if2036.hitungkecepatan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2036.hitungkecepatan.ui.KonsepGlb
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/amandaputri/konsepglb_api_json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface KonsepGlbApiService{
    @GET("konsepglb.json")
    suspend fun getKonsepGlb(): List<KonsepGlb>
}

object KonsepGlbApi {
    val service: KonsepGlbApiService by lazy {
        retrofit.create(KonsepGlbApiService::class.java)
    }
}
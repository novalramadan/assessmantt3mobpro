package org.d3if3103.mobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3103.mobpro1.model.Laptop
import org.d3if3103.mobpro1.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://laptop-api-test.000webhostapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LaptopApiService {
    @GET("/api/laptop.php")
    suspend fun getLaptop(
        @Header("authorization") userId: String
    ): List<Laptop>

    @Multipart
    @POST("/api/laptop.php")
    suspend fun postLaptop(
        @Header("authorization") userId: String,
        @Part("nama_barang") nama_barang: RequestBody,
        @Part("spesifikasi") spesifikasi: RequestBody,
        @Part image: MultipartBody.Part
//        @Part("mine") mine: RequestBody
    ): OpStatus

    @DELETE("/api/deleteLaptop.php/{id}")
    suspend fun deleteLaptop(
        @Header("Authorization") userId: String,
        @Query("id") LaptopId: String
    ) : OpStatus
}

object LaptopApi {
    val service: LaptopApiService by lazy {
        retrofit.create(LaptopApiService::class.java)
    }

    fun getLaptopUrl(imageId: String): String {
//        return "$BASE_URL$imageId.jpg"
        return "${BASE_URL}/api/image.php?id=$imageId"
    }
}

enum class ApiStatus{ LOADING, SUCCESS, FAILED }
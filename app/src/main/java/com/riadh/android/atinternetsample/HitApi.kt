package com.riadh.android.atinternetsample

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface HitApi {
    @GET("hit.xiti")
    fun sendHit(
        @Query("col") col: String,
        @Query("s") s: String,
        @Query("site") site: String,
        @Query("from") from: String,
        @Query("idclient") idclient: String,
        @Query("ptag") ptag: String,
        @Query("p") p: String
    ): Call<ResponseBody>

}
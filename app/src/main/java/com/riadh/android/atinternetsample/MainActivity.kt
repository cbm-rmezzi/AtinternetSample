package com.riadh.android.atinternetsample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://logs13.xiti.com/")
                .client(client)
                .build()

        val service: HitApi = retrofit.create(HitApi::class.java)

        findViewById<Button>(R.id.button).setOnClickListener {
            val dataCall: Call<ResponseBody> = service.sendHit("1", "615704", "615704", "p",
                    "e5c728db-b510-41a3-9de5-117f0321bae9", "android", "hitAndroidApp")

            dataCall.enqueue(object : Callback<ResponseBody?> {


                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {

                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@MainActivity, response.isSuccessful.toString(), Toast.LENGTH_SHORT).show() }

                    Log.i("info", response.code().toString())
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show() }

                }
            })
        }


    }
}
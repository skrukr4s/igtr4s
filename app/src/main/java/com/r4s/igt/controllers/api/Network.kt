package com.r4s.igt.controllers.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.r4s.igt.BuildConfig
import com.r4s.igt.app.App
import com.r4s.igt.controllers.models.Game
import com.r4s.igt.controllers.models.Player
import kotlinx.coroutines.experimental.Deferred
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import java.io.IOException


// Created by sebastian with ♥
interface Network {

    @POST("2ewt6r22zo4qwgx/gameData.json")
    fun getGameData(): Deferred<Response<Game>>

    @POST("5zz3hibrxpspoe5/playerInfo.json")
    fun getPlayerData(): Deferred<Response<Player>>


    companion object {
        private val BASE_URL = BuildConfig.URL_PROD
        fun create() : Network {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


            val client: OkHttpClient = OkHttpClient.Builder()
                    .cache(createHttpClientCache(App.appCtx()))
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(ForceCacheInterceptor())
                    .build()


            val restAdapter = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return restAdapter.create(Network::class.java)
        }

        fun createHttpClientCache(context: Context): Cache? {
            var cacheSize = 2 * 1024 * 1024 // 2MB
            try {
                val cacheDir = context.getDir("service_api_cache", Context.MODE_PRIVATE)
                return Cache(cacheDir, cacheSize.toLong())
            } catch (e: IOException) {
                Log.e("Error", "Couldn't create http cache because of IO problem.", e)
                return null
            }

        }
    }

    class ForceCacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var builder = chain.request().newBuilder()
            if (!isNetworkConnected()) {
                builder.cacheControl(CacheControl.FORCE_CACHE)
            }

            return chain.proceed(builder.build())
        }

        fun isNetworkConnected(): Boolean {
            val cm = App.appCtx().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

            return cm!!.activeNetworkInfo != null
        }
    }


}
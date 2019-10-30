package com.example.nasa_api.model

import com.example.nasa_api.common_classes.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

abstract class Repo {

    interface NASA_API{

        @GET("search")
        fun search(
            @Query("q") query: String? = null,
            @Query("center") center: String?= null,
            @Query("page") page: Int? = null,
            @Query("nasa_id") nasaId: String?=null
        ): Single<SearchResponse>

        @GET("asset/{id}")
        fun asset(@Path("id") nasaId: String): Observable<SearchResponse>
    }

    companion object{
        fun getRepo() = Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(OkHttpClient().newBuilder()
//                .addInterceptor(object : Interceptor{
//                    override fun intercept(chain: Interceptor.Chain): Response {
//                        Log.d("RXTEST_INTERCEPTOR", "Start request ${chain.request()}")
//                        Log.d("RXTEST_INTERCEPTOR", "Thread -> ${Thread.currentThread()}")
//                        return chain.proceed(chain.request())
//                    }
//
//                })
//                .build()
//            )
            .build()
            .create(NASA_API::class.java)
    }
}
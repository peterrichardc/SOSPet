package com.labsidea.mypetapp.data.api

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.labsidea.mypetapp.data.model.RequestResponse
import com.labsidea.mypetapp.data.model.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IApiService {

    @GET ("organization/get_all_organizations?page=1&key=m\$Th)6D.Sk?Sar{")
    fun getInstitutions(): Single<RequestResponse<User>>

    companion object Factory {

        private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun create(): IApiService {
            val adapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            val url = "https://alanfabeni.pythonanywhere.com/eagle/"

            return Retrofit.Builder()
                .addCallAdapterFactory(adapter)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(client)
                .build()
                .create(IApiService::class.java)
        }
    }
}
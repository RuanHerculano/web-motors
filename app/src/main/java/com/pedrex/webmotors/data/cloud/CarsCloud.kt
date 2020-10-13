package com.pedrex.webmotors.data.cloud

import com.google.gson.GsonBuilder
import com.pedrex.webmotors.data.cloud.model.response.CarResponse
import com.pedrex.webmotors.data.cloud.requests.CarsApi
import com.pedrex.webmotors.data.mapper.MapperResponseToModel
import com.pedrex.webmotors.presentation.model.ItemModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class CarsCloud {

    private val api: CarsApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://desafioonline.webmotors.com.br")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        api = retrofit.create<CarsApi>(CarsApi::class.java)
    }

    fun getCars(page: Int) : Observable<List<CarResponse>> {
        return api.fetchVehicles( "application/json", page)
    }
}
package com.pedrex.webmotors.data.cloud.requests

import com.pedrex.webmotors.data.cloud.model.response.CarResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable

interface CarsApi {

    @GET("/api/OnlineChallenge/Vehicles")
    fun fetchVehicles(
        @Header("Content-Type") contentType: String,
        @Query("page") page: Int
    ): Observable<List<CarResponse>>
}
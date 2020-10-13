package com.pedrex.webmotors.data

import com.pedrex.webmotors.data.cloud.CarsCloud
import com.pedrex.webmotors.data.mapper.MapperResponseToModel
import com.pedrex.webmotors.domain.repository.ICarRepository
import com.pedrex.webmotors.presentation.model.ItemModel
import rx.Observable

class CarRepository : ICarRepository {

    private val mapper = MapperResponseToModel()
    private val cloud = CarsCloud()

    override fun getCars(page: Int): Observable<List<ItemModel>> {
        return cloud.getCars(page).map { mapper.transform(it) }
    }
}
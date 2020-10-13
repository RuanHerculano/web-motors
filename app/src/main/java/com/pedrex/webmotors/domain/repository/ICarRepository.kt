package com.pedrex.webmotors.domain.repository

import com.pedrex.webmotors.presentation.model.ItemModel
import rx.Observable

interface ICarRepository {
    fun getCars(page: Int): Observable<List<ItemModel>>
}
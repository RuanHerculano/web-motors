package com.pedrex.webmotors.domain.interactor

import com.pedrex.webmotors.data.CarRepository
import com.pedrex.webmotors.presentation.model.ItemModel
import rx.Observable

class PopulateListUseCase {

    private val repository = CarRepository()
    private var count = 1
    fun buildUseCaseObservable() : Observable<List<ItemModel>> {
        return repository.getCars(count++)
    }
}
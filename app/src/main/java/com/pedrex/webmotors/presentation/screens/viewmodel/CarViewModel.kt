package com.pedrex.webmotors.presentation.screens.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pedrex.webmotors.domain.interactor.PopulateListUseCase
import com.pedrex.webmotors.presentation.model.ItemModel
import com.pedrex.webmotors.presentation.screens.states.CarStates
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

interface InputCartViewModel {
    fun selectCar(car: ItemModel)
    fun populateList()
}

interface OutputCartViewModel {
    val selectCarLiveData: LiveData<ItemModel>
    val populateCarsLiveData: MutableLiveData<CarStates>
}

interface ContractCartViewModel {
    val input: InputCartViewModel
    val output: OutputCartViewModel
}

class CartViewModel : ContractCartViewModel,
    InputCartViewModel,
    OutputCartViewModel {

    private val useCase = PopulateListUseCase()

    override val input: InputCartViewModel get() = this
    override val output: OutputCartViewModel get() = this

    private val firstObservable = MutableLiveData<ItemModel>()
    override val selectCarLiveData: LiveData<ItemModel> get() = firstObservable

    private val populateObservable = MutableLiveData<CarStates>()
    override val populateCarsLiveData: MutableLiveData<CarStates> get() = populateObservable

    override fun selectCar(car: ItemModel) {
        firstObservable.postValue(car)
    }

    override fun populateList() {
        useCase.buildUseCaseObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(40, TimeUnit.SECONDS)
            .subscribe(
                {
                    populateObservable.postValue(CarStates.LoadCars(it))
                },
                {
                    populateObservable.postValue(
                        CarStates.Error(
                            it.message ?: "Erro não Identificado"
                        )
                    )
                    it.printStackTrace()
                }
            )
    }
}
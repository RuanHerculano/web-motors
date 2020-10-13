package com.pedrex.webmotors.presentation.screens.states

import com.pedrex.webmotors.presentation.model.ItemModel

sealed class CarStates {
    class Error(val message: String) : CarStates()
    class LoadCars(val cars: List<ItemModel>) : CarStates()
}
package com.pedrex.webmotors.data.mapper

import com.pedrex.webmotors.data.cloud.model.response.CarResponse
import com.pedrex.webmotors.presentation.model.ItemModel

class MapperResponseToModel {
    fun transform (entity: CarResponse) : ItemModel {
        return ItemModel(
            name = "${entity.make} ${entity.model}",
            detail = entity.version ?: "",
            imageUrl = entity.imageUrl ?: "",
            color = entity.color ?: "",
            km = entity.km ?: 0,
            year = transformYear(entity.yearFab ?: 0, entity.yearModel ?: 0),
            price = transformPrice(entity.price ?: "")
        )
    }

    fun transform (entity: List<CarResponse>) : List<ItemModel> {
        return entity.map { transform(it) }
    }

    private fun transformPrice(price: String): Int {
        return if (price.isEmpty()) {
            0
        } else {
            price.replace(",", "").toInt() / 100
        }
    }

    private fun transformYear(yearFab: Int, yearModel: Int): String {
        return if (yearFab.toString().length == 4 && yearModel.toString().length == 4) {
            val fab = yearFab.toString().substring(2)
            val model = yearModel.toString().substring(2)
            "$fab/$model"
        } else {
            "$yearFab/$yearModel"
        }
    }
}
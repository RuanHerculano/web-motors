package com.pedrex.webmotors.presentation.model

import java.io.Serializable

data class ItemModel (
    val name: String,
    val detail: String,
    val imageUrl: String,
    val km: Int,
    val price: Int,
    val year: String,
    val color: String
) : Serializable
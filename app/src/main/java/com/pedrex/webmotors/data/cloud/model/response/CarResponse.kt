package com.pedrex.webmotors.data.cloud.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CarResponse (
    @SerializedName("ID") val id: Int?,
    @SerializedName("Make") val make: String?,
    @SerializedName("Model") val model: String?,
    @SerializedName("Version") val version: String?,
    @SerializedName("KM") val km: Int?,
    @SerializedName("Image") val imageUrl: String?,
    @SerializedName("Price") val price: String?,
    @SerializedName("YearModel") val yearModel: Int?,
    @SerializedName("YearFab") val yearFab: Int?,
    @SerializedName("Color") val color: String?
) : Serializable
package com.pedrex.webmotors.extensions

import java.text.NumberFormat
import java.util.*

fun Int.toMoney() : String {
    return NumberFormat
        .getCurrencyInstance(Locale("pt", "BR"))
        .apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }.format(this)
}
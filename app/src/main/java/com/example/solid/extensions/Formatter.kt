package com.example.solid.extensions

import java.text.NumberFormat
import java.util.*

fun Double.formatterKm(): String {
    var currencyInstance = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return currencyInstance.format(this).replace("R$", "") +"/KM"
}
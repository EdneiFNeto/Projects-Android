package com.example.solid.extensions

fun Double.formatterKm(): String {
    return "${this.toString().replace(".", ",")}/KM"
}
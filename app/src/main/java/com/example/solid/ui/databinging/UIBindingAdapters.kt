package com.example.solid.ui.databinging

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.solid.extensions.formatterKm

class UIBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("formatarKM")
        fun TextView.formatarKM(valor: Double) {
            this.text = valor.formatterKm()
        }
    }
}
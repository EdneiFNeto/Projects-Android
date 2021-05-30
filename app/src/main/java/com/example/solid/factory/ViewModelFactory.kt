package com.example.solid.factory

interface ViewModelFactory<T> {
    fun save(t: T, success: (Long) -> Unit, failure: (String) -> Unit = {})
    fun list(success:(List<T>)->Unit)
}

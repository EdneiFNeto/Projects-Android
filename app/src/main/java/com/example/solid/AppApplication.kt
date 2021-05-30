package com.example.solid

import android.app.Application
import com.example.solid.di.modules.appModule
import com.example.solid.di.modules.appModuleDao
import com.example.solid.di.modules.appModuleRepository
import com.example.solid.di.modules.appModuleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(arrayListOf(appModule, appModuleDao, appModuleRepository, appModuleViewModel))
        }
    }
}
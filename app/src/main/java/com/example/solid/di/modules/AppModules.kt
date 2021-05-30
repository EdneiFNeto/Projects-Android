package com.example.solid.di.modules

import androidx.room.Room
import com.example.solid.database.AppDatabase
import com.example.solid.database.dao.UserDao
import com.example.solid.repository.UserRepository
import com.example.solid.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB = "app_db"
val appModule= module {
    single<AppDatabase> {
        Room
            .databaseBuilder(get(), AppDatabase::class.java, DB)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val appModuleDao = module {
    single<UserDao> { get<AppDatabase>().userDao() }
}

val appModuleRepository = module {
    single<UserRepository> { UserRepository(get<UserDao>())  }
}

val appModuleViewModel = module {
    viewModel {
        UserViewModel(get<UserRepository>())
    }
}
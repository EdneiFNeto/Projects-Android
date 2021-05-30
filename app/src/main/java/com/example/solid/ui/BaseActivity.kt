package com.example.solid.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.solid.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity:AppCompatActivity() {
    protected val userViewModel:UserViewModel by viewModel()
}

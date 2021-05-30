package com.example.solid.factory.user

import androidx.lifecycle.LifecycleOwner
import com.example.solid.database.entity.UserEntity
import com.example.solid.factory.ViewModelFactory
import com.example.solid.ui.viewmodel.UserViewModel

private const val TAG = "UserViewModelFactory"

class UserViewModelFactory(
    private val lifecycleOwner: LifecycleOwner,
    private val userViewModel: UserViewModel
) : ViewModelFactory<UserEntity> {

    override fun save(userEntity: UserEntity, success: (Long) -> Unit, failure: (String) -> Unit) {
        userViewModel.save(userEntity).observe(lifecycleOwner, {
            when  {
                it > 0L -> success(it)
                else -> failure("Failure save")
            }
        })
    }

    override fun list(success:(List<UserEntity>)->Unit){
        userViewModel.list().observe(lifecycleOwner, {
            if (it.dado != null) {
                success(it.dado)
            }
        })
    }
}

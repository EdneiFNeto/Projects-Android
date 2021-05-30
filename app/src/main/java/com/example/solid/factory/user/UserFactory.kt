package com.example.solid.factory.user

import com.example.solid.database.entity.UserEntity

class UserFactory(private val modelFactory: UserViewModelFactory) {
    fun save(entity: UserEntity,success:(Long)->Unit, failure:(String)->Unit ) {
        modelFactory.save(entity, success, failure)
    }

    fun list(success:(List<UserEntity>)->Unit) {
        modelFactory.list(success)
    }
}

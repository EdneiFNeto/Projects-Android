package com.example.solid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.solid.database.dao.UserDao
import com.example.solid.database.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(private val dao: UserDao) {
    private var scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var entitys = ArrayList<UserEntity>()
    private var isSaved = 0L

    fun save(entity: UserEntity): LiveData<Long> {
        val liveData = MutableLiveData<Long>()
        scope.launch {
            isSaved = dao.save(entity)
            withContext(Dispatchers.Main) {
                liveData.value = isSaved
            }
        }

        return liveData
    }

    fun list(): LiveData<Resources<List<UserEntity>>> {
        val liveData = MutableLiveData<Resources<List<UserEntity>>>()
        scope.launch {
            entitys = dao.list() as ArrayList<UserEntity>
            withContext(Dispatchers.Main) {
                liveData.value = Resources(dado = entitys)
            }
        }

        return liveData
    }
}

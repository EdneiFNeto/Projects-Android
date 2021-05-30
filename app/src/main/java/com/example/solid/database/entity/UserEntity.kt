package com.example.solid.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(@PrimaryKey(autoGenerate = true)
                     val id: Long?,
                      val nome: String,
                      val password: String)

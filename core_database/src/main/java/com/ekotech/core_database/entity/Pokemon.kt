package com.ekotech.core_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String,
)
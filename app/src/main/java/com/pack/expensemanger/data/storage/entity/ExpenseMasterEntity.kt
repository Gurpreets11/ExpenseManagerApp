package com.pack.expensemanger.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_master")
class ExpenseMasterEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long,   // store timestamp
    val notes: String? = null
)
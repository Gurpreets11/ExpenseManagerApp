package com.pack.expensemanger.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_master")
class ExpenseMasterEntity(

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val expenseDateTime: Long,   // store timestamp
    val paymentMethod : Int,
    val categoryId: Int,
    val category: String,
    val subCategoryId: Int,
    val subCategory: String,
    val paymentStatus : Int,
    val notes: String? = null
)
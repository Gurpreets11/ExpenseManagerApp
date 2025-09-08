package com.pack.expensemanger.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_method")
data class PaymentMethodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val methodName: String
)

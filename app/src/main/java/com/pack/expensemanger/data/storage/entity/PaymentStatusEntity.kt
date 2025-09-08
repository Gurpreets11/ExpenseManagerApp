package com.pack.expensemanger.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_status")
data class PaymentStatusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val statusName: String
)

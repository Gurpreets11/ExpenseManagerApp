package com.pack.expensemanger.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pack.expensemanger.data.storage.entity.PaymentStatusEntity

@Dao
interface PaymentStatusDao {
    @Insert
    suspend fun insert(paymentStatus: PaymentStatusEntity)

    @Query("SELECT COUNT(*) FROM payment_status")
    fun getCount(): Int


    @Query("SELECT * FROM payment_status ORDER BY statusName ASC")
    fun getAllPaymentStatuses(): LiveData<List<PaymentStatusEntity>>
}
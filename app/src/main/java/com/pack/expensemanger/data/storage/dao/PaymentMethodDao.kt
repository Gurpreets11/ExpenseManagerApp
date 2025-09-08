package com.pack.expensemanger.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity

@Dao
interface PaymentMethodDao {
    @Insert
    suspend fun insert(paymentMethod: PaymentMethodEntity)

    @Query("SELECT COUNT(*) FROM payment_method")
    fun getCount(): Int

    @Query("SELECT * FROM payment_method ORDER BY methodName ASC")
    fun getAllPaymentMethods(): LiveData<List<PaymentMethodEntity>>
}
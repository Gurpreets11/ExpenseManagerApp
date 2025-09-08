package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.PaymentStatusDao
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity
import com.pack.expensemanger.data.storage.entity.PaymentStatusEntity

class PaymentStatusRepository(private val dao: PaymentStatusDao) {
    //fun getAllPaymentStatuses() = dao.getAllPaymentStatuses()
    val allPaymentStatus: LiveData<List<PaymentStatusEntity>> = dao.getAllPaymentStatuses()

    fun getCount() = dao.getCount()
    suspend fun insert(paymentStatus: PaymentStatusEntity) = dao.insert(paymentStatus)
}
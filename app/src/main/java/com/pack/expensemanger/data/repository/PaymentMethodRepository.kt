package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.PaymentMethodDao
import com.pack.expensemanger.data.storage.entity.CategoryEntity
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity

class PaymentMethodRepository(private val dao: PaymentMethodDao) {
    val allPaymentMethod: LiveData<List<PaymentMethodEntity>>  = dao.getAllPaymentMethods()
    fun getCount() = dao.getCount()
    suspend fun insert(paymentMethod: PaymentMethodEntity) = dao.insert(paymentMethod)
}
package com.pack.expensemanger.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pack.expensemanger.data.repository.PaymentStatusRepository
import com.pack.expensemanger.data.storage.AppDatabase
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity
import com.pack.expensemanger.data.storage.entity.PaymentStatusEntity
import kotlinx.coroutines.launch

class PaymentStatusViewModel(private val repository: PaymentStatusRepository) : ViewModel() {
    //private val repository: PaymentStatusRepository

    //val allPaymentStatuses by lazy { repository.getAllPaymentStatuses() }
    val allPaymentStatuses: LiveData<List<PaymentStatusEntity>> = repository.allPaymentStatus

    /*init {
        val dao = AppDatabase.getDatabase(application).paymentStatusDao()
        repository = PaymentStatusRepository(dao)
    }*/

    val count by lazy { repository.getCount() }

    fun insert(paymentStatus: PaymentStatusEntity) = viewModelScope.launch {
        repository.insert(paymentStatus)
    }
}
package com.pack.expensemanger.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pack.expensemanger.data.repository.PaymentMethodRepository
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity
import kotlinx.coroutines.launch

class PaymentMethodViewModel(private val repository: PaymentMethodRepository) : ViewModel() {
    //private val repository: PaymentMethodRepository

    val allPaymentMethods: LiveData<List<PaymentMethodEntity>> = repository.allPaymentMethod

    /*init {
        val dao = AppDatabase.getDatabase(application).paymentMethodDao()
        repository = PaymentMethodRepository(dao)
    }*/

    val count by lazy { repository.getCount() }


    fun insert(paymentMethod: PaymentMethodEntity) = viewModelScope.launch {
        repository.insert(paymentMethod)
    }
}
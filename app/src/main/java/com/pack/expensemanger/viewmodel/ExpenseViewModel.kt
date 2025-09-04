package com.pack.expensemanger.viewmodel

import com.pack.expensemanger.data.repository.ExpenseRepository
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity


import androidx.lifecycle.*

import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    val allExpenses: LiveData<List<ExpenseMasterEntity>> = repository.allExpenses

    fun insert(expense: ExpenseMasterEntity) = viewModelScope.launch {
        repository.insert(expense)
    }

    fun update(expense: ExpenseMasterEntity) = viewModelScope.launch {
        repository.update(expense)
    }

    fun delete(expense: ExpenseMasterEntity) = viewModelScope.launch {
        repository.delete(expense)
    }
}

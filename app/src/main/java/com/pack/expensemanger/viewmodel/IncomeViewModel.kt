package com.pack.expensemanger.viewmodel


import androidx.lifecycle.*
import com.pack.expensemanger.data.repository.IncomeRepository
import com.pack.expensemanger.data.storage.entity.IncomeEntity

import kotlinx.coroutines.launch

class IncomeViewModel(private val repository: IncomeRepository) : ViewModel() {

    val allIncome: LiveData<List<IncomeEntity>> = repository.allIncome

    fun insert(income: IncomeEntity) = viewModelScope.launch {
        repository.insert(income)
    }

    fun update(income: IncomeEntity) = viewModelScope.launch {
        repository.update(income)
    }

    fun delete(income: IncomeEntity) = viewModelScope.launch {
        repository.delete(income)
    }
}

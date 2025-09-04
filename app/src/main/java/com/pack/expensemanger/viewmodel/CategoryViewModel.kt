package com.pack.expensemanger.viewmodel


import androidx.lifecycle.*
import com.pack.expensemanger.data.repository.CategoryRepository
import com.pack.expensemanger.data.storage.entity.CategoryEntity
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    val allCategories: LiveData<List<CategoryEntity>> = repository.allCategories

    fun insert(category: CategoryEntity) = viewModelScope.launch {
        repository.insert(category)
    }

    fun update(category: CategoryEntity) = viewModelScope.launch {
        repository.update(category)
    }

    fun delete(category: CategoryEntity) = viewModelScope.launch {
        repository.delete(category)
    }
}

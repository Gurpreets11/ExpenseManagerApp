package com.pack.expensemanger.viewmodel


import androidx.lifecycle.*
import com.pack.expensemanger.data.repository.SubCategoryRepository
import com.pack.expensemanger.data.storage.entity.SubCategoryEntity
import kotlinx.coroutines.launch

class SubCategoryViewModel(private val repository: SubCategoryRepository) : ViewModel() {

    //val allSubCategories: LiveData<List<SubCategoryEntity>> = repository.allSubCategories


    /*private val selectedCategoryId = MutableLiveData<Int>()

    val subCategories: LiveData<List<SubCategoryEntity>> =
        Transformations.switchMap(selectedCategoryId) { categoryId ->
            repository.getSubCategoriesByCategory(categoryId)
        }

    fun setCategoryId(categoryId: Int) {
        selectedCategoryId.value = categoryId
    }*/

    fun getSubCategoriesByCategory(categoryId: Int): LiveData<List<SubCategoryEntity>> {
        return repository.getSubCategoriesByCategory(categoryId)
    }

    fun insert(subCategory: SubCategoryEntity) = viewModelScope.launch {
        repository.insert(subCategory)
    }

    fun update(subCategory: SubCategoryEntity) = viewModelScope.launch {
        repository.update(subCategory)
    }

    fun delete(subCategory: SubCategoryEntity) = viewModelScope.launch {
        repository.delete(subCategory)
    }
}

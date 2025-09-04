package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.CategoryDao
import com.pack.expensemanger.data.storage.entity.CategoryEntity

class CategoryRepository(private val categoryDao: CategoryDao) {

    val allCategories: LiveData<List<CategoryEntity>> = categoryDao.getAllCategories()

    suspend fun insert(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    suspend fun update(category: CategoryEntity) {
        categoryDao.updateCategory(category)
    }

    suspend fun delete(category: CategoryEntity) {
        categoryDao.deleteCategory(category)
    }
}

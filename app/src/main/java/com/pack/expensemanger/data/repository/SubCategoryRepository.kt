package com.pack.expensemanger.data.repository

import androidx.lifecycle.LiveData
import com.pack.expensemanger.data.storage.dao.SubCategoryDao
import com.pack.expensemanger.data.storage.entity.CategoryEntity
import com.pack.expensemanger.data.storage.entity.SubCategoryEntity

class SubCategoryRepository(private val subCategoryDao: SubCategoryDao) {


    fun getSubCategoriesByCategory(categoryId: Int): LiveData<List<SubCategoryEntity>> {
        return subCategoryDao.getSubCategoriesByCategory(categoryId)
    }

    suspend fun insert(subCategory: SubCategoryEntity) {
        subCategoryDao.insertSubCategory(subCategory)
    }

    suspend fun update(subCategory: SubCategoryEntity) {
        subCategoryDao.updateSubCategory(subCategory)
    }

    suspend fun delete(subCategory: SubCategoryEntity) {
        subCategoryDao.deleteSubCategory(subCategory)
    }
}

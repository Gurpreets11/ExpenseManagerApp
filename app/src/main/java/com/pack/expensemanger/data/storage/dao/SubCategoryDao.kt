package com.pack.expensemanger.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pack.expensemanger.data.storage.entity.SubCategoryEntity

@Dao
interface SubCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategory(subCategory: SubCategoryEntity)

    @Update
    suspend fun updateSubCategory(subCategory: SubCategoryEntity)

    @Delete
    suspend fun deleteSubCategory(subCategory: SubCategoryEntity)

    @Query("SELECT * FROM subcategories WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getSubCategoriesByCategory(categoryId: Int): LiveData<List<SubCategoryEntity>>
}

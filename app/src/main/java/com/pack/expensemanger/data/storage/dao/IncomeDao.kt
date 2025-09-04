package com.pack.expensemanger.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pack.expensemanger.data.storage.entity.IncomeEntity

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(income: IncomeEntity)

    @Update
    suspend fun updateIncome(income: IncomeEntity)

    @Delete
    suspend fun deleteIncome(income: IncomeEntity)

    @Query("SELECT * FROM income ORDER BY date DESC")
    fun getAllIncome(): LiveData<List<IncomeEntity>>
}

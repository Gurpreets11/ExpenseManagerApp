package com.pack.expensemanger.data.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity

@Dao
interface ExpenseMasterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseMasterEntity)

    @Update
    suspend fun updateExpense(expense: ExpenseMasterEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseMasterEntity)

    @Query("SELECT * FROM expenses_master ORDER BY date DESC")
    fun getAllExpenses(): LiveData<List<ExpenseMasterEntity>>

}
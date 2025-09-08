package com.pack.expensemanger.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pack.expensemanger.data.storage.dao.CategoryDao
import com.pack.expensemanger.data.storage.dao.ExpenseMasterDao
import com.pack.expensemanger.data.storage.dao.IncomeDao
import com.pack.expensemanger.data.storage.dao.PaymentMethodDao
import com.pack.expensemanger.data.storage.dao.PaymentStatusDao
import com.pack.expensemanger.data.storage.dao.SubCategoryDao
import com.pack.expensemanger.data.storage.entity.CategoryEntity
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity
import com.pack.expensemanger.data.storage.entity.IncomeEntity
import com.pack.expensemanger.data.storage.entity.PaymentMethodEntity
import com.pack.expensemanger.data.storage.entity.PaymentStatusEntity
import com.pack.expensemanger.data.storage.entity.SubCategoryEntity

@Database(
    entities = [ExpenseMasterEntity::class, IncomeEntity::class,
        CategoryEntity::class, SubCategoryEntity::class,
        PaymentMethodEntity::class, PaymentStatusEntity::class],
    version = 3, // incremented version since we added new tables
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseMasterDao
    abstract fun incomeDao(): IncomeDao
    abstract fun categoryDao(): CategoryDao
    abstract fun subCategoryDao(): SubCategoryDao
    abstract fun paymentMethodDao(): PaymentMethodDao
    abstract fun paymentStatusDao(): PaymentStatusDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expenses_db"
                ).fallbackToDestructiveMigration() // resets DB if schema changes
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}



package com.example.budgettracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgettracker.database.expense.ExpenseDao
import com.example.budgettracker.database.income.IncomeDao
import com.example.budgettracker.database.totalBudget.TotalBudgetDao
import com.example.budgettracker.model.Expense
import com.example.budgettracker.model.Income
import com.example.budgettracker.model.TotalBudget

@Database(
    entities = [Income::class, Expense::class, TotalBudget::class],
    version = AppDatabase.DATABASE_VERSION + 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun totalBudgetDao(): TotalBudgetDao

    companion object {

        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "my_budget"

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE budget ADD COLUMN description TEXT NOT NULL DEFAULT ''")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE expense ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE income ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}
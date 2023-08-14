package com.example.budgettracker.database.expense

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.budgettracker.model.Expense

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    fun getAll(): List<Expense>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expense: Expense)

    @Query("UPDATE expenses SET amount = :newAmount WHERE id = :id")
    fun updateAmount(id: Int, newAmount: Double)

    @Query("UPDATE expenses SET description = :newDescription WHERE id = :id")
    fun updateDescription(id: Int, newDescription: String)

    @Query("UPDATE expenses SET type = :newType WHERE id = :id")
    fun updateType(id: Int, newType: Byte)

    @Query("DELETE FROM expenses WHERE id = :id")
    fun deleteExpense(id: Int)

    @Query("DELETE FROM expenses")
    fun deleteAll()

    @Query("SELECT SUM(amount) FROM expenses")
    suspend fun getTotalExpenses(): Double

}
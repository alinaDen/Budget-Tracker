package com.example.budgettracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "incomes")
data class Income(

    val type: Byte,
    val amount: Double,
    val description: String,
    @ColumnInfo(name = "date_added")
    val dateAdded: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0)


{companion object {
    const val TYPE_SALARY: Byte = 1
    const val TYPE_GIFT: Byte = 2
    const val TYPE_OTHER: Byte = 3




}}
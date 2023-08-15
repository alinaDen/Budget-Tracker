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
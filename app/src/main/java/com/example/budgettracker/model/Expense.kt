package com.example.budgettracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    val type: Byte,
    val amount: Double,
    val description: String,
    @ColumnInfo(name = "date_added")
    val dateAdded: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
{companion object {
    const val TYPE_FOOD: Byte = 1
    const val TYPE_TRANSPORT: Byte = 2
    const val TYPE_MEDICINE: Byte = 3
    const val TYPE_COSMETICS: Byte = 4
    const val TYPE_CLOTHES: Byte= 5
    const val TYPE_VACATIONS: Byte = 6
    const val TYPE_EDUCATION: Byte = 7
    const val TYPE_SPORT: Byte = 8
    const val TYPE_RESTAURANTS: Byte = 9
    const val TYPE_ENTERTAINMENT: Byte = 10
    const val TYPE_VEHICLE: Byte = 11
    const val TYPE_OTHER: Byte = 12
    const val TYPE_SAVING: Byte = 13



}}


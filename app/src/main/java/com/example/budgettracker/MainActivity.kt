package com.example.budgettracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.budgettracker.databinding.ActivityMainBinding
import com.example.budgettracker.repository.ExpenseRepository

class MainActivity : AppCompatActivity(),MainFragment.AddExpenseListener,MainFragment.AddEIncomeListener{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment.newInstance("", ""))
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.fragments.lastOrNull()?.tag != TAG_MAIN){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,MainFragment.newInstance("",""), TAG_MAIN)
                .commit()
        }else{
            super.onBackPressed()
        }
    }

    override fun addExpense(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddExpenseFragment.newInstance("","",), TAG_EXPENSE)
            .commit()
    }
    override fun addIncome(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddIncomeFragment.newInstance("",""), TAG_INCOME)
            .commit()
    }
    companion object{
        const val TAG_EXPENSE = "AddExpenseFragment"
        const val TAG_INCOME = "AddIncomeFragment"
        const val TAG_MAIN = "MainFragment"
    }
}
package com.example.budgettracker.repository

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.R
import com.example.budgettracker.databinding.ExpenseItemBinding
import com.example.budgettracker.model.Expense

    class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    private var expenseList: MutableList<Expense> = mutableListOf()
    inner class ExpenseViewHolder(private val itemBinding: ExpenseItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(expense: Expense) {
            itemBinding.expenseAmount.text = expense.amount.toString()

            when(expense.type.toInt()){
                1 -> itemBinding.expenseType.setImageResource(R.drawable.food)
                2-> itemBinding.expenseType.setImageResource(R.drawable.transport)
                3-> itemBinding.expenseType.setImageResource(R.drawable.medicine)
                4-> itemBinding.expenseType.setImageResource(R.drawable.cosmetics)
                5-> itemBinding.expenseType.setImageResource(R.drawable.clothes)
                6-> itemBinding.expenseType.setImageResource(R.drawable.vacations)
                7-> itemBinding.expenseType.setImageResource(R.drawable.education)
                8-> itemBinding.expenseType.setImageResource(R.drawable.sport)
                9-> itemBinding.expenseType.setImageResource(R.drawable.restaurants)
                10-> itemBinding.expenseType.setImageResource(R.drawable.entertainment)
                11-> itemBinding.expenseType.setImageResource(R.drawable.car)
                12-> itemBinding.expenseType.setImageResource(R.drawable.other)
                13-> itemBinding.expenseType.setImageResource(R.drawable.savings)

                else -> itemBinding.expenseType.setImageResource(R.drawable.other)

            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemBinding =ExpenseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ExpenseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val animal = expenseList[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setAnimalList(animals: List<Expense>) {
        expenseList.clear()
        expenseList.addAll(animals)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateAnimals(newAnimals: MutableList<Expense>) {
        expenseList = newAnimals
        notifyDataSetChanged()
    }

}

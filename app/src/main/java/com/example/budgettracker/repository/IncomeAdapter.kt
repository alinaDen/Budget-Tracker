package com.example.budgettracker.repository

import com.example.budgettracker.databinding.IncomeItemBinding
import com.example.budgettracker.model.Income
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettracker.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {
    private var incomeList: MutableList<Income> = mutableListOf()
    inner class IncomeViewHolder(private val itemBinding: IncomeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(income: Income) {
            itemBinding.incomeAmount.text = income.amount.toString()

            when(income.type.toInt()){
                1 -> itemBinding.incomeType.setImageResource(R.drawable.salary)
                2-> itemBinding.incomeType.setImageResource(R.drawable.gift)
                3-> itemBinding.incomeType.setImageResource(R.drawable.other)

                else -> itemBinding.incomeType.setImageResource(R.drawable.other)

            }
            val pattern = "dd.MM.yy HH:mm"
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            itemBinding.dateIncomeItem.text = sdf.format(Date(income.dateAdded))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val itemBinding =IncomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return IncomeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val income= incomeList[position]
        holder.bind(income)
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    fun setIncomeList(income: List<Income>) {
        incomeList.clear()
        incomeList.addAll(income)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateIncome(newIncome: MutableList<Income>) {
        incomeList = newIncome
        notifyDataSetChanged()
    }

}

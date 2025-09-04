package com.pack.expensemanger.ui.adapter

import com.pack.expensemanger.R
import com.pack.expensemanger.data.storage.entity.IncomeEntity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class IncomeAdapter(private var incomeList: List<IncomeEntity>) :
    RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSource: TextView = itemView.findViewById(R.id.txtSource)
        val txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
        val txtDate: TextView = itemView.findViewById(R.id.txtDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_income, parent, false)
        return IncomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val income = incomeList[position]
        holder.txtSource.text = income.title
        holder.txtAmount.text = "₹ ${income.amount}"
        holder.txtDate.text = income.date
    }

    override fun getItemCount(): Int = incomeList.size

    fun updateList(newList: List<IncomeEntity>) {
        incomeList = newList
        notifyDataSetChanged()
    }
}

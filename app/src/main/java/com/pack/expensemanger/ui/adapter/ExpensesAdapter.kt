package com.pack.expensemanger.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity
import com.pack.expensemanger.databinding.ItemExpenseBinding


class ExpenseAdapter(private var expensesList: List<ExpenseMasterEntity>) : RecyclerView.Adapter<  ExpenseAdapter.ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun getItemCount(): Int = expensesList.size

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expensesList[position])
    }

    class ExpenseViewHolder(private val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expense: ExpenseMasterEntity) {
            binding.tvExpenseAmount.text = "₹${expense.amount}"
            binding.tvExpenseDescription.text = expense.title
        }
    }

    fun updateList(newList: List<ExpenseMasterEntity>) {
        expensesList = newList
        notifyDataSetChanged()
    }

    /*class ExpenseDiffCallback : DiffUtil.ItemCallback<ExpenseMasterEntity>() {
        override fun areItemsTheSame(oldItem: ExpenseMasterEntity, newItem: ExpenseMasterEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ExpenseMasterEntity, newItem: ExpenseMasterEntity): Boolean =
            oldItem == newItem
    }*/
}

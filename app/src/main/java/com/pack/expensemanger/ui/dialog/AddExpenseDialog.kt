package com.pack.expensemanger.ui.dialog


import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.pack.expensemanger.R
import com.pack.expensemanger.data.storage.entity.ExpenseMasterEntity


class AddExpenseDialog(private val onExpenseAdded: (ExpenseMasterEntity) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add_expense, null)
        val etAmount = view.findViewById<EditText>(R.id.etExpenseAmount)
        val etDescription = view.findViewById<EditText>(R.id.etExpenseDescription)

        return AlertDialog.Builder(requireContext())
            .setTitle("Add Expense")
            .setView(view)
            .setPositiveButton("Add") { _, _ ->
                val amount = etAmount.text.toString().toDoubleOrNull() ?: 0.0
                val description = etDescription.text.toString()
                if (amount > 0) {
                    //val expense = ExpenseMasterEntity(id = 0, title = description, amount = amount, category = "EXPENSES", date = 0, notes = "")
                    //onExpenseAdded(expense)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}

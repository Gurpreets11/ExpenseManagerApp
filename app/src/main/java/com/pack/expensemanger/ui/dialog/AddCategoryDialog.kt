package com.pack.expensemanger.ui.dialog

import com.pack.expensemanger.R
import com.pack.expensemanger.data.storage.entity.CategoryEntity


import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class AddCategoryDialog(private val onCategoryAdded: (CategoryEntity) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add_category, null)
        val etCategoryName = view.findViewById<EditText>(R.id.etCategoryName)

        return AlertDialog.Builder(requireContext())
            .setTitle("Add Category")
            .setView(view)
            .setPositiveButton("Add") { _, _ ->
                val name = etCategoryName.text.toString()
                if (name.isNotEmpty()) {
                    val category = CategoryEntity(name = name)
                    onCategoryAdded(category)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}

package com.mustafaunlu.shoopapp.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showConfirmationDialog(message: String, onConfirm: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setMessage(message)
        .setPositiveButton("Yes") { _, _ -> onConfirm.invoke() }
        .setNegativeButton("No", null)
        .show()
}

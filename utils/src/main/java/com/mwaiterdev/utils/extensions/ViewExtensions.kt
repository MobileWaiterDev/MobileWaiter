package com.mwaiterdev.utils.extensions

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.showSnakeBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun showAlertDialogFragment(context: Context, message: String?){
    AlertDialog.Builder(context)
        .setTitle(message)
        .setMessage(message)
        .setPositiveButton("ok", {dialog, _ -> dialog.cancel()})
        .show()
}
package com.mwaiterdev.waiter.ui.amountdialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.waiter.databinding.FragmentAmountDialogBinding

class AmountDialog(private val typeValue: AmountTypeValue) : DialogFragment() {

    private var listener: IAmountDialog? = null
    private val viewBinding: FragmentAmountDialogBinding by viewBinding(CreateMethod.INFLATE)

    private var args: Bundle? = null

    interface IAmountDialog {
        fun resultValue(resultCode: Int, data: Intent?, args: Bundle?)
    }

    fun setAmountDialogListener(listener: IAmountDialog?) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        args = arguments
        val title = args?.getString(ARG_TITLE)
        val message = args?.getString(ARG_MESSAGE)

        return AlertDialog.Builder(activity)
            .setCancelable(false)
            .setTitle(title)
            .setMessage(message)
            .setView(viewBinding.root)
            .setPositiveButton(OK_BUTTON_TITLE) { _, _ -> buttonOkClick() }
            .setNegativeButton(CANCEL_BUTTON_TITLE) { _, _ -> buttonNoClick() }
            .create()
            .also {
                when (typeValue) {
                    AmountTypeValue.INTEGER -> viewBinding.inputValue.inputType =
                        TYPE_CLASS_NUMBER
                    AmountTypeValue.FLOAT -> {
                        viewBinding.inputValue.inputType =
                            TYPE_CLASS_NUMBER or TYPE_NUMBER_FLAG_DECIMAL
                    }
                    AmountTypeValue.STRING -> viewBinding.inputValue.inputType =
                        InputType.TYPE_CLASS_TEXT
                }
            }
    }

    private fun buttonOkClick() =
        when (typeValue) {
            AmountTypeValue.INTEGER -> {
                viewBinding.inputValue.text.toString().toIntOrNull()?.let {
                    Intent().putExtras(bundleOf().apply {
                        putInt(KEY_RESULT, it)
                    }).also {
                        listener?.resultValue(Activity.RESULT_OK, it, args)
                    }
                } ?: listener?.resultValue(Activity.RESULT_CANCELED, null, null)
            }
            AmountTypeValue.FLOAT -> {
                viewBinding.inputValue.text.toString().toFloatOrNull()?.let {
                    Intent().putExtras(bundleOf().apply {
                        putFloat(KEY_RESULT, it)
                    }).also {
                        listener?.resultValue(Activity.RESULT_OK, it, args)
                    }
                } ?: listener?.resultValue(Activity.RESULT_CANCELED, null, null)
            }
            AmountTypeValue.STRING -> {
                Intent().putExtras(bundleOf().apply {
                    putString(KEY_RESULT, viewBinding.inputValue.text.toString())
                }).also {
                    listener?.resultValue(Activity.RESULT_OK, it, args)
                }
            }
        }

    private fun buttonNoClick() = listener?.resultValue(Activity.RESULT_CANCELED, null, null)

    companion object {
        const val KEY_RESULT = "result"
        const val OK_BUTTON_TITLE = "Применить"
        const val CANCEL_BUTTON_TITLE = "Отмена"
        const val TAG = "dialog"
        const val ARG_TITLE = "dialog.Title"
        const val ARG_MESSAGE = "dialog.Message"
    }
}
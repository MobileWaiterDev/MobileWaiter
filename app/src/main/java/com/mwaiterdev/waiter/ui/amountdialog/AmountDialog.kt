package com.mwaiterdev.waiter.ui.amountdialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.utils.extensions.showSnakeBar
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

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        viewBinding.btnOk.setOnClickListener {
            if (viewBinding.inputValue.text.toString().isEmpty()) {
                viewBinding.root.showSnakeBar(ENTER_AMOUNT_TEXT)
                return@setOnClickListener
            }
            buttonOkClick()
            dialog?.dismiss()
        }

        viewBinding.btnCancel.setOnClickListener {
            buttonNoClick()
            dialog?.dismiss()
        }

        viewBinding.inputValue.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (TextUtils.isEmpty(v.text.toString()).not()) {
                    buttonOkClick()
                    dialog?.dismiss()
                    false
                } else {
                    viewBinding.root.showSnakeBar(ENTER_AMOUNT_TEXT)
                    false
                }
            }
            false
        }

        Handler(Looper.getMainLooper()).postDelayed({
            viewBinding.inputValue.requestFocus()
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(
                viewBinding.inputValue,
                InputMethodManager.SHOW_IMPLICIT
            )
        }, 100)

        return viewBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        args = arguments
        val title = args?.getString(ARG_TITLE)
        val message = args?.getString(ARG_MESSAGE)

        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setView(viewBinding.root)
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
        const val TAG = "dialog"
        const val ARG_TITLE = "dialog.Title"
        const val ARG_MESSAGE = "dialog.Message"
        const val ENTER_AMOUNT_TEXT = "Введите количество"
    }
}
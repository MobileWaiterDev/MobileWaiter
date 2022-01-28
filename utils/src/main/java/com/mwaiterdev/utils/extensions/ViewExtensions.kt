package com.mwaiterdev.utils.extensions

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.utils.R

fun View.showSnakeBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

fun View.longClick(longClick: () -> Boolean) = setOnLongClickListener { longClick() }

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}

fun showAlertDialogFragment(context: Context, message: String?) {
    AlertDialog.Builder(context)
        .setTitle(message)
        .setMessage(message)
        .setPositiveButton("ok", { dialog, _ -> dialog.cancel() })
        .show()
}

fun MaterialCardView.setStrokeColorByState(billItem: BillItem) {
    strokeColor = when (billItem.printed) {
        ITEM_NOT_PRINTED -> {
            Color.parseColor(ITEM_NOT_PRINTED_COLOR)
        }
        ITEM_PRINTED -> {
            Color.parseColor(ITEM_PRINTED_COLOR)
        }
        ITEM_CANCELED -> {
            Color.parseColor(ITEM_CANCELED_COLOR)
        }
        else -> 0
    }
}

fun View.setBackgroundByState(billItem: BillItem) {
    val backgroundStyle = when (billItem.printed) {
        ITEM_NOT_PRINTED -> {
            R.drawable.round_bill_item_blue
        }
        ITEM_PRINTED -> {
            R.drawable.round_bill_item_orange
        }
        ITEM_CANCELED -> {
            R.drawable.round_bill_item_red
        }
        else -> 0
    }
    setBackgroundResource(backgroundStyle)
}

fun MaterialCardView.setBackgroundColorByState(state: Int) {
    val color: Int = when (state) {
        TABLE_STATE_FREE -> {
            Color.parseColor(TABLE_COLOR_FREE)
        }
        TABLE_STATE_BUSY -> {
            Color.parseColor(TABLE_COLOR_BUSY)
        }
        else -> 0
    }
    setCardBackgroundColor(color)
}

const val ITEM_PRINTED = 1
const val ITEM_NOT_PRINTED = 0
const val ITEM_CANCELED = 2
const val ITEM_PRINTED_COLOR = "#FFAB00"
const val ITEM_NOT_PRINTED_COLOR = "#0087D4"
const val ITEM_CANCELED_COLOR = "#FF3738"

const val TABLE_STATE_FREE = 0
const val TABLE_STATE_BUSY = 1
const val TABLE_COLOR_FREE = "#FFFFFF"
const val TABLE_COLOR_BUSY = "#B4E4FF"
package com.mwaiterdev.waiter.ui.bill.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.utils.extensions.setBackgroundByState
import com.mwaiterdev.utils.extensions.setStrokeColorByState
import com.mwaiterdev.waiter.databinding.BillItemBinding

class BillItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: BillItemBinding by viewBinding()

    @SuppressLint("ResourceType")
    fun bind(billItem: BillItem, delegate: BillItemAdapter.Delegate?) {
        with(viewBinding) {
            subTotal.text =
                String.format(TOTAL_STRING_DETAIL_TEMPLATE, billItem.amount, billItem.price)
            total.text = String.format(TOTAL_STRING_TEMPLATE, billItem.total)
            amount.text = billItem.amount.toString()
            name.text = billItem.name
            dataCreate.text = billItem.createTime
            root.click { delegate?.onBillItemPicked(billItem) }
            round.setBackgroundByState(billItem)
            root.setStrokeColorByState(billItem)

            if (billItem.printed == ZERO_VALUE) {
                amount.click { delegate?.onUpdateAmountPicked(billItem) }
            } else {
                amount.click {
                    null
                }
            }

            if (billItem.printed == STATE_EMERGENCY_DELETED
                || billItem.printed == BILL_PRINTED
            ) {
                if (billItem.printed == STATE_EMERGENCY_DELETED) {
                    name.paintFlags = name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }

                root.tag = NO_SWIPE_TAG
            } else {
                root.tag = SWIPE_TAG
                name.paintFlags = DEFAULT_PAINT_FLAGS
            }
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val TOTAL_STRING_DETAIL_TEMPLATE = "%s x %s"
        const val NO_SWIPE_TAG = "no_swipe"
        const val SWIPE_TAG = "swipe"
        const val ZERO_VALUE = 0
        const val STATE_EMERGENCY_DELETED = 2
        const val BILL_PRINTED = 3
        const val DEFAULT_PAINT_FLAGS = 1281
        const val TOTAL_STRING_TEMPLATE = "%s₽"
    }
}
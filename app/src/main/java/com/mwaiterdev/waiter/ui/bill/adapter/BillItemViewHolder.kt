package com.mwaiterdev.waiter.ui.bill.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.waiter.databinding.BillItemBinding

class BillItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: BillItemBinding by viewBinding()

    fun bind(billItem: BillItem, delegate: BillItemAdapter.Delegate?) {
        with(viewBinding) {
            subTotal.text = String.format(TOTAL_STRING_TEMPLATE, billItem.amount, billItem.price)
            total.text = billItem.total.toString()
            amount.text = billItem.amount.toString()
            name.text = billItem.name
            dataCreate.text = billItem.createTime
            root.click { delegate?.onBillItemPicked(billItem) }
            amount.click { delegate?.onUpdateAmountPicked(billItem) }
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val TOTAL_STRING_TEMPLATE = "%s x %s"
    }
}
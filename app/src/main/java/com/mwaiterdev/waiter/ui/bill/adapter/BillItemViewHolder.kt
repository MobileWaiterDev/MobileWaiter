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
            total.text = String.format(TOTAL_STRING_TEMPLATE, billItem.amount, billItem.price)
            amount.text = billItem.amount.toString()
            name.text = billItem.name
            root.click { delegate?.onItemPicked(billItem) }
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val TOTAL_STRING_TEMPLATE = "%s x %s"
    }
}
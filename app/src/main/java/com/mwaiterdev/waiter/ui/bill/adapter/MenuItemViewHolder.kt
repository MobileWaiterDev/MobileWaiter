package com.mwaiterdev.waiter.ui.bill.adapter

import android.graphics.Color
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.utils.extensions.longClick
import com.mwaiterdev.waiter.databinding.BillMenuProductItemBinding

class MenuItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: BillMenuProductItemBinding by viewBinding()
    fun bind(item: Item, delegate: MenuAdapter.Delegate?) {
        with(viewBinding) {
            itemName.text = item.name
            itemPrice.text = String.format(TOTAL_STRING_TEMPLATE, item.price.price)
            favIcon.isVisible = item.favourite > 0
            card.click { delegate?.onItemPicked(item) }
            card.longClick { delegate?.onItemLongClick(item);true }
            card.setCardBackgroundColor(Color.parseColor(item.bgColor))
            itemName.setTextColor(Color.parseColor(item.textColor))
            itemPrice.setTextColor(Color.parseColor(item.textColor))
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val TOTAL_STRING_TEMPLATE = "%s₽"
    }
}
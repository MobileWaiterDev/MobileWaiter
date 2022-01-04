package com.mwaiterdev.waiter.ui.bill.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.waiter.databinding.BillMenuProductItemBinding

class MenuItemViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: BillMenuProductItemBinding by viewBinding()
    fun bind(item: Item, delegate: MenuAdapter.Delegate?) {
        with(viewBinding) {
            itemName.text = item.name
            itemPrice.text = item.price.price.toString()
            root.click { delegate?.onItemPicked(item) }
        }
    }
}
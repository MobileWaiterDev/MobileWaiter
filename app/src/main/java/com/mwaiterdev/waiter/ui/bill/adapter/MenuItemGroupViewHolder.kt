package com.mwaiterdev.waiter.ui.bill.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.waiter.databinding.BillMenuCategoryItemBinding

class MenuItemGroupViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: BillMenuCategoryItemBinding by viewBinding()

    fun bind(group: ItemGroup, delegate: MenuAdapter.Delegate?) {
        with(viewBinding) {
            categoryName.text = group.name
            card.click { delegate?.onGroupPicked(group) }
            card.setCardBackgroundColor(Color.parseColor(group.bgColor))
            categoryName.setTextColor(Color.parseColor(group.textColor))
        }
    }
}
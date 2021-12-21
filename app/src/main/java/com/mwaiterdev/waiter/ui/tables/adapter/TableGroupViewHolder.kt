package com.mwaiterdev.waiter.ui.tables.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.waiter.databinding.TableGroupItemBinding

class TableGroupViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: TableGroupItemBinding by viewBinding()

    fun bind(group: TableGroupItem, delegate: TablesAdapter.Delegate?) {
        with(viewBinding) {
            groupName.text = group.name
            root.click { delegate?.onGroupPicked(group) }
        }
    }
}
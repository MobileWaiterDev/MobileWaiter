package com.mwaiterdev.waiter.ui.tables.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.utils.extensions.click
import com.mwaiterdev.waiter.databinding.TableItemBinding

class TableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val viewBinding: TableItemBinding by viewBinding()

    fun bind(table: TableItem, delegate: TablesAdapter.Delegate?) {
        with(viewBinding) {
            tableName.text = table.name
            root.click { delegate?.onItemPicked(table) }
        }
    }
}
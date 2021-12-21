package com.mwaiterdev.waiter.ui.tables.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.waiter.R

class TablesAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * столика в одном из залов.
         * @param table Стол
         */
        fun onItemPicked(table: TableItem)

        /**
         * Событие наступает при выборе
         * заголовка.
         * @param group Заголовок
         */
        fun onGroupPicked(group: TableGroupItem)
    }

    private val data = ArrayList<ITableItem>()

    fun addItems(newList: ArrayList<ITableItem>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newList))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        when (data[position]) {
            is TableGroupItem -> return HEADER
            is TableItem -> return ITEM
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM -> {
                val root = LayoutInflater.from(parent.context).inflate(
                    R.layout.table_item,
                    parent,
                    false
                )
                return TableViewHolder(root)
            }
            HEADER -> {
                val root = LayoutInflater.from(parent.context).inflate(
                    R.layout.table_group_item,
                    parent,
                    false
                )
                return TableGroupViewHolder(root)
            }
            else -> throw IllegalArgumentException(ERROR_UNKNOWN_VIEW_TYPE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TableViewHolder -> {
                (holder).bind(data[position] as TableItem, delegate)
            }
            is TableGroupViewHolder -> {
                (holder).bind(data[position] as TableGroupItem, delegate)
            }
        }
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<ITableItem>,
        private var newItems: ArrayList<ITableItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            if (oldItems[oldItemPosition] is TableGroupItem && newItems[newItemPosition] is TableGroupItem) {
                (oldItems[oldItemPosition] as TableGroupItem).tableGroupId == (newItems[newItemPosition] as TableGroupItem).tableGroupId
            } else if (oldItems[oldItemPosition] is TableItem && newItems[newItemPosition] is TableItem) {
                (oldItems[oldItemPosition] as TableItem).tableId == (newItems[newItemPosition] as TableItem).tableId
            } else false

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            if (oldItems[oldItemPosition] is TableGroupItem && newItems[newItemPosition] is TableGroupItem) {
                (oldItems[oldItemPosition] as TableGroupItem) == (newItems[newItemPosition] as TableGroupItem)
            } else if (oldItems[oldItemPosition] is TableItem && newItems[newItemPosition] is TableItem) {
                (oldItems[oldItemPosition] as TableItem) == (newItems[newItemPosition] as TableItem)
            } else false
    }

    companion object {
        const val ITEM = 0
        const val HEADER = 1
        const val ERROR_UNKNOWN_VIEW_TYPE = "Неизвестный view type"
    }
}
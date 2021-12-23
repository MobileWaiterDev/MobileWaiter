package com.mwaiterdev.waiter.ui.bill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.IMenuItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.waiter.R

class MenuAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * товара меню из списка.
         * @param item Товар
         */
        fun onItemPicked(item: Item)

        /**
         * Событие наступает при выборе
         * категории товара меню из списка.
         * @param group Товар
         */
        fun onGroupPicked(group: ItemGroup)
    }

    private val data = ArrayList<IMenuItem>()

    fun addItems(newList: ArrayList<IMenuItem>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newList))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    fun clear() = data.clear()

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        when (data[position]) {
            is ItemGroup -> return GROUP
            is Item -> return ITEM
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM -> {
                val root = LayoutInflater.from(parent.context).inflate(
                    R.layout.bill_menu_product_item,
                    parent,
                    false
                )

                return MenuItemViewHolder(root)
            }
            GROUP -> {
                val root = LayoutInflater.from(parent.context).inflate(
                    R.layout.bill_menu_category_item,
                    parent,
                    false
                )
                return MenuItemGroupViewHolder(root)
            }
            else -> throw IllegalArgumentException(ERROR_UNKNOWN_VIEW_TYPE)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MenuItemViewHolder -> {
                (holder).bind(data[position] as Item, delegate)
            }
            is MenuItemGroupViewHolder -> {
                (holder).bind(data[position] as ItemGroup, delegate)
            }
        }
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<IMenuItem>,
        private var newItems: ArrayList<IMenuItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            if (oldItems[oldItemPosition] is ItemGroup && newItems[newItemPosition] is ItemGroup) {
                (oldItems[oldItemPosition] as ItemGroup).itemGroupId == (newItems[newItemPosition] as ItemGroup).itemGroupId
            } else if (oldItems[oldItemPosition] is Item && newItems[newItemPosition] is Item) {
                (oldItems[oldItemPosition] as Item).itemId == (newItems[newItemPosition] as Item).itemId
            } else false

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            if (oldItems[oldItemPosition] is ItemGroup && newItems[newItemPosition] is ItemGroup) {
                (oldItems[oldItemPosition] as ItemGroup) == (newItems[newItemPosition] as ItemGroup)
            } else if (oldItems[oldItemPosition] is Item && newItems[newItemPosition] is Item) {
                (oldItems[oldItemPosition] as Item) == (newItems[newItemPosition] as Item)
            } else false
    }

    companion object {
        const val ITEM = 0
        const val GROUP = 1
        const val ERROR_UNKNOWN_VIEW_TYPE = "Неизвестный view type"
    }

}
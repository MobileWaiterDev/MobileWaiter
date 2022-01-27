package com.mwaiterdev.waiter.ui.bill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.waiter.R

class BillItemAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<BillItemViewHolder?>(), BillItemTouchHelperAdapter {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * товара из списка счета.
         * @param billItem Товар
         */
        fun onBillItemPicked(billItem: BillItem)

        /**
         * Событие наступает при клике
         * по кнопке изменения количества.
         * @param billItem Товар
         */
        fun onUpdateAmountPicked(billItem: BillItem)

        /**
         * Событие наступает при свайпе товара справа налево.
         * @param billItem Товар
         */
        fun onDeletePicked(billItem: BillItem)
    }

    private val data = ArrayList<BillItem>()

    fun addItems(newList: ArrayList<BillItem>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newList))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newList)
    }

    override fun getItemCount() = data.size

    fun clear() = data.clear()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillItemViewHolder =
        BillItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.bill_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BillItemViewHolder, position: Int) =
        holder.bind(data[position], delegate)

    override fun onItemSwipe(position: Int) {
        delegate?.onDeletePicked(data[position])
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<BillItem>,
        private var newItems: ArrayList<BillItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].billItemId == newItems[newItemPosition].billItemId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
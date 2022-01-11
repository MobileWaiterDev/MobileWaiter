package com.mwaiterdev.waiter.ui.bill.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class BillItemTouchHelperCallback(private val adapter: BillItemTouchHelperAdapter) :
    ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragDirection: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeDirection: Int = ItemTouchHelper.START
        return makeMovementFlags(dragDirection, swipeDirection)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeFlags: Int) {
        adapter.onItemSwipe(viewHolder.absoluteAdapterPosition)
    }
}
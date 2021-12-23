package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.Table
import com.mwaiterdev.waiter.databinding.ItemTableCardviewBinding

class AdapterTables(
    private val data: List<Table>,
    private val billItemListener: View.OnClickListener
) : RecyclerView.Adapter<AdapterTables.ItemTable>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTable = ItemTable(
        ItemTableCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemTable, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ItemTable(
        private val binding: ItemTableCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Table) = with(binding) {
            tableName.text = data.name
            timeBooked.text = data.bill?.createTime.toString()
            totalBill.text = data.bill?.total.toString()
            waitressObserve.text = data.userObserverName
            orderRecycleView.adapter = data.bill?.orders?.let { AdapterOrders(it) }
            root.setOnClickListener(billItemListener)
        }
    }
}
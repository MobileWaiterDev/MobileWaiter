package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.Table
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.waiter.databinding.ItemTableCardviewBinding

class AdapterTables(
    private val data: List<Table>
) : RecyclerView.Adapter<AdapterTables.ItemTable>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTable = ItemTable(
        ItemTableCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemTable, position: Int) {
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = data.size

    inner class ItemTable(
        private val binding: ItemTableCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: List<Table>, position: Int) {
              binding.tableName.text = data[position].name
              binding.timeBooked.text = data[position].bill.createTime.toString()
              binding.totalBill.text = data[position].bill.total.toString()
              binding.waitressObserve.text = data[position].userObserverName
              binding.orderRecycleView.adapter = AdapterOrders(data[position].bill.orders)

        }
    }
}
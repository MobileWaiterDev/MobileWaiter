package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.databinding.ItemOrdersListBinding

class AdapterOrders(
    private val data: List<BillsResponse.TableGroup.Table.Bill.BillItem>?
) : RecyclerView.Adapter<AdapterOrders.ItemOrder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOrder = ItemOrder(
        ItemOrdersListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemOrder, position: Int) {
        holder.bind(data?.get(position))
    }

    override fun getItemCount(): Int {
        if (!data.isNullOrEmpty()){
            return data.size
        }
        return 0
    }
    inner class ItemOrder(
        private val binding: ItemOrdersListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BillsResponse.TableGroup.Table.Bill.BillItem?) {
            binding.orderPosition.text = data?.name
        }
    }
}
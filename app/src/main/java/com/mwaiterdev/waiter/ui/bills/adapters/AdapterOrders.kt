package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.waiter.databinding.ItemOrdersListBinding

class AdapterOrders(
    private val data: List<Item>
) : RecyclerView.Adapter<AdapterOrders.ItemOrder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOrder = ItemOrder(
        ItemOrdersListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemOrder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
    inner class ItemOrder(
        private val binding: ItemOrdersListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Item) {
            binding.orderPosition.text = data.name
        }
    }
}
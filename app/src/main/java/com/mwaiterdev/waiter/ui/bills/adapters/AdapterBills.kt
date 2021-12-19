package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.waiter.databinding.ItemBillsBinding

class AdapterBills(
        private val data: List<TableGroup>
) : RecyclerView.Adapter<AdapterBills.ItemBills>() {
    private var isExpanded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBills = ItemBills(
            ItemBillsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            )
    )

    override fun onBindViewHolder(holder: ItemBills, position: Int) {
        holder.bind(data, position)
    }

    override fun getItemCount(): Int = data.size

    inner class ItemBills(
            private val binding: ItemBillsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: List<TableGroup>, position: Int) {
            binding.hallsName.text = data[position].name
            binding.billsCountAndSum.text = data[position].tables[position].bill.total.toString()
            binding.tablesRecycleView.adapter = AdapterTables(data[position].tables)
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                if (isExpanded){
                    binding.tablesRecycleView.visibility = View.VISIBLE
                } else
                {
                    binding.tablesRecycleView.visibility = View.GONE
                }
                notifyItemChanged(layoutPosition)
            }
        }
    }

}
package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.waiter.databinding.ItemBillsBinding

class AdapterBills(
    private val data: List<TableGroup>,
    private val billItemListener: View.OnClickListener
) : RecyclerView.Adapter<AdapterBills.ItemBills>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBills = ItemBills(
        ItemBillsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemBills, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ItemBills(
            private val binding: ItemBillsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TableGroup)=with(binding) {
            hallsName.text = data.name
            billsCountAndSum.text = data.tables[layoutPosition].bill?.total.toString()
            tablesRecycleView.adapter = AdapterTables(data.tables, billItemListener)
            root.setOnClickListener(hallItemListener(data))
        }

        private fun hallItemListener(data: TableGroup) = View.OnClickListener {
            data.isExpanded = !data.isExpanded
            println(data.isExpanded)
            if (data.isExpanded) {
                binding.tablesRecycleView.visibility = View.VISIBLE
            } else {
                binding.tablesRecycleView.visibility = View.GONE
            }
            notifyItemChanged(layoutPosition)
        }
    }

}
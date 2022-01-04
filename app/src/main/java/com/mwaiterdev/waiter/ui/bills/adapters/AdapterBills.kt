package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.databinding.ItemBillsBinding

class AdapterBills(
    private val data: List<BillsResponse.TableGroup>,
    private val billItemListener: (Long?) -> View.OnClickListener
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
        fun bind(data: BillsResponse.TableGroup) = with(binding) {
            hallsName.text = data.name
            billsCountAndSum.text = getCountAndTotalBills(data, data.tableGroupId)
            if (!data.tables.isNullOrEmpty()) {
                tablesRecycleView.adapter = AdapterTables(data.tables, billItemListener)
            }
            root.setOnClickListener(hallItemListener(data))
        }

        private fun getCountAndTotalBills(
            data: BillsResponse.TableGroup?,
            tableGroupId: Long
        ): CharSequence {
            val totalBills = mutableListOf<Float>()
            val tablesGroupByTableGroup = data
                ?.tables
                ?.filter { table -> table.tableGroupId == tableGroupId }

            tablesGroupByTableGroup?.forEach {
                it?.bills?.forEach {
                    totalBills.add(it.total)
                }
            }
            return "Count of Bills ${totalBills.size} - ${totalBills.sum()} $"

        }

        private fun hallItemListener(data: BillsResponse.TableGroup) = View.OnClickListener {
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
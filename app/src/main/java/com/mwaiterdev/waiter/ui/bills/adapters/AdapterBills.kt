package com.mwaiterdev.waiter.ui.bills.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.ItemBillsBinding
import com.mwaiterdev.waiter.ui.bills.adapters.interfaces.BillsFilter
import kotlin.math.roundToLong

class AdapterBills(
    private val data: List<TableGroup>,
    private val billItemListener: (Long?) -> View.OnClickListener,
) : RecyclerView.Adapter<AdapterBills.ItemBills>(), BillsFilter {
    private val fullData: MutableList<TableGroup?> = mutableListOf()

    init {
        data.map {
            it.isExpanded = true
        }.apply {
            fullData.addAll(data)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBills = ItemBills(
        ItemBillsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemBills, position: Int) {
        fullData[position].let { holder.bind(it) }
        fullData[position]?.let { holder.hallItemExpand(it) }
    }

    override fun getItemCount(): Int {
        if (!fullData.isNullOrEmpty()) {
            return fullData.size
        }
        return 0
    }

    override fun getMineBills(data: List<TableGroup>?) {
        Log.e("MineSwitcher", "Mine Switcher Start")
        fullData.clear()
        if (data != null) {
            fullData.addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun filter(data: List<TableGroup>?) {
        Log.e("SwitcherHall", "Hals Switcher")
        fullData.clear()
        if (data != null) {
            fullData.addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ItemBills(
        private val binding: ItemBillsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TableGroup?) = with(binding) {
            hallsName.text = data?.name
            billsCountAndSum.text = data?.tableGroupId?.let { getCountAndTotalBills(data, it, root.context.getText(R.string.count_of_bills_text)) }
            val bills = data?.tables?.flatMap { table ->
                table.bills as List<BillsResponse.TableGroup.Table.Bill>
            }
            if (!data?.tables.isNullOrEmpty()) {
                tablesRecycleView.adapter =
                    AdapterTables(bills, billItemListener)
            }
            root.setOnClickListener(data?.let { hallItemListener(it) })
        }

        private fun getCountAndTotalBills(
            data: TableGroup?,
            tableGroupId: Long,
            phrase: CharSequence
        ): CharSequence {
            val totalBills = mutableListOf<Float>()
            val tablesGroupByTableGroup = data
                ?.tables
                ?.filter { table -> table.tableGroupId == tableGroupId }

            tablesGroupByTableGroup?.forEach {
                it.bills?.forEach { bill ->
                    totalBills.add(bill.total)
                }
            }
            val result = if(totalBills.sum() > MAX_TOTAL_BILL_BEFORE_ROUND){
                "${(totalBills.sum() / REDUCE_NUMBER).roundToLong()} тыс."
            }  else {
                totalBills.sum()
            }
            return "$phrase ${totalBills.size} - $result $"
        }

        private fun hallItemListener(data: TableGroup) = View.OnClickListener {
            data.isExpanded = !data.isExpanded
            hallItemExpand(data)
            notifyItemChanged(layoutPosition)
        }

        fun hallItemExpand(data: TableGroup) {
            if (data.isExpanded && !data.tables.isNullOrEmpty()) {
                binding.tablesRecycleView.visibility = View.VISIBLE
                binding.headerTitleHall.setBackgroundColor(
                    binding.root.context.resources.getColor(
                        R.color.gray,
                        binding.root.context.resources.newTheme()
                    )
                )
            } else {
                binding.tablesRecycleView.visibility = View.GONE
                binding.headerTitleHall.setBackgroundColor(
                    binding.root.context.resources.getColor(
                        R.color.white,
                        binding.root.context.resources.newTheme()
                    )
                )
            }
        }
    }
    companion object {
        private const val MAX_TOTAL_BILL_BEFORE_ROUND = 10000
        private const val REDUCE_NUMBER = 1000
    }
}
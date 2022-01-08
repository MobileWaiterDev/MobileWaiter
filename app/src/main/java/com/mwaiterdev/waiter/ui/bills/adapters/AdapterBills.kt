package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.ItemBillsBinding
import com.mwaiterdev.waiter.ui.bills.adapters.interfaces.BillsFilter

class AdapterBills(
    private val data: List<BillsResponse.TableGroup>?,
    private val billItemListener: (Long?) -> View.OnClickListener,
) : RecyclerView.Adapter<AdapterBills.ItemBills>(), Filterable, BillsFilter {
    private val fullData: MutableList<BillsResponse.TableGroup?> = mutableListOf()
    private val filteredData: MutableList<BillsResponse.TableGroup?> = mutableListOf()

    init {
        if (data != null) {
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
        holder.bind(fullData[position])
    }

    override fun getItemCount(): Int {
        if (!fullData.isNullOrEmpty()) {
            return fullData.size
        }
        return 0
    }


    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredData.clear()
            if (constraint.isNullOrBlank() || constraint == "All Hals") {
                if (data != null) {
                    filteredData.addAll(data)
                }
            } else {
                if (data != null) {
                    if (fullData.size < data.size) {
                        fullData.addAll(data)
                    }
                }
                val data = fullData.filter { it?.name == constraint }
                filteredData.addAll(data)
            }
            return object : FilterResults() {
                init {
                    values = filteredData
                }
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            fullData.clear()
            fullData.addAll(results?.values as MutableList<BillsResponse.TableGroup>)
            notifyDataSetChanged()
        }
    }

    override fun getMineBills(userId: Long, isCheck: Boolean) {
        fullData.clear()
        filteredData.clear()
        if (isCheck) {
            filterBillsByUserId(userId)
            fullData.addAll(filteredData)
        } else {
            if (data != null) {
                fullData.addAll(data)
            }
        }
        notifyDataSetChanged()
    }

    private fun filterBillsByUserId(userId: Long) {
        data?.filter {
            it.tables?.any {
                it.bills?.any {
                    it.createdByUserId == userId
                } == true
            } == true
        }?.let {
            filteredData.addAll(
                it
            )
        }
    }


    inner class ItemBills(
        private val binding: ItemBillsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BillsResponse.TableGroup?) = with(binding) {
            hallsName.text = data?.name
            billsCountAndSum.text = data?.tableGroupId?.let { getCountAndTotalBills(data, it) }
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
            data: BillsResponse.TableGroup?,
            tableGroupId: Long
        ): CharSequence {
            val totalBills = mutableListOf<Float>()
            val tablesGroupByTableGroup = data
                ?.tables
                ?.filter { table -> table.tableGroupId == tableGroupId }

            tablesGroupByTableGroup?.forEach {
                it?.bills?.forEach { bill ->
                    totalBills.add(bill.total)
                }
            }
            return "Count of Bills ${totalBills.size} - ${totalBills.sum()} $"

        }

        private fun hallItemListener(data: BillsResponse.TableGroup) = View.OnClickListener {
            data.isExpanded = !data.isExpanded
            println(data.isExpanded)
            if (data.isExpanded) {
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
            notifyItemChanged(layoutPosition)
        }
    }

}
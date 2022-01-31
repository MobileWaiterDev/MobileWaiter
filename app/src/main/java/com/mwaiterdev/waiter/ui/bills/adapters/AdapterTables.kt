package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.ItemTableCardviewBinding

class AdapterTables(
    private val bills: List<BillsResponse.TableGroup.Table.Bill>?,
    private val billItemListener: (Long?) -> View.OnClickListener
) : RecyclerView.Adapter<AdapterTables.ItemTable>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTable = ItemTable(
        ItemTableCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ItemTable, position: Int) {
        holder.bind(bills?.get(position))
    }

    override fun getItemCount(): Int {
        if (!bills.isNullOrEmpty()) {
            return bills.size
        }
        return 0
    }

    inner class ItemTable(
        private val binding: ItemTableCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BillsResponse.TableGroup.Table.Bill?) = with(binding) {
            tableName.text = data?.tableName
            timeBooked.text = data?.createTime?.subSequence(11, data.createTime.length)
            totalBill.text = data?.total.toString()
            waitressObserve.text = data?.createdByUserName
            countOfBills.text = data?.customers.toString()
            if (data?.billItems?.size ?: 0 >= 3) {
                itemsText.text = data?.billItems?.subList(0, 3)?.joinToString("\r\n") {
                    it.name
                }
            } else {
                itemsText.text = data?.billItems?.joinToString("\r\n") {
                    it.name
                }
            }
            if (data?.printed == 0) {
                iconPrintBill.setImageResource(R.drawable.circle_printer_grey)
            } else {
                iconPrintBill.setImageResource(R.drawable.circle_printer_blue)
            }
            root.setOnClickListener(billItemListener.invoke(data?.billId))
        }
    }
}
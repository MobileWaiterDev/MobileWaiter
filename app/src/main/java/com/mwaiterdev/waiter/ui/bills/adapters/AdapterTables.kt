package com.mwaiterdev.waiter.ui.bills.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.waiter.databinding.ItemTableCardviewBinding

class AdapterTables(
    private val data: List<BillsResponse.TableGroup.Table>,
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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ItemTable(
        private val binding: ItemTableCardviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BillsResponse.TableGroup.Table) = with(binding) {
            tableName.text = data.name
            timeBooked.text = getDataById(data.tableId, data, TypeDataReq.CreatedTime)
            totalBill.text = getDataById(data.tableId, data, TypeDataReq.TotalBill)
            waitressObserve.text = getDataById(data.tableId, data, TypeDataReq.UserName)
            countOfBills.text = getDataById(data.tableId, data, TypeDataReq.BillsCount)
            if (!data.bills.isNullOrEmpty()){
                orderRecycleView.adapter = AdapterOrders(getBillItems(data.tableId, data))
            }
            root.setOnClickListener(billItemListener.invoke(data?.bills?.first()?.billId))
        }

        private fun getDataById(
            tableId: Long?,
            data: BillsResponse.TableGroup.Table?,
            typeData: TypeDataReq
        ): CharSequence {
              data?.bills?.map {
                  if (it.tableId == tableId){
                      return when(typeData){
                          is TypeDataReq.CreatedTime -> it.createTime.subSequence(11, it.createTime.length)
                          is TypeDataReq.UserName -> it.createdByUserName
                          is TypeDataReq.BillsCount -> it.customers.toString()
                          is TypeDataReq.TotalBill -> it.total.toString()
                      }
                  }
              }
            return "Free"
        }

        private fun getBillItems(
            tableId: Long,
            data: BillsResponse.TableGroup.Table): List<BillsResponse.TableGroup.Table.Bill.BillItem>?{
            data.bills.map {
                if (tableId == it.tableId){
                    return it.billItems
                }
            }
            return null
        }
    }
}
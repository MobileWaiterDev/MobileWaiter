package com.mwaiterdev.waiter.ui.bill

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.models.response.BillItems
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.utils.extensions.showSnakeBar
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillBinding
import com.mwaiterdev.waiter.ui.IScreenView
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bill.adapter.BillItemAdapter
import com.mwaiterdev.waiter.ui.bill.adapter.MenuAdapter
import org.koin.android.ext.android.getKoin

class BillFragment : Fragment(R.layout.fragment_bill), BillItemAdapter.Delegate,
    MenuAdapter.Delegate, IScreenView {

    private val scope = getKoin().createScope<BillFragment>()
    private val viewModel: BillViewModel = scope.get()

    private val viewBinding: FragmentBillBinding by viewBinding()

    private val billItemsAdapter by lazy { BillItemAdapter(this) }
    private val menuAdapter by lazy { MenuAdapter(this) }

    private val billId: Long by lazy { arguments?.getLong(KEY_BILL_ID) ?: ZERO_VALUE }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerForBillItems()
        initRecyclerForMenu()

        viewModel.getLiveData()
            .observe(viewLifecycleOwner, { billItems -> renderData(billItems) })
        viewModel.loadBill(billId)

        viewBinding.homeMenu.setOnClickListener {
            viewModel.getItems(ZERO_VALUE)
        }

        if (billId == ZERO_VALUE) {
            setTitle(NEW_BILL_STRING)
        } else {
            setTitle(String.format(OLD_BILL_STRING, billId))
        }
    }

    private fun setTitle(title: String) {
        (activity as TitleToolbarListener).updateTitle(title = title)
    }

    private fun initRecyclerForMenu() {
        val menuRV: RecyclerView = viewBinding.menuItemsRv
        val gridLayoutManager = GridLayoutManager(context, 3)

        menuRV.layoutManager = gridLayoutManager
        menuRV.adapter = menuAdapter
        menuRV.setHasFixedSize(true)
    }

    private fun initRecyclerForBillItems() {
        val billsRV: RecyclerView = viewBinding.billItemsRv
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        billsRV.layoutManager = linearLayoutManager
        billsRV.adapter = billItemsAdapter
        billsRV.setHasFixedSize(true)
    }

    /** Отобразить существующие товары в счете */
    private fun renderData(billItems: ScreenState?) {
        when (billItems) {
            is ScreenState.Success -> {
                showLoading(false)
                when (billItems.data) {
                    is BillItems -> {
                        billItemsAdapter.addItems((billItems.data as BillItems).data)
                    }
                    is ItemGroups -> {
                        menuAdapter.addItems((billItems.data as ItemGroups).data)
                    }
                }
            }
            is ScreenState.Error -> {
                showLoading(false)
                showError(billItems.error)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
        }
    }

    override fun onBillItemPicked(billItem: BillItem) {
        viewBinding.root.showSnakeBar(billItem.name)
    }

    override fun onItemPicked(item: Item) {
        viewBinding.root.showSnakeBar(item.name)
    }

    override fun onGroupPicked(group: ItemGroup) {
        viewModel.getItems(group.itemGroupId)
    }

    //ToDo Реализовать отображение анимации загрузки
    override fun showLoading(visible: Boolean) {
    }

    override fun showError(error: Throwable) {
        viewBinding.root.showSnakeBar(error.message.toString())
    }

    companion object {
        const val KEY_BILL_ID = "billId"
        const val ZERO_VALUE = 0L
        const val NEW_BILL_STRING = "Новый счет"
        const val OLD_BILL_STRING = "BillId: %s"
    }
}
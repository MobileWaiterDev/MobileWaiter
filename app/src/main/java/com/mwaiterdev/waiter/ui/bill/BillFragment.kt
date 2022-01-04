package com.mwaiterdev.waiter.ui.bill

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.IMenuItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.utils.extensions.showSnakeBar
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillBinding
import com.mwaiterdev.waiter.ui.IScreenView
import com.mwaiterdev.waiter.ui.bill.adapter.BillItemAdapter
import com.mwaiterdev.waiter.ui.bill.adapter.MenuAdapter
import com.mwaiterdev.waiter.ui.bills.BillsFragment
import org.koin.android.ext.android.getKoin

class BillFragment : Fragment(R.layout.fragment_bill), BillItemAdapter.Delegate,
    MenuAdapter.Delegate, IScreenView {

    private val scope = getKoin().createScope<BillFragment>()
    private val viewModel: BillViewModel = scope.get()

    private val viewBinding: FragmentBillBinding by viewBinding()

    private val billItemsAdapter by lazy { BillItemAdapter(this) }
    private val menuAdapter by lazy { MenuAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerForBillItems()
        initRecyclerForMenu()
        initObserve()

        viewModel.getBillItems()
        viewModel.getItemGroups()
        Log.e("arguments", arguments?.get(BillsFragment.BILL_ID).toString())
        viewBinding.homeMenu.setOnClickListener {
            viewModel.getItemGroups()
        }
    }

    private fun initObserve() {
        viewModel.getBillItemLiveData()
            .observe(viewLifecycleOwner, { billItems -> showBillItems(billItems) })

        viewModel.getItemGroupListLiveData()
            .observe(viewLifecycleOwner, { itemGroups -> showMenu(itemGroups) })

        viewModel.getItemListLiveData()
            .observe(viewLifecycleOwner, { items -> showMenu(items) })
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

    /** Отобразить меню */
    private fun showMenu(itemGroups: ScreenState?) {
        when (itemGroups) {
            is ScreenState.Success -> {
                showLoading(false)
                menuAdapter.addItems(itemGroups.result as ArrayList<IMenuItem>)
            }
            is ScreenState.Error -> {
                showLoading(false)
                showError(itemGroups.error)
                viewBinding.root.showSnakeBar(itemGroups.error.message.toString())
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
        }
    }

    /** Отобразить существующие товары в счете */
    private fun showBillItems(billItems: ScreenState?) {
        when (billItems) {
            is ScreenState.Success -> {
                showLoading(false)
                billItemsAdapter.addItems(billItems.result as ArrayList<BillItem>)
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

    companion object {
        fun newInstance() = BillFragment()
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
}
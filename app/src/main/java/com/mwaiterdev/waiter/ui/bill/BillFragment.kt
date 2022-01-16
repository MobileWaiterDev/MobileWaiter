package com.mwaiterdev.waiter.ui.bill

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.models.response.BillItems
import com.mwaiterdev.domain.models.response.BillsInfo
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.domain.models.response.NewBill
import com.mwaiterdev.utils.extensions.showSnakeBar
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillBinding
import com.mwaiterdev.waiter.ui.IScreenView
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.amountdialog.AmountDialog
import com.mwaiterdev.waiter.ui.amountdialog.AmountTypeValue
import com.mwaiterdev.waiter.ui.bill.adapter.BillItemAdapter
import com.mwaiterdev.waiter.ui.bill.adapter.BillItemTouchHelperCallback
import com.mwaiterdev.waiter.ui.bill.adapter.MenuAdapter
import org.koin.android.ext.android.getKoin

class BillFragment : Fragment(R.layout.fragment_bill), BillItemAdapter.Delegate,
    MenuAdapter.Delegate, IScreenView, AmountDialog.IAmountDialog {

    private val scope = getKoin().createScope<BillFragment>()
    private val viewModel: BillViewModel = scope.get()

    private val viewBinding: FragmentBillBinding by viewBinding()

    private val billItemsAdapter by lazy { BillItemAdapter(this) }
    private val menuAdapter by lazy { MenuAdapter(this) }

    private val billId: Long by lazy { arguments?.getLong(KEY_BILL_ID) ?: ZERO_VALUE }
    private val tableId: Long by lazy { arguments?.getLong(KEY_TABLE_ID) ?: ZERO_VALUE }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerForBillItems()
        initRecyclerForMenu()
        initObserver()

        viewModel.setCurrentBill(billId)

        viewBinding.homeMenu.setOnClickListener {
            viewModel.getMenu(ZERO_VALUE)
        }

        if (billId == ZERO_VALUE) {
            viewModel.createBill(tableId)
            setTitle(NEW_BILL_STRING)
        } else {
            viewModel.getBillItems()
            setTitle(String.format(OLD_BILL_STRING, billId))
        }

        viewModel.getMenu()
        viewModel.loadBill(billId)
    }

    private fun setTitle(title: String) {
        (activity as TitleToolbarListener).updateTitle(title = title)
    }

    private fun initRecyclerForMenu() {
        val menuRV: RecyclerView = viewBinding.menuItemsRv
        val gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)

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

        val callback: ItemTouchHelper.Callback = BillItemTouchHelperCallback(billItemsAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(billsRV)
    }

    private fun initObserver() {
        viewModel.billItemsLiveData()
            .observe(viewLifecycleOwner, { billItems -> renderData(result = billItems) })

        viewModel.menuLiveData()
            .observe(viewLifecycleOwner, { menuItems -> renderData(result = menuItems) })

        viewModel.operationLiveData()
            .observe(viewLifecycleOwner, { result -> renderData(result = result) })
    }

    private fun renderData(result: ScreenState?) {
        when (result) {
            is ScreenState.Success -> {
                renderSuccess(result)
            }
            is ScreenState.Error -> {
                renderError(result)
            }
            is ScreenState.Loading -> {
                renderLoading()
            }
            else -> {
                throw IllegalArgumentException(ILLEGAL_STATE_ERROR)
            }
        }
    }

    private fun renderLoading() {
        showLoading(true)
    }

    private fun renderError(result: ScreenState.Error) {
        showLoading(false)
        showError(result.error)
    }

    private fun renderSuccess(result: ScreenState.Success) {
        showLoading(false)
        when (result.data) {
            is ItemGroups -> {
                showMenu(result)
            }
            is BillItems -> {
                showBillItems(result)
            }
            is NewBill -> {
                setCurrentBill(result)
            }
            is BillsInfo -> {
                showBillInfo(result)
            }
        }
    }

    private fun showBillInfo(result: ScreenState.Success) {
        Log.d("WaiterDebug", "renderData -> BillsInfo ${result.data as BillsInfo}")
    }

    private fun setCurrentBill(result: ScreenState.Success) {
        Log.d("WaiterDebug", "renderData -> NewBill")
        viewModel.setCurrentBill((result.data as NewBill).data)
    }

    private fun showBillItems(result: ScreenState.Success) {
        Log.d("WaiterDebug", "renderData -> BillItems")
        billItemsAdapter.addItems((result.data as BillItems).data)
        if ((result.data as BillItems).needScrollToEndPosition) {
            viewBinding.billItemsRv.smoothScrollToPosition(billItemsAdapter.itemCount)
        }
    }

    private fun showMenu(result: ScreenState.Success) {
        Log.d("WaiterDebug", "renderData -> ItemGroups")
        menuAdapter.addItems((result.data as ItemGroups).data)
    }

    override fun onBillItemPicked(billItem: BillItem) {
        viewBinding.root.showSnakeBar(billItem.name)
    }

    override fun onUpdateAmountPicked(billItem: BillItem) {
        val dialogFragment = AmountDialog(AmountTypeValue.FLOAT)

        val args = Bundle()
        args.putLong(ARG_BILL_ITEM_ID, billItem.billItemId)
        args.putFloat(ARG_PRICE, billItem.price)
        dialogFragment.arguments = args

        dialogFragment.setAmountDialogListener(this)
        dialogFragment.isCancelable = false
        dialogFragment.show(parentFragmentManager, AmountDialog.TAG)
    }

    override fun onDeletePicked(billItem: BillItem) {
        viewModel.deleteItem(billItem.billItemId)
        Log.d("waiterDebug", "Удаляем $billItem")
    }

    override fun onItemPicked(item: Item) {
        viewModel.addItemIntoBill(item.itemId, DEFAULT_AMOUNT, item.price.price)
    }

    override fun onGroupPicked(group: ItemGroup) {
        viewModel.getMenu(group.itemGroupId)
    }

    override fun showLoading(visible: Boolean) {
        viewBinding.progress.isVisible = visible
    }

    override fun showError(error: Throwable) {
        viewBinding.root.showSnakeBar(error.message.toString())
    }

    override fun resultValue(resultCode: Int, data: Intent?, args: Bundle?) {
        val billItemId = args?.getLong(ARG_BILL_ITEM_ID) ?: ZERO_VALUE
        val price = args?.getFloat(ARG_PRICE) ?: ZERO_FLOAT_VALUE

        if (billItemId > ZERO_VALUE && price > ZERO_FLOAT_VALUE) {
            doItemOperation(resultCode, data, billItemId, price)
        }
    }

    private fun doItemOperation(
        resultCode: Int,
        data: Intent?,
        billItemId: Long,
        price: Float
    ) {
        if (resultCode == Activity.RESULT_OK) {
            data?.getFloatExtra(AmountDialog.KEY_RESULT, ERROR_VALUE)
                ?.also {
                    if (it == ZERO_FLOAT_VALUE) {
                        viewModel.deleteItem(billItemId)
                    } else {
                        viewModel.updateAmount(billItemId, it, price)
                    }
                }
        }
    }

    companion object {
        const val KEY_BILL_ID = "billId"
        const val KEY_TABLE_ID = "tableId"
        const val ZERO_VALUE = 0L
        const val ZERO_FLOAT_VALUE = 0f
        const val NEW_BILL_STRING = "Новый счет"
        const val OLD_BILL_STRING = "BillId: %s"
        const val DEFAULT_AMOUNT = 1f
        const val ERROR_VALUE = -1f
        const val SPAN_COUNT = 3
        const val ILLEGAL_STATE_ERROR = "Unknown State"
        const val ARG_BILL_ITEM_ID = "billItemId"
        const val ARG_PRICE = "price"
    }
}
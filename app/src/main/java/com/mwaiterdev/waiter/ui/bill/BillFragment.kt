package com.mwaiterdev.waiter.ui.bill

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.models.response.*
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
import com.mwaiterdev.waiter.ui.bill.enums.TypeUpdate
import org.koin.android.ext.android.getKoin

class BillFragment : Fragment(R.layout.fragment_bill), BillItemAdapter.Delegate,
    MenuAdapter.Delegate, IScreenView, AmountDialog.IAmountDialog {

    private val scope = getKoin().createScope<BillFragment>()
    val viewModel: BillViewModel = scope.get()

    private val viewBinding: FragmentBillBinding by viewBinding()

    private val billItemsAdapter by lazy { BillItemAdapter(this) }
    private val menuAdapter by lazy { MenuAdapter(this) }
    private val paymentAdapter by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.payment_item,
            resources.getStringArray(R.array.payments)
        )
    }

    private val billId: Long by lazy { arguments?.getLong(KEY_BILL_ID) ?: ZERO_VALUE }
    private val tableId: Long by lazy { arguments?.getLong(KEY_TABLE_ID) ?: ZERO_VALUE }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        viewModel.setCurrentBill(billId)

        val searchView: Int = viewBinding.searchPanel.context.resources
            .getIdentifier(SEARCH_VIEW_TEXTVIEW, null, null)

        //???????????????? ?????? ???????????????? ??????????
        initBill()
        //?????????????????????????? ???????????????? ?????? RV ?????????????? ??????????
        initRecyclerForBillItems()
        //?????????????????????????? ???????????????? ?????? RV ????????
        initRecyclerForMenu()
        //?????????????????????????? ????????????????
        initObserver()
        //?????????????????????????? ???????????? ?????????????????? ???? ???????????? ????????????????
        initNavigationPanel(searchView)
        //?????????????????????????? ???????????????? ????????????
        initSearchSettings(searchView)
        //?????????????????????????? buttons clickListeners
        initSetClickListeners()
        //?????????????????????????? ???????????????????????????? ????????????????
        initStartSettings()

        viewModel.getMenu()

        //???????????????????? ?????????????? ?????????????????? ???????????? ??????????. ???????? ???????? ????????????, ???? ?????????? ?????????????? ???????????? ??????
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.deleteBill()
                    isEnabled = true
                }
            })
    }

    private fun initStartSettings() {
        with(viewBinding) {
            closeBillPanel.isVisible = false
            closeBillForm.isVisible = false
            payInput.setAdapter(paymentAdapter)
            billItemsRv.isEnabled = true
        }
    }

    private fun initSetClickListeners() {
        viewBinding.cookBtn.setOnClickListener {
            viewModel.sendCookBill()
        }

        viewBinding.billCloseBtn.setOnClickListener {
            viewModel.printBill()
        }

        viewBinding.okPayBtn.setOnClickListener {
            if (billItemsAdapter.itemCount > 0) {
                viewModel.closeBill()
            } else {
                viewBinding.root.showSnakeBar(EMPTY_BILL_STRING)
            }
        }

        viewBinding.cancelPayBtn.setOnClickListener {
            viewBinding.closeBillForm.isVisible = false
            viewBinding.billItemsRv.isEnabled = true
            viewBinding.menuItemsRv.isVisible = false
            viewBinding.closeBillPanel.isVisible = false
            viewBinding.searchPanel.isVisible = false
        }

        viewBinding.payInput.setOnItemClickListener { _, _, position, _ ->
            val value = paymentAdapter.getItem(position) ?: ""
            viewBinding.okPayBtn.isEnabled = !value.isEmpty()
        }
    }

    private fun initSearchSettings(searchViewPlateId: Int) {
        viewBinding.searchPanel.findViewById<TextView>(searchViewPlateId)
            .setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(v.text.toString()).not()) {
                        viewModel.search(v.text.toString().lowercase())
                        true
                    } else {
                        viewBinding.root.showSnakeBar(EMPTY_SEARCH_TEXT)
                        false
                    }
                }
                true
            }

        viewBinding.searchPanel.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty()) {
                        viewModel.search(newText.lowercase())
                    } else {
                        menuAdapter.clear()
                        menuAdapter.notifyDataSetChanged()
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {

                    return true
                }
            }
        )
    }

    private fun initNavigationPanel(searchViewPlateId: Int) {
        viewBinding.menuPanel.selectedItemId = R.id.menu_home
        viewBinding.menuPanel.setOnItemSelectedListener {
            viewBinding.searchPanel.findViewById<TextView>(searchViewPlateId).text = ""
            when (it.itemId) {
                R.id.menu_find -> {
                    search()
                    viewBinding.menuItemsRv.isVisible = true
                    viewBinding.searchPanel.isIconified = false;
                    viewBinding.closeBillPanel.isVisible = false
                    true
                }
                R.id.menu_favourite -> {
                    favourite()
                    viewBinding.menuItemsRv.isVisible = true
                    viewBinding.closeBillPanel.isVisible = false
                    true
                }
                R.id.menu_home -> {
                    viewBinding.searchPanel.isVisible = false
                    viewModel.getMenu(ZERO_VALUE)
                    viewBinding.menuItemsRv.isVisible = true
                    viewBinding.closeBillPanel.isVisible = false

                    true
                }
                R.id.menu_arrow -> {
                    viewBinding.menuItemsRv.isVisible = false
                    viewBinding.searchPanel.isVisible = false
                    viewBinding.closeBillPanel.isVisible = false
                    true
                }
                R.id.menu_print -> {
                    viewBinding.searchPanel.isVisible = false
                    viewBinding.menuItemsRv.isVisible = true
                    viewBinding.closeBillPanel.isVisible = true
                    true
                }
                else -> false
            }
        }
    }

    private fun initBill() {
        if (billId == ZERO_VALUE) {
            viewModel.createBill(tableId)
            viewBinding.menuItemsRv.isVisible = true
        } else {
            viewModel.getBillItems()
            viewModel.getBillInfo()
            viewBinding.menuItemsRv.isVisible = false
        }
    }

    //???????????????????? ?????????????? ???????????? ?????????? ?? ??????????????. ???????? ???????? ????????????, ???? ?????????? ?????????????? ???????????? ??????
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                viewModel.deleteBill()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favourite() {
        viewBinding.searchPanel.isVisible = false
        menuAdapter.clear()
        menuAdapter.notifyDataSetChanged()
        viewModel.getFavouriteMenu()
    }

    private fun search() {
        viewBinding.searchPanel.isVisible = true
        menuAdapter.clear()
        menuAdapter.notifyDataSetChanged()
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
            .observe(viewLifecycleOwner, { billItems -> renderBillItems(result = billItems) })

        viewModel.menuLiveData()
            .observe(viewLifecycleOwner, { menuItems -> renderMenu(result = menuItems) })

        viewModel.billInfoLiveData()
            .observe(viewLifecycleOwner, { result -> renderBillInfo(result = result) })

        viewModel.newBillLiveData()
            .observe(viewLifecycleOwner, { result -> renderNewBill(result = result) })

        viewModel.deleteBillLiveData()
            .observe(viewLifecycleOwner, { result -> renderDeleteBill(result = result) })

        viewModel.sendCookBillLiveData()
            .observe(viewLifecycleOwner, { result -> renderSendCookBill(result = result) })

        viewModel.deleteEmergencyLiveData()
            .observe(viewLifecycleOwner, { result -> renderDeleteEmergencyItem(result = result) })

        viewModel.printBillLiveData()
            .observe(viewLifecycleOwner, { result -> renderPrintBill(result = result) })

        viewModel.closeBillLiveData()
            .observe(viewLifecycleOwner, { result -> renderCloseBill(result = result) })

        viewModel.blockEditLiveData()
            .observe(viewLifecycleOwner, { result -> blockEditRender(result) })
    }

    private fun blockEditRender(result: Boolean?) {
        if (result == true) {
            viewBinding.menuPanel.menu.forEach {
                if (it.itemId != R.id.menu_print
                    && it.itemId != R.id.menu_arrow
                ) {
                    it.isEnabled = false
                }
            }

        } else {
            viewBinding.menuPanel.menu.forEach {
                it.isEnabled = true
            }
        }
    }

    private fun renderCloseBill(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
                viewBinding.progressCloseBill.isVisible = false
            }
            is ScreenState.Loading -> {
                showLoading(true)
                viewBinding.progressCloseBill.isVisible = true
            }
            is ScreenState.Success -> {
                showLoading(false)
                viewBinding.progressCloseBill.isVisible = false
                if ((result.data as ResultOperation).data) {
                    NavHostFragment.findNavController(this).popBackStack()
                }
            }
        }
    }

    private fun renderPrintBill(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                showLoading(false)
                if ((result.data as ResultOperation).data) {
                    initPaymentSettings()
                    viewModel.getBillInfo()
                    viewModel.getBillItems(false)
                }
            }
        }
    }

    private fun initPaymentSettings() {
        with(viewBinding) {
            okPayBtn.isEnabled = false
            payInput.setText("")
            closeBillForm.isVisible = true
            billItemsRv.isEnabled = false
        }
    }

    private fun renderDeleteEmergencyItem(result: ScreenState?) {}

    private fun renderSendCookBill(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
                viewBinding.cookingProcessPanel.isVisible = false
            }
            is ScreenState.Loading -> {
                showLoading(true)
                viewBinding.cookingProcessPanel.isVisible = true
            }
            is ScreenState.Success -> {
                showLoading(false)
                viewBinding.cookingProcessPanel.isVisible = false
                if ((result.data as ResultOperation).data) {
                    viewModel.getBillItems(false)
                }
            }
        }
    }

    //?????????????????? ???????????????? ??????????
    private fun renderDeleteBill(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                showLoading(false)
                if ((result.data as ResultOperation).data) {
                    viewBinding.root.showSnakeBar("???????????? ???????? ????????????.")
                }
                NavHostFragment.findNavController(this).popBackStack()
            }
        }
    }

    //?????????????????? ???????????????? ??????????
    private fun renderNewBill(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                setCurrentBill(result)
                showLoading(false)
            }
        }
    }

    //?????????????????? ???????????? ?? ??????????
    private fun renderBillInfo(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                renderError(result)
                showLoading(false)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                showBillInfo(result)
                if ((result.data as BillsInfo).data.printed == 1) {
                    viewModel.setBillPrinted(true)
                } else {
                    viewModel.setBillPrinted(false)
                }
                showLoading(false)
            }
        }
    }

    //?????????????????? ????????
    private fun renderMenu(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                viewBinding.noItemPanel.isVisible = true
                showLoading(false)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                viewBinding.noItemPanel.isVisible = false
                showMenu(result)
                showLoading(false)
            }
        }
    }

    //?????????????????? ?????????????? ??????????
    private fun renderBillItems(result: ScreenState?) {
        when (result) {
            is ScreenState.Error -> {
                showLoading(false)
                viewBinding.noDataPanel.isVisible = true
                viewBinding.billItemsRv.isVisible = false
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            is ScreenState.Success -> {
                viewBinding.noDataPanel.isVisible = false
                viewBinding.billItemsRv.isVisible = true
                showLoading(false)
                showBillItems(result)
            }
            null -> {}
        }
    }

    //?????????????????? ???????????? ????????????
    private fun renderError(result: ScreenState.Error) {
        showError(result.error)
    }

    //???????????????????? ???????????????????? ?? ??????????
    private fun showBillInfo(result: ScreenState.Success) {
        val billInfo = (result.data as BillsInfo).data
        viewBinding.billInfo.text = String.format(
            BILL_INFO, billInfo.countItems, billInfo.count, billInfo.total
        )

        val topTitle = String.format(
            BILL_TOP_TITLE, billInfo.tableGroup,
            billInfo.tableName, billInfo.billNumber
        )

        val downTitle = String.format(
            BILL_DOWN_TITLE, billInfo.createTime,
            billInfo.userName, billInfo.total
        )

        with(viewBinding) {
            billSubTotal.text = billInfo.subtotal.toString()
            billTotal.text = billInfo.total.toString()
            billDiscount.text = ZERO_DISCOUNT_PERCENT
            billSumDiscount.text = ZERO_DISCOUNT_SUM
            totalValue.text = String.format(TOTAL_STRING, billInfo.total)
        }

        (activity as TitleToolbarListener).setMultiLineTitle(topTitle, downTitle)
    }

    //?????????????????? ???????????????? ???????????????? id ??????????
    private fun setCurrentBill(result: ScreenState.Success) {
        viewModel.setCurrentBill((result.data as NewBill).data)
    }

    //?????????????????????? ???????????????????? ?? ?????????????? ??????????
    private fun showBillItems(result: ScreenState.Success) {
        billItemsAdapter.addItems((result.data as BillItems).data)
        if ((result.data as BillItems).needScrollToEndPosition) {
            viewBinding.billItemsRv.smoothScrollToPosition(billItemsAdapter.itemCount)
        }
    }

    //?????????????????????? ????????
    private fun showMenu(result: ScreenState.Success) {
        menuAdapter.addItems((result.data as ItemGroups).data)
    }

    override fun onBillItemPicked(billItem: BillItem) {}

    override fun onUpdateAmountPicked(billItem: BillItem) {
        val dialogFragment = AmountDialog(AmountTypeValue.FLOAT)
        val args = Bundle()
        args.putLong(ARG_BILL_ITEM_ID, billItem.billItemId)
        args.putFloat(ARG_PRICE, billItem.price)
        dialogFragment.arguments = args

        dialogFragment.setAmountDialogListener(this)
        dialogFragment.show(parentFragmentManager, AmountDialog.TAG)
    }

    override fun onDeletePicked(billItem: BillItem) {
        when (billItem.printed) {
            STATE_PRINTED -> {
                viewModel.deleteEmergencyItem(billItem.billItemId)
            }
            STATE_EMERGENCY_DELETED -> {
                viewModel.getBillItems(false)
            }
            else -> {
                viewModel.deleteItem(billItem.billItemId)
            }
        }
    }

    override fun onItemPicked(item: Item) {
        viewModel.addItemIntoBill(item.itemId, DEFAULT_AMOUNT, item.price.price)
    }

    override fun onGroupPicked(group: ItemGroup) {
        viewModel.setItemGroupId(group.itemGroupId)
        viewModel.getMenu(group.itemGroupId)
    }

    override fun onItemLongClick(item: Item) {
        when (viewBinding.menuPanel.selectedItemId) {
            R.id.menu_home -> {
                viewModel.updateFavouriteState(item.favourite, item.itemId, TypeUpdate.updateMenu)
            }
            R.id.menu_favourite -> {
                viewModel.updateFavouriteState(
                    item.favourite,
                    item.itemId,
                    TypeUpdate.updateFavourite
                )
            }
            R.id.menu_find -> {
                val searchViewPlateId: Int = viewBinding.searchPanel.context.resources
                    .getIdentifier(SEARCH_VIEW_TEXTVIEW, null, null)
                val request = viewBinding.searchPanel.findViewById<TextView>(searchViewPlateId).text
                viewModel.updateFavouriteState(
                    item.favourite,
                    item.itemId,
                    TypeUpdate.updateSearch,
                    request.toString().lowercase()
                )
            }
        }
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
        const val DEFAULT_AMOUNT = 1f
        const val ERROR_VALUE = -1f
        const val SPAN_COUNT = 3
        const val ILLEGAL_STATE_ERROR = "Unknown State"
        const val ARG_BILL_ITEM_ID = "billItemId"
        const val ARG_PRICE = "price"
        const val BILL_INFO = "?????????????? - %s ???????????????????? - %s ?????????? - %s???"
        const val BILL_TOP_TITLE = "%s - %s - ???????? %s"
        const val BILL_DOWN_TITLE = "%s %s (%s???)"
        const val SEARCH_VIEW_TEXTVIEW = "android:id/search_src_text"
        const val EMPTY_SEARCH_TEXT = "?????????????? ?????????? ?????? ????????????"
        const val ZERO_DISCOUNT_PERCENT = "0%"
        const val ZERO_DISCOUNT_SUM = "0??"
        const val STATE_PRINTED = 1
        const val STATE_EMERGENCY_DELETED = 2
        const val TOTAL_STRING = "%s???"
        const val EMPTY_BILL_STRING = "???????????? ????????. ???????????????? ????????????."
    }
}
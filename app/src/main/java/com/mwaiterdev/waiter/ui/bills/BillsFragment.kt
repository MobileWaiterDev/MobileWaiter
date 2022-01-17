package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.SimpleItemAnimator
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.utils.extensions.showAlertDialogFragment
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillsBinding
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bills.adapters.AdapterBills
import org.koin.android.ext.android.getKoin

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val scope = getKoin().createScope<BillsFragment>()
    private val viewModel: BillsViewModel = scope.get()
    private val viewBinding: FragmentBillsBinding by viewBinding()
    private var data: BillsResponse? = null
    private var adapter: AdapterBills? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        setFloatingActionButtonListener()
    }

    override fun onStart() {
        viewModel.getLiveData().observe(viewLifecycleOwner){ renderData(it) }
        viewModel.initFilterData()
        viewModel.getData()
        super.onStart()
    }


    private fun setFloatingActionButtonListener() {
        viewBinding.addBill.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.nav_tables)
        }
    }

    private fun setBillItemListener(billId: Long?) = View.OnClickListener {
        NavHostFragment.findNavController(this).navigate(R.id.nav_bill, bundleOf().apply {
            billId?.let { billId -> putLong(BILL_ID, billId) }
        })
    }


    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is BillsResponse) {
                    data = appState.data as BillsResponse
                    data?.let { data ->
                        initAdapter(data)
                        initExpandedFilter(data)
                        initTitleToolBar(data)
                    }
                    initSwitchMine()
                }
            }
            is AppState.Error -> {
                showAlertDialogFragment(requireContext(), appState.error.localizedMessage)
            }
            else -> null
        }
    }

    private fun initSwitchMine() {
        viewBinding.mineBillsSwitcher.isChecked = viewModel.initFilterData()
        viewBinding.mineBillsSwitcher.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFilter(isChecked)
            adapter?.getMineBills(
                1L,
                isChecked
            )
            viewBinding.spinnerTableGroups.setSelection(0)
        }
    }

    private fun initTitleToolBar(data: BillsResponse) {
        data.tableGroups
            ?.firstOrNull()
            ?.tables
            ?.firstOrNull()
            ?.bills
            ?.firstOrNull()?.let {
                (activity as TitleToolbarListener).updateTitle(
                    it
                        .createdByUserName
                )
            }
    }

    private fun initAdapter(data: BillsResponse) {
        (viewBinding.billsRecycleView.itemAnimator as SimpleItemAnimator)
            .supportsChangeAnimations = false
        adapter = AdapterBills(
            data.tableGroups
        ) { billId: Long? -> setBillItemListener(billId) }
        viewBinding.billsRecycleView.adapter = adapter
        adapter?.getMineBills(1L, isCheck = viewModel.initFilterData())

    }

    private fun initExpandedFilter(data: BillsResponse) {
        val listTitleOfHals = data.tableGroups?.map {
            it.name
        } as MutableList<String>
        listTitleOfHals.add(0, ALL_HALS)

        viewBinding.spinnerTableGroups.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            listTitleOfHals
        )

        viewModel.initHallSwitcherItem()
            .let { position -> viewBinding.spinnerTableGroups.setSelection(position) }

        viewBinding.spinnerTableGroups.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    adapter?.filter?.filter(listTitleOfHals[position])
                    viewModel.setHallSwitcherItem(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewBinding.billsRecycleView.adapter = AdapterBills(
                        data.tableGroups
                    ) { billId: Long? -> setBillItemListener(billId) }
                }
            }
    }


    companion object {
        const val BILL_ID = "billId"
        const val ALL_HALS = "All Hals"
        fun newInstance() = BillsFragment()
    }
}